package controller;

import View.Updatable;

/**
 * This interface rappresent a common player
 * @author Matteo Gabellini
 *
 */
public interface Player {
	
	/**
	 * Is a command for start the reproduction
	 */
	void play();
	
	/**
	 * Is a command for suspend the reproduction
	 */
	void pause();
	
	/**
	 * Is a command for stop the reproduction
	 */
	void stop();
	
	/**
	 * Is a setter for the loop mode
	 * @param true if the loop mode must be activeted or false if must be deactiveted
	 */
	void setLoop(final boolean value);
	
	/**
	 * Is a setter for the gain of the player
	 * @param gainValue rappresent a value between 0 and 100
	 */
	void setVolume(double gainValue);
}
