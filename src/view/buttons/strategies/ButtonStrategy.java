package view.buttons.strategies;

import javax.swing.ImageIcon;
import model.PlayerState;

/**
 * An extended Strategy class for buttons
 * 
 * @author Alessandro
 *
 * @param <C>  
 * @param <B>
 */
public interface ButtonStrategy<C, B> extends Strategy<C> {
	
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
	public void update(final B b, final PlayerState s);
}
