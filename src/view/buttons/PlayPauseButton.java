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
	protected PlayPauseButton(final Player controller,
			final PPStrategy strategy, final boolean showTitle) {

		super(strategy.getImage());
		super.setController(controller);
		this.strategy = strategy;
		this.strategy.attachController(controller);
		doShow(showTitle, strategy.getTitle());

		this.addActionListener(e -> {
			this.strategy.doStrategy();
		});
	}

	@Override
	public void updateStatus(final PlayerState status) {

		if (status.equals(PlayerState.RUNNING)
				|| status.equals(PlayerState.PAUSED)
				|| status.equals(PlayerState.STOPPED)
				|| status.equals(PlayerState.REMOVED)) {
			// change to pausable or playble strategy
			this.strategy = status.equals(PlayerState.RUNNING) ? PPStrategy.PAUSE
					: PPStrategy.PLAY;
			this.strategy.attachController((Player) getController());
			// System.out.println(strategy);
			this.setIcon(strategy.getImage());
			this.changeTitle(strategy.getTitle());
			this.repaint();
		}
	}

	public static enum PPStrategy {
		PLAY("Play", PLAY_IMG, true), PAUSE("Pause", PAUSE_IMG, false);

		private String title;
		private ImageIcon img;
		private Player controller;
		private boolean b;

		private PPStrategy(final String title, final ImageIcon img,
				final boolean b) {
			this.title = title;
			this.img = img;
			this.b = b;
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
				if (b) {
					System.out.println("Metto in Riproduzione");
					controller.play();
				} else {
					System.out.println("Metto in Pausa");
					controller.pause();
				}
			} catch (final Exception e) {
				e.printStackTrace();
				showErrorDialog(null, "No item found");
			}

		}
	}
}
