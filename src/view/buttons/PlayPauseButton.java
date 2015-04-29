package view.buttons;

import javax.swing.ImageIcon;

import model.PlayerState;
import static view.config.Utility.*;
import controller.Player;

/**
 * This class extends the PersonalJButton to build a play and pause manager of
 * the controller
 * 
 * @author Alessandro
 *
 */
public class PlayPauseButton extends TJB {

	private static final long serialVersionUID = -8958765355776362631L;

	private PPStrategy strategy;

	/**
	 * 
	 * @param layout
	 *            to be attached
	 * @param buttonEnabled
	 *            if the button have to be enabled or not
	 */
	protected PlayPauseButton(final Player controller, final PPStrategy strategy,
			final boolean showTitle) {

		super(strategy.getImage());
		super.setController(controller);
		this.strategy = strategy;
		this.strategy.attachController(controller);
		doShow(showTitle, strategy.getTitle());

		this.addActionListener(e -> {
			strategy.doStrategy();
		});
	}

	@Override
	public void updateStatus(final PlayerState status) {

		if (status.equals(PlayerState.RUNNING)
				|| status.equals(PlayerState.PAUSED)
				|| status.equals(PlayerState.STOPPED)) {
			// change to pausable or playble strategy
			this.strategy = status.equals(PlayerState.RUNNING)
					|| status.equals(PlayerState.PAUSED) ? this.strategy.equals(PPStrategy.PLAY) ? PPStrategy.PAUSE
					: PPStrategy.PLAY : PPStrategy.PLAY;
			this.setIcon(strategy.getImage());
			this.changeTitle(strategy.getTitle());
		} else if(status.equals(ERROR)){
			showErrorDialog(null, "WTF");
		}
	}

	public static enum PPStrategy {
		PLAY("Play", PLAY_IMG), PAUSE("Pause", PAUSE_IMG);

		private String title;
		private ImageIcon img;
		private Player controller;

		private PPStrategy(final String title, final ImageIcon img) {
			this.title = title;
			this.img = img;
		}

		public void attachController(final Player controller) {
			this.controller = controller;
		}

		public String getTitle() {
			return title;
		}

		public ImageIcon getImage() {
			return img;
		}

		public void doStrategy() {
			try {
				if (this.equals(PLAY)) {
					controller.play();
				} else {
					controller.pause();
				}
			} catch (final Exception e) {
				e.printStackTrace();
				showErrorDialog(null, "No item found");
			}

		}
	}
}
