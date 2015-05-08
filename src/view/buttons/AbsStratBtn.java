package view.buttons;

import javax.swing.ImageIcon;

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
public abstract class AbsStratBtn<C> extends PersonalJButton<C>
	implements StratObj<BtnStrategy<C, AbsStratBtn<C>, PlayerState>>{

	private static final long serialVersionUID = 5596342092610796464L;

	private BtnStrategy<C, AbsStratBtn<C>, PlayerState> strategy;

	protected AbsStratBtn(final ImageIcon image,final C controller,
			final BtnStrategy<C, AbsStratBtn<C>, PlayerState> strategy,
			final boolean showTitle) {
		
		super(image);
		super.setController(controller);
		this.setStrategy(strategy);
		this.doShow(showTitle, strategy.getTitle());
		
		this.addActionListener(e -> {
			// go to the next song
			this.doStrategy();
		});
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
	public BtnStrategy<C, AbsStratBtn<C>, PlayerState> getStrategy() {
		return this.strategy;
	}

	/**
	 * Change the strategy implemented by this Object
	 * 
	 * @param strategy
	 */
	public void setStrategy(final BtnStrategy<C, AbsStratBtn<C>, PlayerState> strategy) {
		this.strategy = strategy;
	}
	
	@Override
	public void updateStatus(PlayerState status) {
		
		this.getStrategy().updateUser(this, status);
	}
}
