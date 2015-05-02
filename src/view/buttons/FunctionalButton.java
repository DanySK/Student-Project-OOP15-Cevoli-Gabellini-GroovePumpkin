package view.buttons;

import static view.buttons.strategies.PlayerStrategy.*;
import view.buttons.strategies.*;

/**
 * This class implements the functionality of a 
 * multifunctional button using the chisen strategy
 * depending on which strategy as been chosen
 * 
 * @author Alessandro
 *
 */
public class FunctionalButton<C> extends StrategicalButton<C> {

	private static final long serialVersionUID = -5804691980876122814L;
	
	/**
	 * 
	 * @param controller
	 * @param showTitle
	 * @param strategy
	 */
	protected FunctionalButton(final C controller, final boolean showTitle,
			final ButtonStrategy<C, StrategicalButton<C>> strategy) {
		
		super(strategy.getImage(), controller, strategy, showTitle);
		
		if (this.getStrategy().equals(STOP)) {
			this.setEnabled(false);
		}
	}
}
