package controller.songplayer;

import controller.Player;
import model.SongPlayerState;

/**
 * A interface that represent a SingleSongPlayer
 * (this interface is independent if the class that implements manages sampled sound or midi sound)
 * 
 * @author Matteo Gabellini
 *
 */
public interface SongPlayer extends Player, SongInfosManager{
	
	/**
	 * Take the state
	 * @return the song player state
	 */
	SongPlayerState getState();
	
	/**
	 *Return true if the song is playing 
	 */
	boolean isActive();
	
}