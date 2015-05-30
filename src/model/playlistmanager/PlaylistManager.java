package model.playlistmanager;

import java.util.List;
import java.util.Optional;

import model.playlistmanager.choicestrategy.PlaylistChoiceStrategy;

/**
 * A Playlist manager is an object that are able to manage all the aspect of a playlist
 * @author Matteo Gabellini
 *
 */
public interface PlaylistManager<X> {
	
	/**
	 * Set the strategy for chose the song from the playlist
	 * @param strategy
	 */
	void setChoiceStrategy(PlaylistChoiceStrategy<X> strategy);
	
	/**
	 * This method is a get accessor for the
	 * @return the current playList index of the current song
	 */
	Optional<Integer> getCurrentSongIndex();
	
	/**
	 * this method is a getter for the current song
	 * @return the current song
	 */
	Optional<X> getCurretSong();
	
	
	/**
	 * This method change the current song with the next song
	 * @return the X of the next song 
	 */
	Optional<X> changeToTheNextSong();

	/**
	 * This method change the current song with the previous song 
	 * @return the X of the previous song
	 */
	Optional<X> changeToThePreviousSong();
	
	/**
	 * This method change the current song with the song of the playlist specified by the index 
	 * @throws IllegalArgumentException if the index have a illegal value
	 * @return the X of the song
	 */
	Optional<X> changeSong(final int index) throws IllegalArgumentException;
	
	/**
	 * This method implements the logic for add the X of a audio file to the playlist
	 * @param songPath is the resource locator of the sound file
	 * @throws IllegalArgumentException if parameter is null
	 */
	void addSongToPlayList(final X songPath) throws IllegalArgumentException;
	
	/**
	 * This method implements the logic for remove a song from the playList
	 * @param index rapprent the position in the playlist
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	void removeSongFromPlayList(final int index) throws IllegalArgumentException;
	
	/**
	 * This method permits to load the playlist specified like parameter
	 * @param playList to load
	 * @throws IllegalArgumentException if parameter is null
	 */
	void loadPlayList(final List<X> playList) throws  IllegalArgumentException;
	
	/**
	 * This method return the current playlist
	 * @return a copy of the current playlist
	 */
	List<X> getPlayList();
	
	
	
}
