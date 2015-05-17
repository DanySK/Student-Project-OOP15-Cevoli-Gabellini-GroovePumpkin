package view.model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * As for the abstract mouse listener listener class,
 * I've created this only to reduce code length eliminating
 * unused method.
 * 
 * @author Alessandro
 *
 */
public abstract class AbstractKeyListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		e.consume();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		e.consume();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		e.consume();
	}

}
