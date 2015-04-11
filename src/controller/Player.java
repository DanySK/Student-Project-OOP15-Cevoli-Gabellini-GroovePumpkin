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
}
