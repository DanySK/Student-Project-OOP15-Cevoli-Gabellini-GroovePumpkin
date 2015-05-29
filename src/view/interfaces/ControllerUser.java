package view.interfaces;

/**
 * Interface that represents an object able to use a controller
 * 
 * @author Alessandro
 *
 * @param <C>
 */
public interface ControllerUser<C> {
	
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
