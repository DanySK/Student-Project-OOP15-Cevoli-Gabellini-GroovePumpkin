package controller.musicplayer;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import controller.UpdatableObserversManager;
import controller.songplayer.MidiSongPlayer;
import controller.songplayer.SampledSongPlayer;
import controller.songplayer.SongInfosManager;
import controller.songplayer.SongPlayer;
import controller.songplayer.SongWatchDog;
import model.PlayerState;
import model.SongPlayerState;
import model.playlistmanager.PlaylistManager;
/**
 * A basic implementation of a Music Player that manage the main function of a MusicPlayer
 * see also {@link controller.musicplayer.MusicPlayer}
 * 
 * The classes that extend this abstract class must implement the action to do after the song ending
 * @author Matteo Gabellini
 * 
 */
public abstract class AbstractMusicPlayer extends UpdatableObserversManager implements MusicPlayer{
	
	private volatile boolean waitBeforeChange;
	private final PlaylistManager<URL> model;	
	
	private Optional<SongWatchDog> threadSongWatcher;	
	private Optional<SongPlayer> soundPlayer;

	public AbstractMusicPlayer(final PlaylistManager<URL> plManager) {
		super();
		this.model = plManager;
		this.soundPlayer = Optional.empty();
		this.threadSongWatcher = Optional.empty();
		this.waitBeforeChange = true;
	}

	

	private void changeSong() {
		final SongPlayerState preChangePlayerState = (this.soundPlayer
				.isPresent() ? this.soundPlayer.get().getState()
				: SongPlayerState.STOPPED);

		// Check if the player is present and if is active
		if (this.soundPlayer.isPresent()
				&& !this.soundPlayer.get().equals(SongPlayerState.STOPPED)) {
			// if the player is active I stop them...
			// For the correct synchronization of threads i call the stop method
			// of this class
			this.stop();
		}
		this.notifyToUpdatable(PlayerState.SONGCHANGED);
		// If before the song changing the song player was running...
		if (preChangePlayerState == SongPlayerState.RUNNING) {
			Thread threadChange = new Thread(new Runnable() {

				@Override
				public void run() {
					/*If the user invoke the methods to change song more 
					 * than one time in less than 500 millisecond wait 
					 * before start the new song*/
					while (AbstractMusicPlayer.this.waitBeforeChange) {
						AbstractMusicPlayer.this.waitBeforeChange = false;
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					AbstractMusicPlayer.this.play();
				}
			});
			threadChange.start();
		}
	}

	@Override
	public synchronized void goToNextSong() {
		if (this.getCurrentSong().isPresent()) {
			if (this.model.changeToTheNextSong().isPresent()) {
				this.waitBeforeChange = true;
				this.changeSong();
			}
		}
	}

	@Override
	public synchronized void goToPreviousSong() {
		if (this.getCurrentSong().isPresent()) {
			if (this.model
					.changeToThePreviousSong().isPresent()) {
				this.waitBeforeChange = true;
				this.changeSong();
			}
		}
	}

	@Override
	public synchronized void goToSong(final int index) {
		if (this.model.changeSong(index).isPresent()) {
			this.waitBeforeChange = true;
			this.changeSong();
		}
	}

	@Override
	public  Optional<URL> getCurrentSong() {
		return this.model.getCurretSong();
	}

	private void loadSong(final URL songPath) {

		AudioInputStream audioStream;
		Sequence midiSequence;
		try {
			midiSequence = MidiSystem.getSequence(songPath);
			try {
				this.soundPlayer = Optional
						.of(new MidiSongPlayer(midiSequence));
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			}
		} catch (InvalidMidiDataException e1) {
			// if isn't a midi sequence i try to load like an audio file
			try {
				audioStream = AudioSystem.getAudioInputStream(songPath);


				// Controllo se il file che ho caricato è in codifica PCM
				// floating point
				if (audioStream.getFormat().getEncoding() == AudioFormat.Encoding.PCM_FLOAT) {
					// Convert in the correct audio format
					audioStream = AudioSystem.getAudioInputStream(
							new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
									audioStream.getFormat().getSampleRate(),
									24, audioStream.getFormat().getChannels(),
									6, audioStream.getFormat().getFrameRate(),
									false), audioStream);
				}

				try {
					this.soundPlayer = Optional.of(new SampledSongPlayer(
							audioStream));
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (UnsupportedAudioFileException uafe) {
				uafe.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public synchronized void play() {
		if (this.model.getCurretSong().isPresent()) {
			if (!this.soundPlayer.isPresent()) {
				// If there isn't a sound player i try to load the current song
				// of the playlist
				this.loadSong(this.model.getCurretSong().get());
			}

			try {

				this.soundPlayer.get().play();
				if (this.soundPlayer.get().isActive()
						&& !threadSongWatcher.isPresent()) {
					threadSongWatcher = Optional.of(new SongWatchDog(this,
							this.soundPlayer.get()));
					threadSongWatcher.get().start();
				}

				this.notifyToUpdatable(soundPlayer.get().getState() == SongPlayerState.RUNNING ? PlayerState.RUNNING
						: PlayerState.ERROR);
			} catch (NoSuchElementException e) {
				this.notifyToUpdatable(PlayerState.ERROR);
			}
		}
	}

	protected abstract void afterSongEnding();

	@Override
	public  synchronized void stop() {
		if(this.soundPlayer.isPresent()){
			final boolean songEnded = this.soundPlayer.get().getState().equals(SongPlayerState.RUNNING) && !this.soundPlayer.get().isActive();
			this.soundPlayer.get().stop();
			// If the song was invocated by the
			// song watchdog the soundPlayer is not active
			if (!songEnded && this.threadSongWatcher.isPresent()) {
				// wait the termination of song watchdog
				try {
					this.threadSongWatcher.get().join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
			this.notifyToUpdatable(this.soundPlayer.get().getState() == SongPlayerState.STOPPED ? PlayerState.STOPPED
					: PlayerState.ERROR);
			this.threadSongWatcher = Optional.empty();
			this.soundPlayer = Optional.empty();
			//If the state of the sound player is RUNNING and the sound player isn't active means that the stop was called by the song watchdog because the song was terminated
			if(songEnded){
				this.afterSongEnding();
			} 
		}
	}
	
	@Override
	public synchronized void pause() {
		if (this.soundPlayer.isPresent()) {
			this.soundPlayer.get().pause();
			this.notifyToUpdatable(soundPlayer.get().getState() == SongPlayerState.PAUSED ? PlayerState.PAUSED
					: PlayerState.ERROR);
		}
	}
	
	public void addSong(final URL songPath) throws IllegalArgumentException {
		this.model.addSongToPlayList(songPath);
		this.notifyToUpdatable(PlayerState.RELOAD);
	}

	@Override
	public  void removeSong(final int index) throws IllegalArgumentException {
		if (this.soundPlayer.isPresent()
				&& this.model.getCurrentSongIndex().get() == index) {
			this.soundPlayer.get().stop();
			this.soundPlayer = Optional.empty(); 
			this.notifyToUpdatable(PlayerState.REMOVED);
		}
		this.model.removeSongFromPlayList(index);
		this.notifyToUpdatable(PlayerState.RELOAD);
	}

	@Override
	public List<URL> getPlayList() {
		return this.model.getPlayList();
	}

	@Override
	public Optional<SongInfosManager> getCurrentSongInfosManager(){
		return this.soundPlayer.isPresent()? Optional.of((SongInfosManager) this.soundPlayer.get()) : Optional.empty();
	}
	
}
