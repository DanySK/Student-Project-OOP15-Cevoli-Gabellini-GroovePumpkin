package model.playlistmanager.choicestrategy;

import java.util.List;
import java.util.Optional;
/**
 * A Classic strategy rappresent the classic mode for chose song from a playlist
 * 
 * @author Matteo Gabellini
 *
 * @param <X> the type of the elements that compose the playlist handled
 */

public class ClassicStrategy<X> implements PlaylistChoiceStrategy<X> {
	private int currentIdx;	
	
	
	public ClassicStrategy(){
		this.currentIdx = -1;
	}
	
	@Override
	public Optional<Integer> getNextSong(final List<X> playlist) {
		if (playlist == null || playlist.isEmpty() || this.currentIdx >= playlist.size()-1) {
			return Optional.empty();
		} 
		return Optional.ofNullable(++this.currentIdx);
	}

	@Override
	public Optional<Integer> getPreviousSong(final List<X> playlist) {
		if (playlist == null || playlist.isEmpty() || this.currentIdx == 0) {
			return Optional.empty();
		}		
		return Optional.ofNullable(--this.currentIdx);
	}

	@Override
	public Optional<Integer> getSong(final int index, final List<X> playlist) {
		if (playlist == null || playlist.isEmpty() || index < 0
				|| index > playlist.size() - 1) {
			return Optional.empty();
		}
		this.currentIdx = index;
		return Optional.ofNullable(this.currentIdx);
	}

	@Override
	public Optional<Integer> getCurrentSongIndex() {
		return this.currentIdx == -1? Optional.empty(): Optional.of(this.currentIdx);
	}

	@Override
	public void removedIndex(final int index) {
		if (this.currentIdx == index) {
			this.currentIdx = -1;
		}
	}

}
