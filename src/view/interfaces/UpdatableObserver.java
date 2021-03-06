package view.interfaces;

import model.PlayerState;
/**
 * This interface create an observer for a controller that need to update himself
 * 
 * @author Alessandro
 *
 */
public interface UpdatableObserver {
	/**
	 * This method is called by the observed object to notify the observer
	 * 
	 */
	 void updateStatus(final PlayerState status);
}
