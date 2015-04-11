package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.sound.sampled.*;
import javax.sound.midi.*;
import javax.swing.JComponent;

import Model.MusicPlayerModel;
import Model.MusicPlayerModelImpl;
import Model.PlayerState;
import Model.SingleSongPlayerState;
import Model.Utility;
import View.MusicPlayerPanel;
import View.Updatable;

/**
 * A music player is something that take different format song and reproduce them
 * @author Matteo Gabellini
 *
 */
public class MusicPlayerImpl implements MusicPlayer {

	private List<Updatable> view;

	private final MusicPlayerModel model;
	private Optional<SongPlayer> soundPlayer; // this field rappresents the
												// concrete track player
	/*
	 * Questa variabile contiene un oggeto di una classe anonima che implementa
	 * il comportamento per controllare quando la canzone termina perchè è stata
	 * riprodotta tutta
	 */
	private Thread threadSongWatcher;
	private volatile boolean endOfSong;
	
	
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
		if(view != null){
			view.stream().forEach(x -> x.updateStatus(state));
		}
	}

	private void changeSong(Optional<URL> song){
		if (song.isPresent()) {
			// Se la canzone indicata dall'indice è presente la carico

			SingleSongPlayerState preChangePlayerState = (this.soundPlayer
					.isPresent() ? this.soundPlayer.get().getState()
					: SingleSongPlayerState.STOPPED);

			this.loadSong(song.get());
			notifyToUpdatable(PlayerState.SONGCHANGED);
			// If before the song changing the song player was running...
			if (preChangePlayerState == SingleSongPlayerState.RUNNING) {
				// Start the reproduction of the new song
				this.play();
			}

		}
	}
	
	@Override
	public void goToNextSong() {		
		final Optional<URL> nextSong = this.model.changeToTheNextSong();
		if (nextSong.isPresent()) {
			this.changeSong(nextSong);
		}
	}

	@Override
	public void goToPreviousSong() {
		final Optional<URL> previousSong = this.model.changeToThePreviousSong();
		if (previousSong.isPresent()) {
			this.changeSong(previousSong);
		}
	}

	@Override
	public void goToSong(int index) {
		final Optional<URL> song = this.model.changeSong(index);
		if (song.isPresent()) {
			this.changeSong(song);
		}
	}	
	
	@Override
	public Optional<URL> getCurrentSong() {
		return this.model.getCurretSong();
	}
	
	/*
	 * ***********************************************
	public void loadSong(File song){
		try {
			this.loadSong(new URL(Utility.anURLPathBuilder(song.getAbsolutePath())));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	******************************************/
	
	
	@Override
	public void loadSong(URL songPath) {
		// Check if the player is present and if is active
		if (this.soundPlayer.isPresent() && this.soundPlayer.get().isActive()) {
			// if the player is active I stop them...
			//For the correct synchronization of threads i call the stop method of this class
			this.stop();
		}		
		
		AudioInputStream audioStream;
		final Sequence midiSequence;
		//Provo a caricare l'url come sequenza midi
		try {
			midiSequence = MidiSystem.getSequence(songPath);
			try {
				this.soundPlayer = Optional.of(new MidiSongPlayer(midiSequence));
			} catch (MidiUnavailableException e) {
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
		if (!this.soundPlayer.isPresent()) {
			// Se non è stato creato lo creo e gli passo la canzone corrente
			// della play list
			this.loadSong(this.model.getCurretSong().get());
		}
		
		try {
			//Provo ad avviare il lettore nel caso non fosse stato creato catturo con il catch l'eccezione
			this.soundPlayer.get().play();
			this.endOfSong = false;
			// Chiedo al lettore lo stato perchè dipende da esso e lo notifico e lo notifico
			notifyToUpdatable(soundPlayer.get().getState() == SingleSongPlayerState.RUNNING ? PlayerState.RUNNING
					: PlayerState.ERROR);

			if (this.soundPlayer.get().isActive()) {
				// Avvio un thread che controlla quando la canzone termina se
				// non è già attivo
				if (threadSongWatcher == null || !threadSongWatcher.isAlive()) {
					threadSongWatcher = new Thread() {
						@Override
						public void run() {
							System.out
									.println("Sono il song watcher e sono partito");

							while (soundPlayer.get().isActive()
									|| soundPlayer.get().getState() == SingleSongPlayerState.PAUSED) {
								// Finche la traccia è attiva stoppo
								// momentaneamente il
								// thread
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							
							//Controllo che non sia stato invocato lo stop dall'utente
							if(soundPlayer.get().getState() != SingleSongPlayerState.STOPPED){
								MusicPlayerImpl.this.endOfSong = true;
								MusicPlayerImpl.this.stop();
							}						
							
							System.out
									.println("Sono il song watcher e termino");
						}
					};
					threadSongWatcher.start();
				}
			}
		} catch (NoSuchElementException e) {
			notifyToUpdatable(PlayerState.ERROR);
		}
	}

	@Override
	public void stop() {
		if(this.soundPlayer.isPresent()){
			this.soundPlayer.get().stop();
			
			// Se la variabile endOfSong è false significa che lo stop è
			// stato invocato dall'utente
			if (!this.endOfSong) {
				// Attendo che termini il thread del song watcher
				try {
					this.threadSongWatcher.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				// Chiedo al lettore lo stato perchè dipende da esso e lo notifico
				notifyToUpdatable(this.soundPlayer.get().getState() == SingleSongPlayerState.STOPPED ? PlayerState.STOPPED
						: PlayerState.ERROR);
				this.soundPlayer = Optional.empty();
			} else {
				//Altrimenti se lo stop è stato invocato dal song watcher
				//Controllo se il loop è disattivato
				if(!this.model.isLoopModeActive()){
					//in tal caso passo alla canzone successiva
					this.goToNextSong();
				}
				//...e faccio partire la riproduzione
				this.play();				
			}
		} else {
			notifyToUpdatable(PlayerState.ERROR);
		}
	}

	@Override
	public void pause() {
		if (this.soundPlayer.isPresent()) {
			this.soundPlayer.get().pause();
			// Chiedo al lettore lo stato perchè dipende da esso e lo notifico
			notifyToUpdatable(soundPlayer.get().getState() == SingleSongPlayerState.PAUSED ? PlayerState.PAUSED
					: PlayerState.ERROR);
		}
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
	public int addSongs(String directoryPath) {
		// TODO Auto-generated method stub
		
		
		
		/////DA IMPLEMENTARE LO FACCIO APPENA LE RESTANTI FUNZIONI VANNO
		return 0;
	}
	
	@Override
	public void removeSong(int index) throws IllegalArgumentException {
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
