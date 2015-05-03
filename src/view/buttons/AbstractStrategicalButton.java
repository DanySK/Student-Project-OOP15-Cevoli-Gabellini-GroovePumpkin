package view.buttons;

import javax.swing.ImageIcon;

import model.PlayerState;
import view.buttons.strategies.ButtonStrategy;

/**
 * This abstract class implements a button ables to
 * use strategy to modify its behaviour
 * 
 * @author Alessandro
 *
 * @param <C>
 */
public abstract class AbstractStrategicalButton<C> extends PersonalJButton<C> {

	private static final long serialVersionUID = 5596342092610796464L;

	private ButtonStrategy<C, AbstractStrategicalButton<C>> strategy;

	protected AbstractStrategicalButton(final ImageIcon image,final C controller,
			final ButtonStrategy<C, AbstractStrategicalButton<C>> strategy,
			final boolean showTitle) {
		
		super(image);
		super.setController(controller);
		this.setStrategy(strategy);
		this.doShow(showTitle, strategy.getTitle());
		
		this.addActionListener(e -> {
			// go to the next song
			this.getStrategy().doStrategy(this.getController());
		});
	}

	/**
	 * 
	 * @return The strategy implemented by this Object
	 */
	public ButtonStrategy<C, AbstractStrategicalButton<C>> getStrategy() {
		return this.strategy;
	}

	/**
	 * Change the strategy implemented by this Object
	 * 
	 * @param strategy
	 */
	public void setStrategy(final ButtonStrategy<C, AbstractStrategicalButton<C>> strategy) {
		this.strategy = strategy;
	}
	
	@Override
	public void updateStatus(PlayerState status) {
		
		this.getStrategy().update(this, status);
	}
}
