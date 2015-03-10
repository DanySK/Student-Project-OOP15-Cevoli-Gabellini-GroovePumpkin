package View;

import Model.PlayerState;

public interface Updatable {
	/**
	 * 
	 * 
	 * @return
	 */
	public boolean updateStatus(final PlayerState status);
}
