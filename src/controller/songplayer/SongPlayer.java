package controller.songplayer;

import controller.Player;
import model.SongPlayerState;

/**
 * A interface that rapprent a SingleSongPlayer
 * (this interface is indipendent if the class that implements manage sampled sound or midi sound)
 * @author Matteo Gabellini
 *
 */
public interface SongPlayer extends Player{
	
	/**
	 * This method is used for set the position in the song open in the clip
	 * @param the microsecond past from the begin of the song
	 * @throws IllegalArgumentException if the parameter time is out of the range of the song
	 */
	void setPosition(final int time) throws IllegalArgumentException;
	
	/**
	 * Obtain the length of the song, exspressed in second
	 * @return the length of the track 
	 */
	double getDuration();
	
	/**
	 * This method return the elapsed time from the begin of the song
	 * @return the elapsed second from the song's begin
	 */
	double getElapsedTime();
	
	SongPlayerState getState();
	
	/**
	 *Return true if the song is playing 
	 */
	boolean isActive();
	
}