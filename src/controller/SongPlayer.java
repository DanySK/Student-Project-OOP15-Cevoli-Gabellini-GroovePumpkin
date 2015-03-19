package controller;

import Model.PlayerState;

/**
 * A interface that rapprent a SingleSongPlayer
 * (this interface is indipendent if the class that implements manage sampled sound or midi sound)
 * @author Matteo Gabellini
 *
 */
public interface SongPlayer{
	void play();
	
	/**
	 * This method stop the reproduction a close the AudioStream
	 */
	void stop();	
	void pause();
	
	/**
	 * @return the length of the track
	 */
	double getDuration();
	
	/**
	 * This method return the elapsed time from the begin of the song
	 * @return the elapsed second from the song's begin
	 */
	double getElapsedTime();
	
	PlayerState getState();
	
	/**
	 *Return true if the song is playing 
	 */
	boolean isActive();
	
}