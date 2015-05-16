package view.buttons;

import model.PlayerState;
import view.interfaces.BtnStrategy;
import view.interfaces.StratObj;

/**
 * This abstract class implements a button ables to
 * use strategy to modify its behaviour
 * 
 * @author Alessandro
 *
 * @param <C>
 */
public abstract class AbstractStratBtn<C> extends PersonalJButton<C>
	implements StratObj<BtnStrategy<C, AbstractStratBtn<C>, PlayerState>>{

	private static final long serialVersionUID = 5596342092610796464L;

	private BtnStrategy<C, AbstractStratBtn<C>, PlayerState> strategy;

	protected AbstractStratBtn(final C controller,
			final BtnStrategy<C, AbstractStratBtn<C>, PlayerState> strategy,
			final boolean showTitle) {
		
		super(strategy.getImage());
		super.setController(controller);
		this.setStrategy(strategy);
		this.doShow(showTitle, strategy.getTitle());
	}
	
	/**
	 * Applies the implemented strategy
	 * 
	 */
	public void doStrategy(){
		this.getStrategy().doStrategy(getController());
	}
	
	/**
	 * 
	 * @return The strategy implemented by this Object
	 */
	public BtnStrategy<C, AbstractStratBtn<C>, PlayerState> getStrategy() {
		return this.strategy;
	}

	/**
	 * Change the strategy implemented by this Object
	 * 
	 * @param strategy
	 */
	public void setStrategy(final BtnStrategy<C, AbstractStratBtn<C>, PlayerState> strategy) {
		this.strategy = strategy;
	}
	
	@Override
	public void updateStatus(PlayerState status) {
		
		this.getStrategy().updateUser(this, status);
	}
}
