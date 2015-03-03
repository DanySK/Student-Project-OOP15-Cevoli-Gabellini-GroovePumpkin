package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Personalized JPanel for the PlayBackPanel, this class "handles" the playing
 * and pausing of a choosen song.
 * 
 * @author Alessandro
 *
 */
@SuppressWarnings("unused")
public class CompositeEastPanel extends PersonalJPanel {

	private static final long serialVersionUID = 4164776505153007930L;

	private final JLabel songName = new JLabel("< Nothing Else Matters >");
	private final PlaySpace play = new PlaySpace(PersonalJButton.PLAY_IMG);
	private final PersonalJButton stop = new PersonalJButton(PersonalJButton.STOP_IMG);
	private final JSlider gain = new JSlider(JSlider.HORIZONTAL, 0, 100, 35);

	public CompositeEastPanel() {
		super(new BorderLayout());
		
		this.setBuiltInBorder();
		songName.setBackground(WHITE);
		songName.setForeground(GRAY);

		final PersonalJPanel north = new PersonalJPanel(new BorderLayout());
		populateNorthPanel(north);
		this.add(north, BorderLayout.NORTH);

		final PersonalJPanel central = new PersonalJPanel();
		central.setLayout(new BoxLayout(central, BoxLayout.Y_AXIS));
		this.populateCentralPanel(central);
		this.add(central, BorderLayout.EAST);

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
		fw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// go to the next song
			}
		});

		final PersonalJButton rw = new PersonalJButton(PersonalJButton.RW_IMG);
		rw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// go back to the previous song
			}
		});

		northCentral.add(rw);
		northCentral.add(play);
		northCentral.add(fw);

		north.add(northCentral, BorderLayout.CENTER);
	}

	private void populateCentralPanel(final PersonalJPanel east) {
		
		final PersonalJButton loop = new PersonalJButton(
				PersonalJButton.LOOP_OFF_IMG, "off");

		loop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (loop.getID().equals("off")) {
					loop.setID("on");
					loop.setIcon(PersonalJButton.LOOP_ON_IMG);
					// Loop the playlist
				} else {
					loop.setID("off");
					loop.setIcon(PersonalJButton.LOOP_OFF_IMG);
					// UnLoop the playlist
				}
			}
		});
		
		this.stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(CompositeEastPanel.this.play.isPaused()){
					
				} else{
					
				}
			}
		});

		east.add(loop);
		east.add(this.stop);
	}

	private void populateGainPanel(PersonalJPanel gainPanel) {

		final CompoundBorder gainLabel = (CompoundBorder) PersonalJButton
				.getACompoundTitledBorder("Volume: " + gain.getValue());

		((TitledBorder) gainLabel.getOutsideBorder()).setTitleColor(GRAY);
		gain.setBorder(gainLabel);
		gain.setBackground(WHITE);
		gain.setForeground(GRAY);
		gain.setEnabled(true);

		gain.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// Change the Volume of the song
				((TitledBorder) gainLabel.getOutsideBorder())
						.setTitle("Volume: " + gain.getValue());

				gain.repaint();
			}
		});

		gainPanel.add(gain, BorderLayout.CENTER);
	}
}
