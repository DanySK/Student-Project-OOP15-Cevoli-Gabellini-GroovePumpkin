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
import controller.songplayer.SongPlayer;
import controller.songplayer.SongWatchDog;
import model.PlayerState;
import model.SongPlayerState;
import model.playlistmanager.ClassicPlaylistManager;
import model.playlistmanager.ExtendedPlaylistManager;
import model.playlistmanager.ShuffablePlaylistManager;
/**
 * A Basic implementation of a Music Player that manage the main function of a MusicPlayer
 * @author matteogabellini
 * 
 */
public class BasicMusicPlayer extends UpdatableObserversManager implements MusicPlayer, Shuffable{
	
	protected final ExtendedPlaylistManager<URL> model;	
	
	/*
	 * Questa variabile contiene un oggeto di una classe anonima che implementa
	 * il comportamento per controllare quando la canzone termina perchè è stata
	 * riprodotta tutta
	 */
	private Optional<SongWatchDog> threadSongWatcher;	
	private Optional<SongPlayer> soundPlayer; // this field rappresents the
												// concrete track player

	public BasicMusicPlayer(final ExtendedPlaylistManager<URL> plManager) {
		if(plManager == null){
			this.model = new ClassicPlaylistManager<URL>();
		} else {
			this.model = plManager;
		}
		this.soundPlayer = Optional.empty();
	}

	

	private void changeSong(final Optional<URL> song) {
		if (song.isPresent()) {
			// Se la canzone indicata dall'indice è presente la carico

			final SongPlayerState preChangePlayerState = (this.soundPlayer
					.isPresent() ? this.soundPlayer.get().getState()
					: SongPlayerState.STOPPED);

			this.loadSong(song.get());
			notifyToUpdatable(PlayerState.SONGCHANGED);
			// If before the song changing the song player was running...
			if (preChangePlayerState == SongPlayerState.RUNNING) {
				// Start the reproduction of the new song
				this.play();
			}

		}
	}

	@Override
	public synchronized void goToNextSong() {
		final Optional<URL> nextSong = this.model.changeToTheNextSong();
		if (nextSong.isPresent()) {
			this.changeSong(nextSong);
		}
	}

	@Override
	public synchronized void goToPreviousSong() {
		final Optional<URL> previousSong = this.model.changeToThePreviousSong();
		if (previousSong.isPresent()) {
			this.changeSong(previousSong);
		}
	}

	@Override
	public synchronized void goToSong(final int index) {
		final Optional<URL> song = this.model.changeSong(index);
		if (song.isPresent()) {
			this.changeSong(song);
		}
	}

	@Override
	public synchronized Optional<URL> getCurrentSong() {
		return this.model.getCurretSong();
	}

