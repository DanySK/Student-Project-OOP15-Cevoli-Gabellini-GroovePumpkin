package view.buttons;

import java.util.Optional;

import view.buttons.strategies.PlayerStrategy;
import static view.buttons.strategies.PlayerStrategy.*;
import model.PlayerState;
import controller.Player;

/**
 * This class extends the PersonalJButton to build a play and pause manager of
 * the controller
 * 
 * @author Alessandro
 *
 */
public class PlayerButton extends TJB {

	private static final long serialVersionUID = -8958765355776362631L;

	private PlayerStrategy strategy;

	/**
	 * 
	 * @param layout
	 *            to be attached
	 * @param strategy
	 *            to be implemented by this object
	 * 
	 * @param buttonEnabled
	 *            if the button have to be enabled or not
	 */
	protected PlayerButton(final Player controller,
			final PlayerStrategy strategy, final boolean showTitle) {

		super(strategy.getImage());
		super.setController(controller);
		this.strategy = strategy;
		this.doShow(showTitle, strategy.getTitle());
		
		if (this.strategy.equals(STOP)){
			this.setEnabled(false);
		}

		this.addActionListener(e -> {
			this.strategy.doStrategy((Player) this.getController());
		});
	}

	/**
	 * 
	 * @return The strategy implemented by this Object
	 */
	public PlayerStrategy getStrategy() {
		return this.strategy;
	}

	/**
	 * Change the strategy implemented by this Object
	 * 
	 * @param strategy
	 */
	public void setStrategy(final Optional<PlayerStrategy> strategy) {
		if(strategy.isPresent()){
			this.strategy = strategy.get();
		}
	}

	@Override
	public void updateStatus(final PlayerState status) {
		strategy.accept(this, status);
	}
}
