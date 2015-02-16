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

/**
 * This class extends the abstract composite (Label+Button) space 
 * to build a play and pause system for this software
 * 
 * @author Alessandro
 *
 */
@SuppressWarnings("unused")
public class PlayAndPauseSpace extends AbstractCompositeJSpace {

	private static final long serialVersionUID = -8958765355776362631L;
	protected static final String PLAY = "Play";
	protected static final String PAUSE = "Pause";
	
	/**
	 * 
	 * @param layout to be attached
	 * @param name to be applied on the label
	 * @param buttonEnabled if the button have to be enable or not
	 */
	public PlayAndPauseSpace(final LayoutManager layout, final String name,
			final boolean buttonEnabled) {

		super(layout, name, PersonalJButton.PLAY_IMG);

		super.setButtonActionListener(new PlayListener(this));
		super.getButton().setEnabled(buttonEnabled);
	}
	
	/**
	 * 
	 * @param layout to be attached
	 * @param buttonEnabled if the button have to be enabled or not
	 */
	public PlayAndPauseSpace(final LayoutManager layout,
			final boolean buttonEnabled) {

		super(layout, PersonalJButton.PLAY_IMG);
		super.setButtonActionListener(new PlayListener(this));
		super.getButton().setEnabled(buttonEnabled);
	}
	
	/**
	 * 
	 * @return if the song is pause or is running
	 */
	public boolean isPaused() {
		return super.getLabel().getText().equals(PLAY);
	}
	
	private static class PlayListener implements ActionListener {
		private PlayAndPauseSpace panel;

		public PlayListener(final PlayAndPauseSpace panel) {
			this.panel = panel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (panel.getLabel().getText().equals(PAUSE)) {
				panel.getLabel().setText(PLAY);
				panel.getButton().setIcon(PersonalJButton.PLAY_IMG);
				// pause the recording
			} else {
				panel.getLabel().setText(PAUSE);
				panel.getButton().setIcon(PersonalJButton.PAUSE_IMG);
				// restart the recording
			}

		}
	}
}
