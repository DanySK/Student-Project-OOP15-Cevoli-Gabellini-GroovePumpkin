package view.panels;

import java.awt.Component;
import java.awt.LayoutManager;

import view.interfaces.Controllable;

/**
 * This class rapresents a Panel that can hold and communicate with the attached
 * controller of type <C>
 * 
 * @author Alessandro
 *
 * @param <C>
 */
public abstract class ControllablePane<C> extends PersonalJPanel 
		implements Controllable<C> {

	private static final long serialVersionUID = 2742792723546786577L;
	private C controller;

	/**
	 * A basic constructor
	 * 
	 */
	public ControllablePane() {
		super();
	}

	/**
	 * A basic constructor with the given layout for this panel
	 * 
	 * @param layout
	 */
	public ControllablePane(final LayoutManager layout) {
		super(layout);
	}

	@Override
	public C getController() {
		return this.controller;
	}

	/**
	 * NOTE: This method assumes that all the Controllolable object inside this
	 * component are of the same type <C>
	 * 
	 * The warning has been suppressed because was annoying, I used a try catch
	 * to avoid undesired effect
	 * 
	 * @param controller
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setController(final C controller) {
		this.controller = controller;
		for (final Component c : this.getComponents()) {
			if (c instanceof Controllable) {
				try {
					((Controllable<C>) c).setController(controller);
				} catch (ClassCastException ex) {

				}
			}
		}
	}

}
