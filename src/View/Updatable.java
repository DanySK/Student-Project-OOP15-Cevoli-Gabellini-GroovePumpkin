package View;

import Model.PlayerState;
/**
 * This interface create an observer for a controller that need to update himself
 * 
 * 
 * @author Alessandro
 *
 */
public interface Updatable {
	/**
	 */
	public void updateStatus(final PlayerState status);
}
