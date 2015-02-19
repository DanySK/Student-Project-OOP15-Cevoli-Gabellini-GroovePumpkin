package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

/**
 * This class extends the abstract composite (Label+Button) space to build a
 * play and pause system for this software
 * 
 * @author Alessandro
 *
 */
@SuppressWarnings("unused")
public class PlayAndPauseSpace extends AbstractCompositeJSpace {

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
	public PlayAndPauseSpace(final ImageIcon img) {

		super(img);
		this.addActionListener(new PlayListener(this));

		if (img != null && this.getIcon().equals(PersonalJButton.PLAY_IMG)) {
			// System.out.println("OK");
			this.setID(PLAY);
		} else {
			// System.out.println("OK");
			this.setID(PAUSE);
		}
	}

	/**
	 * 
	 * @return if the song is pause or is running
	 */
	public boolean isPaused() {
		return super.getTitle().equals(PLAY);
	}

	/**
	 * Set if the button start enabled or not
	 * 
	 * @param buttonEnabled
	 */
	public void setButtonEnabled(final boolean buttonEnabled) {
		this.setEnabled(buttonEnabled);
	}

	public static class PlayListener implements ActionListener {
		private TitledBorder label;
		private PersonalJButton play;

		public PlayListener(final PersonalJButton play) {
			this.play = play;

			if (play.getBorder() instanceof CompoundBorder
					&& ((CompoundBorder) play.getBorder()).getOutsideBorder() instanceof TitledBorder) {
				this.label = (TitledBorder) ((CompoundBorder) play.getBorder())
						.getOutsideBorder();
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (label == null) {

				if (play.getID().equals(PAUSE)) {
					play.setID(PLAY);
					play.setIcon(PersonalJButton.PLAY_IMG);
					// pause the recording
				} else {
					play.setID(PAUSE);
					play.setIcon(PersonalJButton.PAUSE_IMG);
					// restart the recording
				}

			} else {

				if (label.getTitle().equals(PAUSE)) {
					label.setTitle(PLAY);
					play.setIcon(PersonalJButton.PLAY_IMG);
					// pause the recording
				} else {
					label.setTitle(PAUSE);
					play.setIcon(PersonalJButton.PAUSE_IMG);
					// restart the recording
				}

			}
		}
	}
}
