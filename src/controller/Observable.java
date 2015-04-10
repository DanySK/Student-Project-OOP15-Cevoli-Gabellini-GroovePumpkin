package controller;

import View.Updatable;

public interface Observable {
	/**
	 * This method add an Updatable Observer
	 * @param component
	 */
	void addUpdatableObserver(final Updatable component);
}
