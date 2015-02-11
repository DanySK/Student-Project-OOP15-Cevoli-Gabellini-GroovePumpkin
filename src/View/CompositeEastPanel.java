package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Personalized JPanel for the PlayBackPanel
 * 
 * @author Alessandro
 *
 */
@SuppressWarnings("unused")
public class CompositeEastPanel extends PersonalJPanel {

	private static final long serialVersionUID = 4164776505153007930L;

	private final JLabel songName = new JLabel("< Nothing Else Matters >");

	private final JSlider gain = new JSlider(JSlider.HORIZONTAL, 0, 100, 35);

	public CompositeEastPanel() {
		super(new BorderLayout());
		
		songName.setBackground(WHITE);
		songName.setForeground(GRAY);

		final PersonalJPanel north = new PersonalJPanel(new FlowLayout());
		north.add(songName);
		this.add(north, BorderLayout.NORTH);

		final PersonalJPanel centre = new PersonalJPanel(new FlowLayout());
		populateCentralPanel(centre);
		this.add(centre, BorderLayout.CENTER);
		
		final PersonalJPanel gainPanel = new PersonalJPanel(new BorderLayout());
		this.populateGainPanel(gainPanel);
		this.add(gainPanel, BorderLayout.SOUTH);
	}

	private void populateGainPanel(PersonalJPanel gainPanel) {
		
		final JLabel gainLabel = new JLabel("Volume: " + gain.getValue());
		
		gain.setBackground(WHITE);
		gain.setForeground(GRAY);
		gain.setEnabled(true);
		gain.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// Change the Volume of the song
				gainLabel.setText("Volume: " + gain.getValue());
				
			}
		});
		
		gainPanel.add(gainLabel, BorderLayout.NORTH);
		gainPanel.add(gain, BorderLayout.CENTER);
	}

	private void populateCentralPanel(final PersonalJPanel centre) {

		final PlayAndPauseSpace play = new PlayAndPauseSpace(PersonalJButton.PLAY_IMG, true);

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

		centre.add(rw);
		centre.add(play);
		centre.add(fw);
	}
}
