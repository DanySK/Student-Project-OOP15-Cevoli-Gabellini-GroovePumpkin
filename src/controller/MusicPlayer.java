package controller;

import java.net.URL;

import Model.PlayerState;
import View.Updatable;

/**
 * questa interfaccia rappresenta il contrattore del lettore musicale vero e proprio, 
 * cioè quella parte del sistema "Lettore musicale" che si occupa di prendere i brani ed avviarli
 * @author Matteo Gabellini
 *
 */
public interface MusicPlayer {
	
	/**
	 * This method add an Updatable object to the controller datastructure
	 * @param component
	 */
	void addUpdatableObserver(final Updatable component);

	/**
	 * This method notify to all added Updatable object the changed status of the controller
	 */
	void notifyToUpdatable(final PlayerState state);
	
	/**
	 * this method load the next song
	 */
	void goToNextSong();
	
	/**
	 * this method load the previous song
	 */
	void goToPreviousSong();
	
	/**
	 * this method load the song of the playlist specified by the index
	 * @param index
	 */
	void goToSong(int index);
	
	/**
	 * This method take the URL of the song and try to load the track
	 * @param songPath
	 */
	void loadSong(URL songPath);
	
	
	/**
	 * 
	 */
	void play();
	void stop();
	void pause();
}
