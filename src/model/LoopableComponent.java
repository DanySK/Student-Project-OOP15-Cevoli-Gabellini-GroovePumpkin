package model;
/**
 * A class that implements this interface has the loop management
 * 
 * @author Matteo Gabellini
 *
 */
public interface LoopableComponent {
	/**
	 * Able or disale the loop mode
	 * @param true if the loop must be activeted otherwise false
	 */
	void setLoopMode(final boolean value);
	
	/**
	 * Take the state of the loop mode
	 * @return true if loop mode is active otherwise false
	 */
	boolean isLoopModeActive();	
}
