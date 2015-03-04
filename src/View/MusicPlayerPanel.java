package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

/**
 * Personalized JPanel for the PlayBackPanel, this class "handles" the playing
 * and pausing of a choosen song.
 * 
 * @author Alessandro
 *
 */
public class MusicPlayerPanel extends PersonalJPanel {

	private static final long serialVersionUID = 4164776505153007930L;

	private final JLabel songName = new JLabel("< Nothing Else Matters >");
	private final PlaySpace play = new PlaySpace(PersonalJButton.PLAY_IMG, false);
	private final StopSpace stop = new StopSpace(play, false);
	private final JSlider gain = new JSlider(JSlider.HORIZONTAL, 0, 100, 35);

	public MusicPlayerPanel(final List<File> playlist) {
		super(new BorderLayout());

		this.setBuiltInBorder();
		songName.setBackground(WHITE);
		songName.setForeground(GRAY);

		final PersonalJPanel north = new PersonalJPanel(new BorderLayout());
		populateNorthPanel(north);
		this.add(north, BorderLayout.NORTH);

		final PersonalJPanel east = new PersonalJPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.add(new LoopSpace(playlist, false));
		east.add(stop);
		this.add(east, BorderLayout.EAST);

		final PersonalJPanel gainPanel = new PersonalJPanel(new BorderLayout(
				10, 5));
		this.populateGainPanel(gainPanel);
		this.add(gainPanel, BorderLayout.SOUTH);
	}

	private void populateNorthPanel(final PersonalJPanel north) {

		final PersonalJPanel northLabel = new PersonalJPanel(new FlowLayout());
		northLabel.add(songName);

		north.add(northLabel, BorderLayout.NORTH);

		final PersonalJPanel northCentral = new PersonalJPanel(new FlowLayout());

		final PersonalJButton fw = new PersonalJButton(PersonalJButton.FW_IMG);
		fw.addActionListener(e->{
			// go to the next song
		});

		final PersonalJButton rw = new PersonalJButton(PersonalJButton.RW_IMG);
		rw.addActionListener(e->{
			// go back to the previous song
		});

		northCentral.add(rw);
		northCentral.add(play);
		northCentral.add(fw);

		north.add(northCentral, BorderLayout.CENTER);
	}
	
	private void populateGainPanel(PersonalJPanel gainPanel) {

		final CompoundBorder gainLabel = (CompoundBorder) PersonalJButton
				.getACompoundTitledBorder("Volume: " + gain.getValue());

		((TitledBorder) gainLabel.getOutsideBorder()).setTitleColor(GRAY);
		gain.setBorder(gainLabel);
		gain.setBackground(WHITE);
		gain.setForeground(GRAY);
		gain.setEnabled(true);

		gain.addChangeListener(e->{
			// Change the Volume of the song
			((TitledBorder) gainLabel.getOutsideBorder())
					.setTitle("Volume: " + gain.getValue());

			gain.repaint();
		});

		gainPanel.add(gain, BorderLayout.CENTER);
	}
}
