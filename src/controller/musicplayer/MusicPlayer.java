package controller.musicplayer;

import java.net.URL;
import java.util.List;
import java.util.Optional;

import controller.Observable;
import controller.Player;

/**
 * 
 * This interface represents the contract of the music player itself,
 * that is the part of the "Music Player" that deals with taking songs and start them
 * 
 * A music player is something that take different format song and reproduce
 * them 
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
	 * a getter for the duration of current song
	 * @return the duration in milliseconds of the current track
	 */
	double getDurationOfCurrentSong();
	
	/**
	 * This method is used for set the position in the current song
	 * @param the microsecond past from the begin of the song
	 * @throws IllegalArgumentException if the parameter time is out of the range of the song
	 */
	void setPosition(final int time) throws IllegalArgumentException;
	 
	
	/**
	 * This method implements the logic for add the URL of a audio file to the playlist
	 * @param songPath is the resource locator of the sound file
	 * @throws IllegalArgumentException if parameter is null
	 */
	void addSong(URL songPath) throws IllegalArgumentException;
	
	
	/**
	 * This method implements the logic for remove a song from the playList
	 * @param index rapprent the position in the playlist
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	void removeSong(int index) throws IllegalArgumentException;
	
	/**
	 * This method return the current playlist
	 * @return a copy of the current playlist
	 */
	List<URL> getPlayList();
}
