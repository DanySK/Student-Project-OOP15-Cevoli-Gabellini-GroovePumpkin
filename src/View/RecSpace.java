package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author Alessandro
 *
 */
@SuppressWarnings("unused")
public class RecSpace extends PersonalJButton {

	private static final long serialVersionUID = -2653526539862883988L;
	public static final String REC = "Rec";
	public static final String STOP = "Stop";

	public RecSpace(final PlaySpace rps) {

		super(PersonalJButton.REC_IMG);
		this.showTitle(REC);

		this.addActionListener(new RECListener(this, rps));
	}

	public boolean isRecording() {
		return super.getTitledBorder().getTitle().equals("Stop");
	}

	public static class RECListener implements ActionListener {

		private PersonalJButton rec;
		private PlaySpace rps;
		

		public RECListener(final PersonalJButton rec,
				final PlaySpace rps) {
			this.rec = rec;
			this.rps = rps;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (rec.getTitledBorder().getTitle().equals(REC)) {
				rec.getTitledBorder().setTitle(STOP);
				rec.setIcon(PersonalJButton.STOP_IMG);
				rps.setEnabled(true);

				// start recording the sound

			} else {
				rec.getTitledBorder().setTitle(REC);
				rec.setIcon(PersonalJButton.REC_IMG);
				rps.getTitledBorder().setTitle("Pause");
				rps.setIcon(PersonalJButton.PAUSE_IMG);
				rps.setEnabled(false);

				// stop recording

			}

		}
	}
}
