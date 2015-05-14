package controller.musicplayer;
/**
 * This interface rappresent an object that manages the loop mode
 * @author Matteo Gabellini
 *
 */
public interface Loopable{
	/**
	 * Set the function of loop
	 * @param loopActive
	 * 			true - if the loop must be activeted
	 * 			false - if the loop must be deactiveted
	 */
	void setLoop(final boolean loopActive);
	
	/**
	 * Take the state of the loop mode
	 * @return true if loop mode is active otherwise false
	 */
	boolean isLoopModeActive();	
}
