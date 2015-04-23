package bbb;

import java.net.URL;
import java.util.List;
import java.util.Optional;

/**
 * This interface rappresent the model of a music player
 * @author Matteo Gabellini
 *
 */
public interface MusicPlayerModel {
	
	
	/**
	 * This method is a get accessor for the
	 * @return the current playList index of the current song
	 */
	Optional<Integer> getCurrentSongIndex();
	
	/**
	 * this method is a getter for the current song
	 * @return the current song
	 */
	Optional<URL> getCurretSong();
	
	/**
	 * A getter for the title of the current song
	 * @return a string that rappresent the title of the curent song
	 */
	String getCurrentTitle();
	
	/**
	 * This method implements the logic for choose the next song from the playlist
	 * This method doesn't change the current song, to do this use changeToTheNextSong()
	 * @return the resource locator of the next audiofile
	 */
	Optional<URL> getNextSong(); //this can implemented with a iterator logic
	
	/**
	 * This method implements the logic for choose the previous song from the playlist
	 * This method doesn't change the current song, to do this use changeToThePreviousSong()
	 * @return the resource locator of the previous audiofile
	 */
	Optional<URL> getPreviousSong();
	
	/**
	 * This method change the current song with the next song
	 * @retrun the URL of the next song 
	 */
	Optional<URL> changeToTheNextSong();

	/**
	 * This method change the current song with the previous song 
	 * @return the URL of the previous song
	 */
	Optional<URL> changeToThePreviousSong();
	
	/**
	 * This method change the current song with the song of the playlist specified by the index 
	 * @throws IllegalArgumentException if the index have a illegal value
	 * @return the URL of the song
	 */
	Optional<URL> changeSong(int index) throws IllegalArgumentException;
	
	/**
	 * This method set the shuffle mode
	 * 
	 * @param true if we want to active the shuffle mode or false for deactive if was be already activated
	 */
	void setShuffleMode(boolean active);
	
	boolean isShuffleModeActive();
	
	/**
	 * This method set the Loop mode that is active the song repeat
	 * 
	 * @param true if we want activate the loop mode or false for deactive if was be already activated
	 */
	void setLoopMode(boolean active);
	
	boolean isLoopModeActive();
	
	/**
	 * This method implements the logic for add the URL of a audio file to the playlist
	 * @param songPath is the resource locator of the sound file
	 * @throws IllegalArgumentException if parameter is null
	 */
	void addSongToPlayList(URL songPath) throws IllegalArgumentException;
	
	/**
	 * This method implements the logic for remove a song from the playList
	 * @param index rapprent the position in the playlist
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	void removeSongFromPlayList(int index) throws IllegalArgumentException;
	
	/**
	 * This method permits to load the playlist specified like parameter
	 * @param playList to load
	 * @throws IllegalArgumentException if parameter is null
	 */
	void loadPlayList(List<URL> playList) throws  IllegalArgumentException;
	
	/**
	 * This method return the current playlist
	 * @return a copy of the current playlist
	 */
	List<URL> getPlayList();
	
	
	
}
