package view.viewModel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * I've created this class to shorten the code in the other class.
 * I didn't need all those method and I don't like to see empty method where I'm doing 
 * an anonymous class 
 * 
 * @author Alessandro
 *
 */
public abstract class AbstractMouseListener implements MouseListener {
	
	/**
	 * Ovverride this method
	 */
	@Override
	public void mouseClicked(final MouseEvent e) {
		e.consume();
	}
	
	/**
	 * Ovverride this method
	 */
	@Override
	public void mousePressed(final MouseEvent e) {
		e.consume();
	}
	
	/**
	 * Ovverride this method
	 */
	@Override
	public void mouseReleased(final MouseEvent e) {
		e.consume();
	}
	
	/**
	 * Ovverride this method
	 */
	@Override
	public void mouseEntered(final MouseEvent e) {
		e.consume();
	}
	
	/**
	 * Ovverride this method
	 */
	@Override
	public void mouseExited(final MouseEvent e) {
		e.consume();

	}

}
