package bbb;

import javax.swing.ImageIcon;

import model.PlayerState;
import static bbb.Utility.*;
import controller.Player;

/**
 * This class extends the PersonalJButton to build a play and pause manager of
 * the controller
 * 
 * @author Alessandro
 *
 */
public class PlayPauseSpace extends PersonalJButton implements Updatable {

	private static final long serialVersionUID = -8958765355776362631L;
	public static final String PLAY = "Play";
	public static final String PAUSE = "Pause";

	/**
	 * 
	 * @param layout
	 *            to be attached
	 * @param buttonEnabled
	 *            if the button have to be enabled or not
	 */
	protected PlayPauseSpace(final Player controller, final ImageIcon img,
			final boolean showTitle) {

		super(img);
		super.setController(controller);

		doShow(showTitle, PLAY);

		this.addActionListener(e -> {
			if (this.getIcon().equals(PLAY_IMG)) {
				try {
					controller.play();
				} catch (final Exception err_play) {
					showErrorDialog(this, "No playable items found");
				}

			} else {
				try {
					controller.pause();
				} catch (final Exception err_pause) {
					showErrorDialog(this, "No pausable items found");
				}
			}
		});
	}

	@Override
	public void updateStatus(final PlayerState status) {

		if (status.equals(PlayerState.RUNNING)) {
			this.setIcon(PAUSE_IMG);
			this.changeTitle(PAUSE);
			// start
		} else if (status.equals(PlayerState.PAUSED) || status.equals(PlayerState.STOPPED)) {
			this.setIcon(PLAY_IMG);
			this.changeTitle(PLAY);
			// pause
		}
	}
}
