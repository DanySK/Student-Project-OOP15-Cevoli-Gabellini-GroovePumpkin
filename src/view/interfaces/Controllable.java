package view.interfaces;

/**
 * Interface for a controllable object
 * 
 * @author Alessandro
 *
 * @param <C>
 */
public interface Controllable<C> {
	
	/**
	 * 
	 * @return the controller associated with this object
	 */
	C getController();
	
	/**
	 * Attach a new controller to this object
	 * 
	 * @param c
	 */
	void setController(final C c);
}
