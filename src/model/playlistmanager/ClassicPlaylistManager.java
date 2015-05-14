package model.playlistmanager;

import model.playlistmanager.choicestrategy.ClassicStrategy;
import model.playlistmanager.choicestrategy.PlaylistChoiceStrategy;

/**
 *  This class rapresent a classic playlist manager that use only the classic strategy for chose the next song
 * @author Matteo Gabellini
 *
 * @param <X>
 */
public class ClassicPlaylistManager<X> extends AbstractExtendedPlaylistManager<X> {
	
	public ClassicPlaylistManager() {
		super(new BasicPlaylistManager<>(new ClassicStrategy<>()));
	}
	
	public ClassicPlaylistManager(PlaylistManager<X> decorated) {
		super(decorated);
	}
	
	/**
	 * This method do nothing is used to block the possibility to change the
	 * strategy by the user, beacuse the classic playlist manager must have only
	 * the classic strategy
	 * 
	 * @throw {@link UnsupportedOperationException}
	 */
	@Override
	public void setChoiceStrategy(PlaylistChoiceStrategy<X> strategy) {
		throw new UnsupportedOperationException("The choice strategy changing is doing internally by this object");
	}
}
