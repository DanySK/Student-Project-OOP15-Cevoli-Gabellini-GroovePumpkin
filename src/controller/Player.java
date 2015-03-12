package controller;

/**
 * This interface rappresent a common player
 * @author Matteo Gabellini
 *
 */
public interface Player {
	void play();
	void pause();
	void stop();
	void setLoop(boolean value);	
}
