package model.playlistmanager;

import java.util.Optional;

import model.playlistmanager.choicestrategy.ClassicStrategy;
import model.playlistmanager.choicestrategy.ShuffleStrategy;

/**
 * A Shuffable PlaylistManager is an object that add the manage of the shuffle
 * mode to a PlaylistManager
 * 
 * @author Matteo Gabellini
 *
 * @param <X>
 *            the elements type of the playlist
 */
public class ShuffablePlaylistFeature<X> implements PlaylistFeature<X> {
	
	private final Optional<PlaylistFeature<X>> nextHandler; 
	private boolean shuffleModeState;

	public ShuffablePlaylistFeature() {
		this.nextHandler = Optional.empty();
		this.shuffleModeState = false;
	}

	public ShuffablePlaylistFeature(final PlaylistFeature<X> nextHandler) {
		this.nextHandler = Optional.ofNullable(nextHandler);
		this.shuffleModeState = false;
	}

	@Override
	public void setFeatureState(final Class<?> featureClass,final PlaylistManager<X> plManager,
			final boolean active) {
		if (this.getClass().equals(featureClass)) {
			this.shuffleModeState = active;
			plManager.setChoiceStrategy(this.shuffleModeState ? new ShuffleStrategy<>()
					: new ClassicStrategy<>());
		} else {
			if(nextHandler.isPresent()){
				nextHandler.get().setFeatureState(featureClass,plManager, active);
			} else {
				new UnsupportedOperationException("This feature in unavailable");
			}
		}
	}

	@Override
	public boolean isFeatureActive(
			final Class<?> featureClass) {
		if (this.getClass().equals(featureClass)) {
			return this.shuffleModeState;
		} else {
			if(nextHandler.isPresent()){
				return nextHandler.get().isFeatureActive(featureClass);
			} else {
				new UnsupportedOperationException("There isn't any feauture that can handle this request");
				return false;
			}
		}

	}

}
