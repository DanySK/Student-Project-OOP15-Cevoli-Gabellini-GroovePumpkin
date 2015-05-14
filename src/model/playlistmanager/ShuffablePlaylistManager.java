package model.playlistmanager;

import model.playlistmanager.choicestrategy.ClassicStrategy;
import model.playlistmanager.choicestrategy.PlaylistChoiceStrategy;
import model.playlistmanager.choicestrategy.ShuffleStrategy;

/**
 * A Shuffable PlaylistManager is an object that add the manage of the shuffle mode to a PlaylistManager
 * @author Matteo Gabellini
 *
 * @param <X> the elements type of the playlist
 */
public class ShuffablePlaylistManager<X> extends AbstractExtendedPlaylistManager<X>{
	
	private boolean shuffleModeState;
	
	public ShuffablePlaylistManager() {
		super(new BasicPlaylistManager<>(new ClassicStrategy<X>()));
		this.shuffleModeState = false;
	}
	
	public ShuffablePlaylistManager(PlaylistManager<X> decorated) {
		super(decorated);
		this.shuffleModeState = false;
	}
	
	/**
	 * This method do nothing is used to block the possibility to change the
	 * strategy by the user beacuse the changing of the strategy is doing
	 * internaly
	 * 
	 * @throw {@link UnsupportedOperationException}
	 */
	@Override
	public void setChoiceStrategy(PlaylistChoiceStrategy<X> strategy) {
		throw new UnsupportedOperationException("The choice strategy changing is doing internally by this object");
	}

	@Override
	public void setFeatureState(Class<? extends PlaylistFeatures> featureClass, boolean active) {
		if (this.getClass().equals(featureClass)) {
			this.shuffleModeState = active;
			super.setChoiceStrategy(this.shuffleModeState ? new ShuffleStrategy<>()
					: new ClassicStrategy<>());
		} else {
			super.setFeatureState(featureClass, active);
		}
	}

	@Override
	public boolean isFeatureActive(Class<? extends PlaylistFeatures> featureClass) {
		if (this.getClass().equals(featureClass)) {
			return this.shuffleModeState;
		} else {
			return super.isFeatureActive(featureClass);
		}
		
	}

	
	
}
