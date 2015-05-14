package model;

/**
 * This enum rappresent all possible state that a player can assume
 * A player may assume more than one state at the same time
 * @author Matteo Gabellini
 *
 */

public enum PlayerState {
	RUNNING, PAUSED, STOPPED, 
	ERROR, SONGCHANGED, RELOAD, 
	REMOVED, SHUFFLED, UNSHUFFLED, 
	LOOPED, UNLOOPED;
}
