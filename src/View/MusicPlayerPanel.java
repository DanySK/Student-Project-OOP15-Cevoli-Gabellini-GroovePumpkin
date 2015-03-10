package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import controller.MusicPlayer;
import Model.PlayerState;
import View.buttons.ButtonFactory;
import View.buttons.PersonalJButton;
/**
 * Personalized JPanel for the PlayBackPanel, this class "handles" the playing
 * and pausing of a choosen song.
 * 
 * @author Alessandro
 *
 */
public class MusicPlayerPanel extends PersonalJPanel implements Updatable{

	private static final long serialVersionUID = 4164776505153007930L;

	private final JLabel songName = new JLabel("< Nothing Else Matters >");
	private Updatable generic;
	private final JSlider gain = new JSlider(JSlider.HORIZONTAL, 0, 100, 35);
	private final List<Updatable> buttons= new ArrayList<>();

	public MusicPlayerPanel(final MusicPlayer controller) {
		super(new BorderLayout());

		this.setBuiltInBorder();
		songName.setBackground(WHITE);
		songName.setForeground(GRAY);

		final PersonalJPanel north = new PersonalJPanel(new BorderLayout());
		populateNorthPanel(north);
		this.add(north, BorderLayout.NORTH);

		final PersonalJPanel east = new PersonalJPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.add(ButtonFactory.createButton(ButtonFactory.LOOP_BUTTON, true, controller));
		generic= (Updatable) ButtonFactory.createButton(ButtonFactory.STOP_BUTTON, true, null);
		buttons.add(generic);
		east.add((Component) generic);
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
		generic= (Updatable) ButtonFactory.createButton(ButtonFactory.PLAY_BUTTON, false, null);
		buttons.add(generic);
		northCentral.add((Component) generic);
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

	@Override
	public void updateStatus(PlayerState status) {
		for( Updatable u : buttons){
			u.updateStatus(status);
		}
		
	}
}
