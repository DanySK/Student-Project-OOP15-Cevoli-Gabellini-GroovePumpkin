package view.buttons;

import model.PlayerState;
import view.interfaces.BtnStrategy;
import static view.buttons.strategies.PlayerStrategy.*;

/**
 * This class implements the functionality of a 
 * multifunctional button using the chisen strategy
 * depending on which strategy as been chosen
 * 
 * @author Alessandro
 * 
 * @param <C> the type of controller to be attached
 *
 */
public class FunctionalButton<C> extends AbsStratBtn<C> {

	private static final long serialVersionUID = -5804691980876122814L;
	
	/**
	 * 
	 * @param controller
	 * @param showTitle
	 * @param strategy
	 */
	public FunctionalButton(final C controller, final boolean showTitle,
			final BtnStrategy<C, AbsStratBtn<C>, PlayerState> strategy) {
		
		super(strategy.getImage(), controller, strategy, showTitle);
		
		if (this.getStrategy().equals(STOP)) {
			this.setEnabled(false);
		}
	}
}
