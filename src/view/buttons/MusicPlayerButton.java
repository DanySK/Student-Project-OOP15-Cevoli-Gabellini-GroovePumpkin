package view.buttons;

import java.util.Optional;

import view.buttons.strategies.MusicPlayerStrategy;
import model.PlayerState;
import controller.MusicPlayer;

/**
 * This class implements the functionality of a forwarder or a Rewinder
 * depending on which strategy as been chosen
 * 
 * @author Alessandro
 *
 */
public class MusicPlayerButton extends TJB {

	private static final long serialVersionUID = -5804691980876122814L;
	private MusicPlayerStrategy strategy;

	protected MusicPlayerButton(final MusicPlayer controller, final boolean showTitle,
			final MusicPlayerStrategy strategy) {
		super(strategy.getImage());
		doShow(showTitle, strategy.getTitle());
		super.setController(controller);
		this.strategy = strategy;

		this.addActionListener(e -> {
			// go to the next song
			this.strategy.doStrategy((MusicPlayer) this.getController());
		});
	}

	/**
	 * 
	 * @return The strategy implemented by this Object
	 */
	public MusicPlayerStrategy getStrategy() {
		return this.strategy;
	}

	/**
	 * Change the strategy implemented by this Object
	 * 
	 * @param strategy
	 */
	public void setStrategy(final Optional<MusicPlayerStrategy> strategy) {
		if (strategy.isPresent()) {
			this.strategy = strategy.get();
		}
	}
	
	@Override
	public void updateStatus(PlayerState status) {
		this.strategy.accept(this, status);
	}
}
