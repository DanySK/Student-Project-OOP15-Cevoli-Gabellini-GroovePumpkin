package view.buttons.strategies;

import javax.swing.ImageIcon;
import model.PlayerState;

public interface ButtonStrategy<T, B> extends Strategy<T> {
	
	/**
	 * Return the image associated with the strategy
	 * 
	 * @return
	 */
	public ImageIcon getImage();
	
	/**
	 * Return the title associated with this strategy
	 * 
	 * @return
	 */
	public String getTitle();
	
	/**
	 * Update the status of the object
	 * 
	 * NOTE: that a not functional method, is similar to accept of BiConsumer
	 * 		because a wanted to inglobate this here instead of using a biconsumer
	 * 
	 */
	public void update(final B button, final PlayerState status);
}
