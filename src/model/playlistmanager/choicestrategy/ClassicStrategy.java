package model.playlistmanager.choicestrategy;

import java.util.List;
import java.util.Optional;
/**
 * A Classic strategy rappresent the classic mode for chose song from a playlist
 * 
 * @see PlaylistChoiceStrategy
 * @author Matteo Gabellini
 *
 * @param <X> the type of the elements that compose the playlist handled
 */

public class ClassicStrategy<X> implements PlaylistChoiceStrategy<X> {
	private static final int NOT_SELECTED = -1;
	private int currentIdx;	
	
	
	public ClassicStrategy(){
		this.currentIdx = NOT_SELECTED;
	}
	
	@Override
	public Optional<Integer> getNextSong(final List<X> playlist) {
		if (playlist == null || playlist.isEmpty() || this.currentIdx >= playlist.size()-1 || this.currentIdx == NOT_SELECTED) {
			return Optional.empty();
		} 
		return Optional.ofNullable(++this.currentIdx);
	}

	@Override
	public Optional<Integer> getPreviousSong(final List<X> playlist) {
		if (playlist == null || playlist.isEmpty() || this.currentIdx == 0 || this.currentIdx == NOT_SELECTED) {
			return Optional.empty();
		}		
		return Optional.ofNullable(--this.currentIdx);
	}

	@Override
	public void goToSong(final int index, final List<X> playlist) {
		this.currentIdx = index;
	}

	@Override
	public Optional<Integer> getCurrentSongIndex() {
		return this.currentIdx == NOT_SELECTED? Optional.empty(): Optional.of(this.currentIdx);
	}

	@Override
	public void removedIndex(final int index) {
		if (this.currentIdx == index) {
			this.currentIdx = NOT_SELECTED;
		} else if(this.currentIdx > index){
			this.currentIdx--;
		}
	}

}
