package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.PlayerState;
/**
 * This class manage the observers that implements the interface Updatable
 * @author Matteo Gabellini
 *
 */
public class UpdatableObserversManager implements Observable {
	
	private List<Updatable> observers;
	
	@Override
	public void addUpdatableObservers(final Updatable... component) {
		if (observers == null) {
			observers = new ArrayList<>();
		}
		Arrays.asList(component).stream().forEach(X -> observers.add(X));
	}

	/**
	 * This method notify a player state to the added observer
	 */
	protected void notifyToUpdatable(final PlayerState state) {
		if (observers != null) {
			observers.stream().forEach(x -> x.updateStatus(state));
		}
	}

}
