package view.buttons;

import static view.buttons.strategies.PlayerStrategy.*;
import controller.Player;
import view.buttons.strategies.ButtonStrategy;

/**
 * This class implements the functionality of a forwarder or a Rewinder
 * depending on which strategy as been chosen
 * 
 * @author Alessandro
 *
 */
public class MusicPlayerButton<C extends Player> extends StrategicalButton<C> {

	private static final long serialVersionUID = -5804691980876122814L;
	/**
	 * 
	 * @param controller
	 * @param showTitle
	 * @param strategy
	 */
	protected MusicPlayerButton(final C controller, final boolean showTitle,
			final ButtonStrategy<C, StrategicalButton<C>> strategy) {
		
		super(strategy.getImage(), controller, strategy, showTitle);
		
		if (this.getStrategy().equals(STOP)) {
			this.setEnabled(false);
		}
	}
}
