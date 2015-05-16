package model.playlistmanager;

import java.util.List;
import java.util.Optional;

import model.playlistmanager.choicestrategy.PlaylistChoiceStrategy;

/**
 * The base of an extended playlist manager
 * 
 * @author Matteo Gabellini
 *
 * @param <X>
 */
public abstract class AbstractExtendedPlaylistManager<X> implements ExtendedPlaylistManager<X>{
	
	protected final PlaylistManager<X> decorated;
	
	protected AbstractExtendedPlaylistManager(final PlaylistManager<X> decorated) {
		this.decorated = decorated;
	}
	
	@Override
	public void setChoiceStrategy(final PlaylistChoiceStrategy<X> strategy) {
		this.decorated.setChoiceStrategy(strategy);
	}

	@Override
	public Optional<Integer> getCurrentSongIndex() {
		return this.decorated.getCurrentSongIndex();
	}

	@Override
	public Optional<X> getCurretSong() {
		return this.decorated.getCurretSong();
	}

	@Override
	public Optional<X> changeToTheNextSong() {
		return this.decorated.changeToTheNextSong();
	}

	@Override
	public Optional<X> changeToThePreviousSong() {
		return this.decorated.changeToThePreviousSong();
	}

	@Override
	public Optional<X> changeSong(final int index) throws IllegalArgumentException {
		return this.decorated.changeSong(index);
	}

	@Override
	public void addSongToPlayList(final X songPath) throws IllegalArgumentException {
		this.decorated.addSongToPlayList(songPath);
	}

	@Override
	public void removeSongFromPlayList(final int index)
			throws IllegalArgumentException {
		this.decorated.removeSongFromPlayList(index);
	}

	@Override
	public void loadPlayList(final List<X> playList) throws IllegalArgumentException {
		this.decorated.loadPlayList(playList);
	}

	@Override
	public List<X> getPlayList() {
		return this.decorated.getPlayList();
	}

	public void setFeatureState(final Class<? extends PlaylistFeatures> featureClass,final boolean active){
		if(this.decorated instanceof PlaylistFeatures){
			((PlaylistFeatures) this.decorated).setFeatureState(featureClass,active);
		} else {
			throw new UnsupportedOperationException("The playlist manager of this instance haven't this feature");
		}
	}

	public boolean isFeatureActive(final Class<? extends PlaylistFeatures> featureClass){
		if(this.decorated instanceof PlaylistFeatures){
			return ((PlaylistFeatures) this.decorated).isFeatureActive(featureClass);
		}
		throw new UnsupportedOperationException("The playlist manager haven't this feature");		
	}
}
