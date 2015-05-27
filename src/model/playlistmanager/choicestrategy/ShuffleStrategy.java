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
	private static final int NOT_SELECTED = -1;
	
	private int currShuffledIdx = NOT_SELECTED;
	private final ShuffledListManager listManager;

	public ShuffleStrategy() {
		this.listManager = new ShuffledListManager();
	}	

	
	@Override
	public Optional<Integer> getCurrentSongIndex() {
		return this.currShuffledIdx == NOT_SELECTED ? Optional.empty()
				: Optional.ofNullable(this.listManager.getElement(this.currShuffledIdx));
	}
	
	@Override
	public Optional<Integer> getNextSong(final List<X> playlist) {
		if (playlist == null || playlist.isEmpty()) {
			return Optional.empty();
		}		
		
		final ExtractionStrategy<Integer> eStrategy = new ExtractionStrategy<Integer>() {
			public Integer getElement() {
				return (new Random().nextInt(playlist.size()) *new Random().nextInt(playlist.size()))% playlist.size();
			}
		};

		// call the method that implements the random choice of the song
		// the shuffled list is empty or the actual index is the last
		if (this.listManager.getSize() == 0 || this.currShuffledIdx == this.listManager.getSize() - 1) {
			
			if (this.currShuffledIdx == NOT_SELECTED) {
				this.listManager.addElement(eStrategy.getElement());
			} else {
				// I take the next song and i controll that isn't equals to the
				// current song
				int nextSong = eStrategy.getElement();
				while (nextSong == this.listManager.getElement(this.currShuffledIdx)) {
					nextSong = eStrategy.getElement();
				}
				// I get a new shuffled song
				this.listManager.addElement(nextSong);
			}
			
			currShuffledIdx = this.listManager.getSize() - 1;
			return Optional.ofNullable(this.listManager.getElement(this.currShuffledIdx));
		} else {
			// Otherwise i chose the next song from the list of the previous
			// chosen song
			if(this.currShuffledIdx == NOT_SELECTED){
				this.currShuffledIdx = -1;
			}
			return Optional.ofNullable(this.listManager.getElement(++this.currShuffledIdx));
		}
	}

	@Override
	public Optional<Integer> getPreviousSong(final List<X> playlist) {
		if (playlist == null || playlist.isEmpty() || this.listManager.listIsEmpty() || currShuffledIdx == 0) {
			return Optional.empty();
		} else {
			return Optional.ofNullable(this.listManager.getElement(--this.currShuffledIdx));
		}
	}

	@Override
	public void goToSong(final int index, final List<X> playlist) {
		this.listManager.addElement(index);
		this.currShuffledIdx = this.listManager.getSize() - 1;
	}

	@Override
	public void removedIndex(final int index){
		if(this.getCurrentSongIndex().isPresent()){ 
			if (this.getCurrentSongIndex().get() == index) {
				this.currShuffledIdx = NOT_SELECTED;
			} else {
				for (int i = 0; i < this.currShuffledIdx; i++) {
					if (this.listManager.getElement(i) == index) {
						this.currShuffledIdx--;
					}
				}
			}
		}
		this.listManager.removeElement(index);
	}
	
	
	/*
	 * This class manages the list of the shuffled index extract by the outer strategy
	 * When the internal list will be reorganize this class update also the field of the outer class 
	 * currShuffledIdx
	 * @author Matteo Gabellini
	 *
	 */
	private class ShuffledListManager{
		private static final int SHUFFLED_LIST_LIMIT = 50;
		
		private List<Integer> shuffled;
		
		
		public ShuffledListManager(){
			this.shuffled = new ArrayList<>();
		}
		
		public void addElement(final int index){
			shuffled.add(index);
			if (shuffled.size() == SHUFFLED_LIST_LIMIT) {
				this.reorganizeShuffledList();
			}			
		}
		
		public Integer getElement(final int index){
			return this.shuffled.get(index);
		}
		
		public int getSize(){
			return this.shuffled.size();
		}
		
		public boolean listIsEmpty(){
			return this.shuffled.isEmpty();
		}
		
		/*
		 * This method reorganize the shuffled list delete the first half part of
		 * the list and move the elements in the second part at the begin of the
		 * list
		 */
		private void reorganizeShuffledList() {
			final int divisionIdx = SHUFFLED_LIST_LIMIT / 2;
			ShuffleStrategy.this.currShuffledIdx = ShuffleStrategy.this.currShuffledIdx/2;
			// Create the new list
			final List<Integer> newShuffledList = new ArrayList<>();
			// Copy all elements of the second part of the list
			for (int i = divisionIdx; i < shuffled.size(); i++) {
				newShuffledList.add(shuffled.get(i));
			}
			this.shuffled = newShuffledList;
		}
		
		public void removeElement(final int index) {
			this.shuffled.removeIf(X -> X == index);
			for (int i = 0; i < this.shuffled.size(); i++) {
				if (this.shuffled.get(i) > index) {
					this.shuffled.set(i, this.shuffled.get(i) - 1);
				}
			}
		}
	}
}
