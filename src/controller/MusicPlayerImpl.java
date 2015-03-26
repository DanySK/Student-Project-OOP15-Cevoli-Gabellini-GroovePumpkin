package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sound.sampled.*;
import javax.sound.midi.*;
import javax.swing.JComponent;

import Model.MusicPlayerModel;
import Model.MusicPlayerModelImpl;
import Model.PlayerState;
import Model.SingleSongPlayeState;
import View.MusicPlayerPanel;
import View.Updatable;

/**
 * 
 * @author Matteo Gabellini
 *
 */
public class MusicPlayerImpl implements MusicPlayer {

	private List<Updatable> view;

	private final MusicPlayerModel model;
	private Optional<SongPlayer> soundPlayer; // this field rappresents the
												// concrete track player

	private Optional<MidiSongPlayer> midiSequencer; //this field rappresents the concrete midi sequencer
	
	public MusicPlayerImpl() {
		this.model = new MusicPlayerModelImpl();
		this.soundPlayer = Optional.empty();
	}

	@Override
	public void addUpdatableObserver(final Updatable component) {
		// CODICE DA RISISTEMARE

		if (view == null) {
			view = new ArrayList<>();
		}
		view.add(component);
	}

	private void notifyToUpdatable(final PlayerState state) {
		view.stream().forEach(x -> x.updateStatus(state));
	}

	@Override
	public void goToNextSong() {
		//Check if the player is present...
		if(this.soundPlayer.isPresent()){
			//if the player is present I stop them...
			this.soundPlayer.get().stop();			
		}
		
		final Optional<URL> nextSong = this.model.changeToTheNextSong();
		
		if ((nextSong.isPresent())) {
			SingleSongPlayeState preChangePlayerState = this.soundPlayer.get().getState();
			// Se la canzone successiva è presente la carico
			this.loadSong(nextSong.get());
			
			//If before the song changing the song player was running...
			if(preChangePlayerState == SingleSongPlayeState.RUNNING){
				//Start the reproduction of the new song
				this.play();
			}
		}
	}

	@Override
	public void goToPreviousSong() {
		final Optional<URL> previousSong = this.model.changeToThePreviousSong();
		if (!(previousSong.isPresent())) {
			SingleSongPlayeState preChangePlayerState = this.soundPlayer.get().getState();
			// Se la canzone precedente è presente la carico
			this.loadSong(previousSong.get());
			
			//If before the song changing the song player was running...
			if(preChangePlayerState == SingleSongPlayeState.RUNNING){
				//Start the reproduction of the new song
				this.play();
			}
		}
	}

	@Override
	public void goToSong(int index) {
		final Optional<URL> song = this.model.changeSong(index);
		if (!(song.isPresent())) {
			SingleSongPlayeState preChangePlayerState = this.soundPlayer.get().getState();
			// Se la canzone indicata dall'indice è presente la carico
			this.loadSong(song.get());
			
			//If before the song changing the song player was running...
			if(preChangePlayerState == SingleSongPlayeState.RUNNING){
				//Start the reproduction of the new song
				this.play();
			}
			
		}
	}

	
	@Override
	public URL getCurrentSong() {
		return this.model.getCurretSong().get();
	}
	
	
	
	@Override
	public void loadSong(URL songPath) {
		AudioInputStream audioStream;
		final Sequence midiSequence;
		//Provo a caricare l'url come sequenza midi
		try {
			midiSequence = MidiSystem.getSequence(songPath);
			try {
				this.soundPlayer = Optional.of(new MidiSongPlayer(midiSequence));
			} catch (MidiUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InvalidMidiDataException e1) {
			//Nel caso non sia una sequenza midi provo a caricarla come file audio
			try {		
				audioStream = AudioSystem.getAudioInputStream(songPath);
				
				//System.out.println("Before converting " + audioStream.getFormat());
				
				//Controllo se il file che ho caricato è in codifica PCM floating point
				if(audioStream.getFormat().getEncoding() == AudioFormat.Encoding.PCM_FLOAT){
					//Convert in the correct audio format
					audioStream = AudioSystem.getAudioInputStream(new AudioFormat(
							AudioFormat.Encoding.PCM_SIGNED,
							audioStream.getFormat().getSampleRate(),
							24,
							audioStream.getFormat().getChannels(),
							6,
							audioStream.getFormat().getFrameRate(),false), audioStream);
				}
				
				try {
					this.soundPlayer = Optional.of(new SampledSongPlayer(audioStream));
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
	public void play() {
		// Controllo se soundPlayer non è stato istanziato
		if (!soundPlayer.isPresent()) {
			// Se non è stato creato lo creo e gli passo la canzone corrente
			// della play list
			this.loadSong(this.model.getCurretSong().get());
		}
		soundPlayer.get().play();
		// Chiedo al lettore lo stato perchè dipende da esso
		notifyToUpdatable(soundPlayer.get().getState() == SingleSongPlayeState.RUNNING ? PlayerState.RUNNING : PlayerState.ERROR);
	}

	@Override
	public void stop() {
		soundPlayer.get().stop();
		// Chiedo al lettore lo stato perchè dipende da esso
		notifyToUpdatable(soundPlayer.get().getState() == SingleSongPlayeState.STOPPED ? PlayerState.STOPPED : PlayerState.ERROR);
		this.soundPlayer = Optional.empty();
	}

	@Override
	public void pause() {
		soundPlayer.get().pause();
		// Chiedo al lettore lo stato perchè dipende da esso
		notifyToUpdatable(soundPlayer.get().getState() == SingleSongPlayeState.PAUSED ? PlayerState.PAUSED : PlayerState.ERROR);
	}

	@Override
	public void setLoop(boolean value) {
		model.setLoopMode(value);
	}

	@Override
	public void setShuffleMode(boolean active) {
		this.model.setShuffleMode(active);
	}

	@Override
	public boolean isShuffleModeActive() {
		return this.model.isShuffleModeActive();
	}

	@Override
	public boolean isLoopModeActive() {
		return this.model.isLoopModeActive();
	}

	@Override
	public void addSong(URL songPath) throws IllegalArgumentException {
		this.model.addSongToPlayList(songPath);
		notifyToUpdatable(PlayerState.RELOAD);
	}

	@Override
	public void removeSong(int index) throws IllegalArgumentException {
		// Se la canzone che voglio togliere e quella che sto riproducendo
		// blocco la riproduzione
		if (this.model.getCurrentSongIndex().isPresent()
				&& this.model.getCurrentSongIndex().get() == index) {
			this.soundPlayer.get().stop();
		}
		this.model.removeSongFromPlayList(index);
		notifyToUpdatable(PlayerState.RELOAD);
	}

	@Override
	public void loadPlayList(List<URL> playList)
			throws IllegalArgumentException {
		this.model.loadPlayList(playList);
	}

	@Override
	public List<URL> getPlayList() {
		return this.model.getPlayList();
	}

	@Override
	public double getElapsedTime() {
		if(this.soundPlayer.get().isActive()){
			return this.soundPlayer.get().getElapsedTime();
		}
		return 0;
	}
}
