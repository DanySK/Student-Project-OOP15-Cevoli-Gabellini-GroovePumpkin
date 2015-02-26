package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;


/**
 * This class rapresents the GUI space for the groovebox
 * 
 * @author Alessandro
 *
 */

public class GroovePanel extends PersonalJPanel {

	private static final long serialVersionUID = 1116768170189928089L;

	private final Double[] items = new Double[] { 40d, 60d, 80d, 100d, 120d,
			140d, 160d, 180d };

	private final JComboBox<Double> timeDialerOptions = new JComboBox<>(items);
	private final GrooveBox grooveBox = new GrooveBox(GrooveBox.TABLEMODEL);
	
	public GroovePanel() {
		super(new BorderLayout(5, 5));

		timeDialerOptions.setBackground(WHITE);
		timeDialerOptions.setForeground(GRAY);

		final PersonalJPanel westPanel = new PersonalJPanel(new BorderLayout(5,
				5));
		westPanel.setBuiltInBorder();

		populateWestPanel(westPanel);
		this.add(westPanel, BorderLayout.WEST);
		
		grooveBox.initGrooveBox();
		this.add(new JScrollPane(grooveBox), BorderLayout.CENTER);
	}

	private void populateWestPanel(final PersonalJPanel westPanel) {

		final JLabel timeDialerLabel = new JLabel("Time Dial: ");
		timeDialerLabel.setBackground(WHITE);
		timeDialerLabel.setForeground(GRAY);

		final PersonalJPanel timePanel = new PersonalJPanel(new FlowLayout(
				FlowLayout.CENTER, 5, 10));
		timePanel.add(timeDialerLabel);
		timePanel.add(timeDialerOptions);
		westPanel.add(timePanel, BorderLayout.NORTH);

		final PersonalJPanel buttonPanel = new PersonalJPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		final PlaySpace play = new PlaySpace(PersonalJButton.PLAY_IMG);
		play.showTitle("Play");
		
		final PersonalJButton loop = new PersonalJButton(
				PersonalJButton.LOOP_OFF_IMG, "off");
		loop.showTitle("Loop");
		
		loop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (loop.getID().equals("off")) {
					loop.setID("on");
					loop.setIcon(PersonalJButton.LOOP_ON_IMG);
					// Loop the groove
				} else {
					loop.setID("off");
					loop.setIcon(PersonalJButton.LOOP_OFF_IMG);
					// UnLoop the groove
				}
			}
		});

		final SaveSpace save = new SaveSpace(true);
		
		buttonPanel.add(play);
		buttonPanel.add(loop);
		buttonPanel.add(save);

		westPanel.add(buttonPanel, BorderLayout.CENTER);
	}

	/**
	 * @return The time dial in beat for minute
	 */
	public Double getDial() {
		return (Double) timeDialerOptions.getSelectedItem();
	}
}
