package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sound.sampled.*;
import javax.sound.midi.*;

import Model.MusicPlayerModel;
import Model.MusicPlayerModelImpl;
import Model.PlayerState;
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
	private Optional<SongPlayer> soundPlayer; //this field rappresents the concrete track player
	//private Optional<SongPlayerMidi>  midiSequencer; //this field rappresents the concrete midi sequencer
	
	public  MusicPlayerImpl() {
		this.model = new MusicPlayerModelImpl();
	}
	
	
	@Override
	public void addUpdatableObserver(final Updatable component) {
		//CODICE DA RISISTEMARE
		
		if(view == null){
			view = new ArrayList<>();
		}
		
		view.add(component);
	}

	@Override
	public void notifyToUpdatable(final PlayerState state) {
		//view.stream().forEach(x -> x.update(state));
	}
	
	@Override
	public void goToNextSong() {
		final Optional<URL> nextSong = this.model.changeToTheNextSong();;
		if(!(nextSong.isPresent())){
			//Se la prossima canzone è presente la carico
			this.loadSong(nextSong.get());
		}
	}


	@Override
	public void goToPreviousSong() {
		final Optional<URL> previousSong = this.model.changeToThePreviousSong();
		if(!(previousSong.isPresent())){
			//Se la prossima canzone è presente la carico
			this.loadSong(previousSong.get());
		}
	} 
	
	@Override
	public void goToSong(int index) {
		final Optional<URL> song = this.model.changeSong(index);
		if(!(song.isPresent())){
			//Se la canzone indicata dall'indice è presente la carico
			this.loadSong(song.get());
		}
	}	
	
	@Override
	public void loadSong(URL songPath) {	
		final AudioInputStream audioStream;
		try{
			audioStream = AudioSystem.getAudioInputStream(songPath);
			//if(MIDI)
			//this.soundPlayer = Optional.of(new MidiSongPlayer());
			this.soundPlayer = Optional.of(new SampledSongPlayer(audioStream));		
		} catch(UnsupportedAudioFileException uafe){
			uafe.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}


	@Override
	public void play() {
		soundPlayer.get().play();
		//Chiedo al lettore lo stato perchè dipende da esso
		notifyToUpdatable(PlayerState.RUNNING);
	}


	@Override
	public void stop() {
		soundPlayer.get().stop();
		//Chiedo al lettore lo stato perchè dipende da esso
		notifyToUpdatable(soundPlayer.get().getState());
	}


	@Override
	public void pause() {
		soundPlayer.get().pause();
		//Chiedo al lettore lo stato perchè dipende da esso
		notifyToUpdatable(soundPlayer.get().getState());
	}
}
