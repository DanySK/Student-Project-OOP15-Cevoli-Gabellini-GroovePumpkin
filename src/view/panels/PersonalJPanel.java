package view.panels;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import view.Controllable;
import model.PlayerState;
import controller.Updatable;
import static view.config.Utility.*;

/**
 * A Personalized White&DarkGrey JPanel, with a personalizable Layout
 * 
 * @author Alessandro
 *
 */

public class PersonalJPanel<C> extends JPanel implements Updatable,
		Controllable<C> {

	private static final long serialVersionUID = -7098020132793828090L;

	private C controller;
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
		for (Component c : cmp) {
			this.add(c);
		}
	}

	/**
	 * Add new Observers for this object
	 * 
	 * @param up
	 */
	public void addObservers(final Updatable... up) {
		for (Updatable u : up) {
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
	public C getController() {
		return this.controller;
	}

	/**
	 * NOTE: This method assumes that all the Controllolable object inside 
	 * this component are of the same type <C>
	 * 
	 * @param controller
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setController(final C controller) {
		this.controller = controller;
		for (Component c : this.getComponents()) {
			if (c instanceof Controllable) {
				((Controllable<C>) c).setController(controller);
			}
		}
	}

	@Override
	public void updateStatus(final PlayerState status) {
		observers.stream().forEach(u -> u.updateStatus(status));
	}

}
