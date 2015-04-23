package controller;
/**
 * This interface rappresent a Player with the loop function
 * @author Matteo Gabellini
 *
 */
public interface LoopablePlayer extends Player {
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
