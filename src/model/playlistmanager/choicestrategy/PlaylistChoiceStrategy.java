package model.playlistmanager.choicestrategy;

import java.util.List;
import java.util.Optional;

/**
 * An object of a class that implements this interface define a strategy to
 * extract songs from a playlist
 * 
 * @author Matteo Gabellini
 *
 * @param <X> 
 * 		the elements type of the playlist
 */
public interface PlaylistChoiceStrategy<X> {
	
	
	/**
	 * a getter for the index of the current song
	 * @return the current song index in the play list
	 */
	Optional<Integer> getCurrentSongIndex();

	/**
	 * Take the next song
	 * 
	 * @param playlist
	 *            where the index is extracted
	 * @return the index of the next song or an empty optional if isn't possible
	 *         extract the next song
	 */
	Optional<Integer> getNextSong(List<X> playlist);

	
	/**
	 * Take the previous song
	 * 
	 * @param playlist
	 *            where the index is extracted
	 * @return the index of the previous song or an empty optional if isn't possible
	 *         extract the previous song
	 */
	Optional<Integer> getPreviousSong(List<X> playlist);
	
	/**
	 * Update the internal data structure if the user of the playlist goes directly to a song
	 * @param index
	 * 			the index of the song in the playlist
	 * @param playlist
	 * 			the playlist on which I want to use this strategy
	 */
	void goToSong(final int index, List<X> playlist);
	
	/**
	 * Update the internal strategy data structure when a index was removed from song 
	 * @param index
	 */
	void removedIndex(int index);
	
}
