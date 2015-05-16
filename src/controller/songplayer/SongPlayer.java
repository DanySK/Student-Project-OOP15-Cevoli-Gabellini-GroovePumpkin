package controller.songplayer;

import controller.Player;
import model.SongPlayerState;

/**
 * A interface that rapprent a SingleSongPlayer
 * (this interface is indipendent if the class that implements manage sampled sound or midi sound)
 * @author Matteo Gabellini
 *
 */
public interface SongPlayer extends Player, SongInfosManager{
	
	SongPlayerState getState();
	
	/**
	 *Return true if the song is playing 
	 */
	boolean isActive();
	
}