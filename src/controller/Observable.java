package controller;

import view.interfaces.UpdatableObserver;


/**
 * This interface rappresent an object that can be observed by a an object that
 * implements the interface Updatable
 * 
 * @author Matteo Gabellini
 *
 */
public interface Observable {
	/**
	 * This method add an Updatable Observer
	 * @param component
	 */
	void addUpdatableObservers(final UpdatableObserver... component);
}
