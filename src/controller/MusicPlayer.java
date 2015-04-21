package controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;

import Model.PlayerState;
import View.Updatable;

/**
 * 
 * This interface represents the contract of the music player itself,
 * that is the part of the "Music Player" that deals with taking songs and start them
 * @author Matteo Gabellini
 *
 */
public interface MusicPlayer extends Player, Observable{
	
	
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
	 * a getter for the current song
	 * @return An optional that contains the song that will be played if will be call the method play
	 */
	Optional<URL> getCurrentSong();
	
	/**
	 * a getter for the time past from the begin of the song
	 * @return the elapsed second
	 */
	double getElapsedTime();
	
	/**
	 * Is a setter for the loop mode
	 * @param true if the loop mode must be activeted or false if must be deactiveted
	 */
	void setLoop(final boolean value);
	
	/**
	 * This method set the shuffle mode
	 * 
	 * @param true if we want to active the shuffle mode or false for deactive if was be already activated
	 */
	void setShuffleMode(boolean active);
	
	boolean isShuffleModeActive();
	
	boolean isLoopModeActive();	
	
	/**
	 * This method implements the logic for add the URL of a audio file to the playlist
	 * @param songPath is the resource locator of the sound file
	 * @throws IllegalArgumentException if parameter is null
	 */
	void addSong(URL songPath) throws IllegalArgumentException;
	
	/**
	 * A scanner for a directory hierarchy, scan the directory, subdirectory and add all the Midi e Wav song found
	 * @param directoryPath is the path of the directory where start the search of the song
	 * @return number of song found
	 */
	int addSongs(String directoryPath);
	
	/**
	 * This method implements the logic for remove a song from the playList
	 * @param index rapprent the position in the playlist
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	void removeSong(int index) throws IllegalArgumentException;
	
	/**
	 * This method permits to load the playlist specified like parameter
	 * @param playList to load
	 * @throws IllegalArgumentException if parameter is null
	 */
	void loadPlayList(List<URL> playList) throws  IllegalArgumentException;
	
	/**
	 * Getter for the current state of the Player
	 * @return current player state
	 */
	PlayerState getState();
	
	/**
	 * This method return the current playlist
	 * @return a copy of the current playlist
	 */
	List<URL> getPlayList();
}