	/* This method take the URL of the song and try to load the track */
	private void loadSong(final URL songPath) {
		// Check if the player is present and if is active
		if (this.soundPlayer.isPresent() && !this.soundPlayer.get().equals(SongPlayerState.STOPPED)) {
			// if the player is active I stop them...
			// For the correct synchronization of threads i call the stop method
			// of this class
			this.stop();
		}

		AudioInputStream audioStream;
		Sequence midiSequence;
		// Provo a caricare l'url come sequenza midi
		try {
			midiSequence = MidiSystem.getSequence(songPath);
			try {
				this.soundPlayer = Optional
						.of(new MidiSongPlayer(midiSequence));
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			}
		} catch (InvalidMidiDataException e1) {
			// Nel caso non sia una sequenza midi provo a caricarla come file
			// audio
			try {
				audioStream = AudioSystem.getAudioInputStream(songPath);

				// System.out.println("Before converting " +
				// audioStream.getFormat());

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
		// Controllo se soundPlayer non è stato istanziato
		if (!this.soundPlayer.isPresent()) {
			// Se non è stato creato lo creo e gli passo la canzone corrente
			// della play list
			this.loadSong(this.model.getCurretSong().get());
		}

		try {
			// Provo ad avviare il lettore nel caso non fosse stato creato
			// catturo con il catch l'eccezione
			this.soundPlayer.get().play();
			// Chiedo al lettore lo stato perchè dipende da esso e lo notifico
			// e lo notifico
			notifyToUpdatable(soundPlayer.get().getState() == SongPlayerState.RUNNING ? PlayerState.RUNNING
					: PlayerState.ERROR);
			// Avvio un thread che controlla quando la canzone termina se
			// non è già attivo
			if (this.soundPlayer.get().isActive() && (threadSongWatcher.isPresent() || !threadSongWatcher.get().isAlive())) {
					// VALUTA LA POSSIBILITA' DI USARE META EVENT LISTENER PER
					// CONTROLLARE LA FINE DEL MIDI
					threadSongWatcher = Optional.of(new SongWatchDog(this, this.soundPlayer.get()));
					threadSongWatcher.get().start();				
			}
		} catch (NoSuchElementException e) {
			notifyToUpdatable(PlayerState.ERROR);
		}
	}

	protected void afterSongEnding() {
		this.goToNextSong();				
		this.play();
	}

	@Override
	public synchronized void stop() {
		if(this.soundPlayer.isPresent()){
			final boolean songEnded = this.soundPlayer.get().getState().equals(SongPlayerState.RUNNING) && !this.soundPlayer.get().isActive();
			this.soundPlayer.get().stop();
			// If the song was invocated by the
			// song watchdog the soundPlayer is not active
			if (!songEnded) {
				// wait the termination of song watchdog
				try {
					this.threadSongWatcher.get().join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// Chiedo al lettore lo stato perchè dipende da esso e lo
				// notifico
				notifyToUpdatable(this.soundPlayer.get().getState() == SongPlayerState.STOPPED ? PlayerState.STOPPED
						: PlayerState.ERROR);
			} 
			this.soundPlayer = Optional.empty();
			this.threadSongWatcher = Optional.empty();
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
			// Chiedo al lettore lo stato perchè dipende da esso e lo notifico
			notifyToUpdatable(soundPlayer.get().getState() == SongPlayerState.PAUSED ? PlayerState.PAUSED
					: PlayerState.ERROR);
		}
	}
	
	public void addSong(final URL songPath) throws IllegalArgumentException {
		this.model.addSongToPlayList(songPath);
		notifyToUpdatable(PlayerState.RELOAD);
	}

	@Override
	public synchronized void removeSong(final int index) throws IllegalArgumentException {
		// Se la canzone che voglio togliere e quella che sto riproducendo
		// blocco la riproduzione
		if (this.soundPlayer.isPresent()
				&& this.model.getCurrentSongIndex().get() == index) {
			this.soundPlayer.get().stop();
			notifyToUpdatable(PlayerState.REMOVED);
		}
		this.model.removeSongFromPlayList(index);
		notifyToUpdatable(PlayerState.RELOAD);
	}

	@Override
	public List<URL> getPlayList() {
		return this.model.getPlayList();
	}

	@Override
	public double getElapsedTime() {
		if (this.soundPlayer.get().isActive()) {
			return this.soundPlayer.get().getElapsedTime();
		}
		return 0;
	}
	
	@Override
	public double getDurationOfCurrentSong() {
		return this.soundPlayer.isPresent()? this.soundPlayer.get().getDuration() : 0;
	}
	
	@Override 
	public synchronized void setPosition(final int time) throws IllegalArgumentException{
		if(this.soundPlayer.isPresent()){
			this.soundPlayer.get().setPosition(time);
		}
	}
	
	@Override
	public void setShuffleMode(final boolean active) {
		this.model.setFeatureState(ShuffablePlaylistManager.class,active);	
		notifyToUpdatable(this.model.isFeatureActive(ShuffablePlaylistManager.class)? PlayerState.SHUFFLED : PlayerState.UNSHUFFLED);
	}	
}
