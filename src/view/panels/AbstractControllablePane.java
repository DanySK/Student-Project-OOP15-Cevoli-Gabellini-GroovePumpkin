package view.panels;

import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import view.interfaces.Controllable;

/**
 * This class rapresents a Panel that can hold and communicate with the attached
 * controller of type <C>
 * 
 * @author Alessandro
 *
 * @param <C>
 */
public abstract class AbstractControllablePane<C> extends PersonalJPanel 
		implements Controllable<C> {

	private static final long serialVersionUID = 2742792723546786577L;
	private C controller;
	private final List<CmdPane> cmdPane= new ArrayList<>();

	/**
	 * A basic constructor
	 * 
	 */
	protected AbstractControllablePane() {
		super();
	}

	/**
	 * A basic constructor with the given layout for this panel
	 * 
	 * @param layout
	 */
	protected AbstractControllablePane(final LayoutManager layout) {
		super(layout);
	}

	@Override
	public C getController() {
		return this.controller;
	}

	/**
	 * Change the controller attached to this object,
	 * it doesn't change the one attached to the children
	 * of this object if it has controllable children
	 * 
	 * @param controller
	 */
	@Override
	public void setController(final C controller) {
		this.controller = controller;
	}
	
	/**
	 * 
	 * @return The list of commands panels associated with this object
	 */
	public List<CmdPane> getCommandPane(){
		return this.cmdPane;
	}
}
