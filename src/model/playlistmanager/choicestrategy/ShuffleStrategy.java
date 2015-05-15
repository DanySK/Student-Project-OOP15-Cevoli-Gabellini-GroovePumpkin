package model.playlistmanager.choicestrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * This strategy rappresent the mode for chosing song randomly from a playlist
 * This strategy remember some extracted song, 
 * so the instances give the capability to return to a extract previous song
 *  
 * @author Matteo Gabellini
 * @author Alessandro Cevoli
 * @param <X> The type of the playlist elements
 */
public class ShuffleStrategy<X> implements PlaylistChoiceStrategy<X> {
	private static final int SHUFFLED_LIST_LIMIT = 50;
	
	private int currShuffledIdx = -1;
	private List<Integer> shuffled;
	private ExtractionStrategy<Integer> eStrategy;

	public ShuffleStrategy() {
		this.shuffled = new ArrayList<>();
	}

	/*
	 * This method reorganize the shuffled list delete the first half part of
	 * the list and move the elements in the second part at the begin of the
	 * list
	 */
	private void reorganizeShuffledList() {
		final int divisionIdx = SHUFFLED_LIST_LIMIT / 2;
		this.currShuffledIdx = this.currShuffledIdx/2;
		// Create the new list
		final List<Integer> newShuffledList = new ArrayList<>();
		// Copy all elements of the second part of the list
		for (int i = divisionIdx; i < shuffled.size(); i++) {
			newShuffledList.add(shuffled.get(i));
		}
		this.shuffled = newShuffledList;
	}	

	
	@Override
	public Optional<Integer> getCurrentSongIndex() {
		return Optional.ofNullable(this.shuffled.get(this.currShuffledIdx));
	}
	
	@Override
	public Optional<Integer> getNextSong(final List<X> playlist) {
		if (playlist == null || playlist.isEmpty()) {
			return Optional.empty();
		}		
		
		this.eStrategy = new ExtractionStrategy<Integer>() {
			public Integer getElement() {
				return new Random().nextInt(playlist.size()) % playlist.size();
			}
		};

		// call the method that implements the random choice of the song
		// the shuffled list is empty or the actual index is the last
		if (shuffled.size() == 0 || this.currShuffledIdx == shuffled.size() - 1) {
			if (shuffled.size() == SHUFFLED_LIST_LIMIT) {
				this.reorganizeShuffledList();
			}
			
			if (this.currShuffledIdx == -1) {
				shuffled.add(this.eStrategy.getElement());
			} else {
				// I take the next song and i controll that isn't equals to the
				// current song
				int nextSong = this.shuffled.get(this.currShuffledIdx);
				while (nextSong == this.shuffled.get(this.currShuffledIdx)) {
					nextSong = this.eStrategy.getElement();
				}
				// I get a new shuffled song
				shuffled.add(nextSong);
			}
			
			if(this.shuffled.size() >= SHUFFLED_LIST_LIMIT){
				this.reorganizeShuffledList();
			}
			
			currShuffledIdx = shuffled.size() - 1;
			return Optional.ofNullable(this.shuffled
					.get(this.currShuffledIdx));
		} else {
			// Otherwise i chose the next song from the list of the previous
			// chosen song
			return Optional.ofNullable(this.shuffled
					.get(++this.currShuffledIdx));
		}
	}

	@Override
	public Optional<Integer> getPreviousSong(final List<X> playlist) {
		if (playlist == null || playlist.isEmpty()) {
			return Optional.empty();
		}
		
		// take the previous song from the stack
		if (shuffled.isEmpty() || currShuffledIdx == 0) {
			// if the shuffled playlist is empty i'll do nothing or
			// If the currentShuffledIdx corresponding to the first song of the
			// shuffled playlist
			// I return an empty Optional
			return Optional.empty();
		} else {
			return Optional.ofNullable(this.shuffled
					.get(--this.currShuffledIdx));
		}
	}

	@Override
	public Optional<Integer> getSong(final int index, final List<X> playlist) {
		if (playlist == null || playlist.isEmpty() || index < 0
				|| index > playlist.size() - 1) {
			return Optional.empty();
		}

		this.shuffled.add(index);
		this.currShuffledIdx = shuffled.size() - 1;
		return Optional.ofNullable(this.shuffled
				.get(this.currShuffledIdx));
	}

	@Override
	public void removedIndex(final int index) {
		for(int i = 0; i < this.shuffled.size(); i++){
			if(this.shuffled.get(i) == index){
				//if the value of the i-th element is equal than index 
				this.shuffled.remove(i);
			} else if(this.shuffled.get(i) > index){
				//if the value is greater than the index that i remove
				this.shuffled.set(i,this.shuffled.get(index) - 1);
			}
		}
	}
}
