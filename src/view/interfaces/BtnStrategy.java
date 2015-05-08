package view.interfaces;

import javax.swing.ImageIcon;

/**
 * An extended Strategy class for buttons
 * 
 * @author Alessandro
 *
 * @param <C>  -> Controller
 * @param <B>  -> Button
 * @param <S>  -> Command
 */
public interface BtnStrategy<C, B, S> extends Strategy<C> {
	
	/**
	 * Return the image associated with the strategy
	 * 
	 * @return
	 */
	ImageIcon getImage();
	
	/**
	 * Return the title associated with this strategy
	 * 
	 * @return
	 */
	String getTitle();
	
	/**
	 * Update the status of the object
	 * 
	 * NOTE: that not a functional method, is similar to accept of BiConsumer
	 * 		because I wanted to inglobate this here instead of using a biconsumer
	 * 
	 */
	void updateUser(final B b, final S s);
}
