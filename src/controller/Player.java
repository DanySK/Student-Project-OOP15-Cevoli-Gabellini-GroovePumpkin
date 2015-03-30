package controller;

import View.Updatable;

/**
 * This interface rappresent a common player
 * @author Matteo Gabellini
 *
 */
public interface Player {
	void play();
	void pause();
	void stop();
	void setLoop(final boolean value);	
	
	/**
	 * This method add an Updatable object to the controller datastructure
	 * @param component
	 */
	void addUpdatableObserver(final Updatable component);
}
