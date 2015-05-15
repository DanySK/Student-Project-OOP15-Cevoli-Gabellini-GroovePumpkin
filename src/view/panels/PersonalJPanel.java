package view.panels;

import static view.config.Configuration.DARK_GRAY;
import static view.config.Configuration.WHITE;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.PlayerState;
import controller.Observable;
import controller.Updatable;

/**
 * A Personalized White&DarkGrey JPanel, with a personalizable Layout
 * 
 * @author Alessandro
 *
 */

public class PersonalJPanel extends JPanel implements Updatable, Observable{

	private static final long serialVersionUID = -7098020132793828090L;

	private final List<Updatable> observers = new ArrayList<>();

	/**
	 * A basic constructor for a White&Gray panel
	 * 
	 */
	public PersonalJPanel() {
		super();
		this.setBackground(WHITE);
		this.setForeground(DARK_GRAY);

		for (final KeyListener kl : this.getKeyListeners()) {
			this.removeKeyListener(kl);
		}
	}

	/**
	 * A basic constructor for a White&Gray panel with the given layout
	 * 
	 * @param layout
	 */
	public PersonalJPanel(final LayoutManager layout) {
		this();
		this.setLayout(layout);
	}

	/**
	 * Add new components for this object
	 * 
	 * @param cmp
	 */
	public void addComponents(final Component... cmp) {
		for (final Component c : cmp) {
			if(c!=null){
				this.add(c);
			}
		}
	}

	/**
	 * Add new Observers for this object
	 * 
	 * @param up
	 */
	public void addUpdatableObservers(final Updatable... up) {
		for (final Updatable u : up) {
			observers.add(u);
		}
	}

	/**
	 * Remove an Observer for this object
	 * 
	 * @param up
	 */
	public void removeObserver(final Updatable up) {
		observers.remove(up);
	}

	/**
	 * Get all the Observers of this object
	 * 
	 * @return
	 */
	public List<Updatable> getObservers() {
		return observers;
	}

	@Override
	public void updateStatus(final PlayerState status) {
		observers.stream().forEach(u -> u.updateStatus(status));
	}

}