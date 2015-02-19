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
public class RecSpace extends AbstractCompositeJSpace {

	private static final long serialVersionUID = -2653526539862883988L;
	public static final String REC = "Rec";
	public static final String STOP = "Stop";

	public RecSpace(final PlayAndPauseSpace rps) {

		super(PersonalJButton.REC_IMG);
		this.showTitle(REC);

		this.addActionListener(new RECListener(this, rps));
	}

	public boolean isRecording() {
		return super.getTitle().equals("Stop");
	}

	public static class RECListener implements ActionListener {

		private PersonalJButton rec;
		private PlayAndPauseSpace rps;
		private TitledBorder label;

		public RECListener(final PersonalJButton rec,
				final PlayAndPauseSpace rps) {
			this.rec = rec;
			this.rps = rps;

			this.label = (TitledBorder) ((CompoundBorder) rec.getBorder())
					.getOutsideBorder();
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (label.getTitle().equals(REC)) {
				label.setTitle(STOP);
				rec.setIcon(PersonalJButton.STOP_IMG);
				rps.setEnabled(true);

				// start recording the sound

			} else {
				label.setTitle(REC);
				rec.setIcon(PersonalJButton.REC_IMG);
				rps.getTitledBorder().setTitle("Pause");
				rps.setIcon(PersonalJButton.PAUSE_IMG);
				rps.setEnabled(false);

				// stop recording

			}

		}
	}
}
