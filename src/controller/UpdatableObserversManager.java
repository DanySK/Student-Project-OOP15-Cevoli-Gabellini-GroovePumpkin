package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import view.interfaces.UpdatableObserver;
import model.PlayerState;
/**
 * This class manage the observers that implements the interface Updatable
 * 
 * This class can be extended from a class that must be oberved from an Updatable object
 * @author Matteo Gabellini
 *
 */
public class UpdatableObserversManager implements Observable {
	
	private final List<UpdatableObserver> observers;
	
    public UpdatableObserversManager() {
		this.observers = new ArrayList<>();
	}
	
	@Override
	public void addUpdatableObservers(final UpdatableObserver... component) {
		Arrays.asList(component).stream().forEach(X -> observers.add(X));
	}

	/**
	 * This method notify a player state to the added observer
	 * This method should only be used by classes that extend this 
	 * and can't be used externally
	 */
	protected void notifyToUpdatable(final PlayerState state) {
		observers.stream().forEach(x -> x.updateStatus(state));
	}

}
