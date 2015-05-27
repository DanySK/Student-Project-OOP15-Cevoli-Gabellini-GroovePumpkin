package controller.musicplayer;

import java.net.URL;
import java.util.List;
import java.util.Optional;

import controller.Observable;
import controller.Player;
import controller.songplayer.SongInfosManager;

/** 
 * A music player is something that take different format song from a playlist and reproduce
 * them 
 * 
 * This interface extends the interface {@link controller.Player} and {@link controller.Observable}
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
	void goToSong(int index) throws IllegalArgumentException;
	
	
	/**
	 * a getter for the current song
	 * @return An optional that contains the song that will be played if will be call the method play
	 */
	Optional<URL> getCurrentSong();

	/**
	 * Take the informations of the possible current song in reproduction
	 * 
	 * @return An optional with the information of the song in reproduction
	 *         otherwise an Optional.empty() if there isn't
	 */
	Optional<SongInfosManager> getCurrentSongInfosManager();
	 
	
	/**
	 * This method implements the logic for add the URL of a audio file to the playlist
	 * @param songPath is the resource locator of the sound file
	 * @throws IllegalArgumentException if parameter is null
	 */
	void addSong(URL songPath) throws IllegalArgumentException;
	
	
	/**
	 * This method implements the logic for remove a song from the playList
	 * @param indexes rapprent the position in the playlist
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	void removeSong(int... indexes) throws IllegalArgumentException;
	
	/**
	 * This method return the current playlist
	 * @return a copy of the current playlist
	 */
	List<URL> getPlayList();	
}
