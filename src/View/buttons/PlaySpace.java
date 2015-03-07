package View.buttons;

import javax.swing.ImageIcon;

/**
 * This class extends the abstract composite (Label+Button) space to build a
 * play and pause system for this software
 * 
 * @author Alessandro
 *
 */
public class PlaySpace extends PersonalJButton {

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
	protected PlaySpace(final ImageIcon img, final boolean showTitle,
			final Object controller) {

		super(img);
		super.setController(controller);

		if (img != null && this.getIcon().equals(PersonalJButton.PLAY_IMG)) {
			this.setID(PLAY);
		} else {
			this.setID(PAUSE);
		}

		if (showTitle) {
			this.showTitle(this.getID());
		}

		this.addActionListener(e -> {
			if (PlaySpace.this.getID().equals(PLAY)) {
				PlaySpace.this.setID(PAUSE);
				if (PlaySpace.this.getTitledBorder() != null) {
					PlaySpace.this.getTitledBorder().setTitle(PAUSE);
				}
				PlaySpace.this.setIcon(PersonalJButton.PAUSE_IMG);
				// start

			} else {
				PlaySpace.this.setID(PLAY);
				if (PlaySpace.this.getTitledBorder() != null) {
					PlaySpace.this.getTitledBorder().setTitle(PLAY);
				}
				PlaySpace.this.setIcon(PersonalJButton.PLAY_IMG);
				// pause

			}
		});
	}

	/**
	 * 
	 * @return if the song is paused or is running
	 */
	public boolean isPaused() {
		return super.getID().equals(PLAY);
	}
}
