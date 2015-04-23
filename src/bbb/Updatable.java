package bbb;

import model.PlayerState;
/**
 * This interface create an observer for a controller that need to update himself
 * 
 * @author Alessandro
 *
 */
public interface Updatable {
	/**
	 * This method is called by the obsverved object to notufy the observer
	 * 
	 */
	public void updateStatus(final PlayerState status);
}
