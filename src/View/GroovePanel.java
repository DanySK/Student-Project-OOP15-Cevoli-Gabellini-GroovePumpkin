package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.BorderUIResource.EtchedBorderUIResource;

/**
 * 
 * @author Alessandro
 *
 */
@SuppressWarnings("unused")
public class GroovePanel extends PersonalJPanel {

	private static final long serialVersionUID = 1116768170189928089L;

	private final Double[] items = new Double[] { 40d, 60d, 80d, 100d, 120d,
			140d, 160d, 180d };

	private final JComboBox<Double> timeDialerOptions = new JComboBox<>(items);

	public GroovePanel() {
		super(new BorderLayout());
		timeDialerOptions.setBackground(WHITE);
		timeDialerOptions.setForeground(GRAY);
		final PersonalJPanel westPanel = new PersonalJPanel(new BorderLayout(5, 5));
		populateWestPanel(westPanel);

		this.add(westPanel, BorderLayout.WEST);
		final PersonalJPanel grooveBox= new PersonalJPanel(new BorderLayout(5, 5));
		grooveBox.add(new GrooveTable(), BorderLayout.CENTER);
		this.add(new JScrollPane(grooveBox), BorderLayout.CENTER);
	}

	private void populateWestPanel(final PersonalJPanel westPanel) {

		final JLabel timeDialerLabel = new JLabel("Time Dial: ");
		timeDialerLabel.setBackground(WHITE);
		timeDialerLabel.setForeground(GRAY);

		final PersonalJPanel timePanel = new PersonalJPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
		timePanel.add(timeDialerLabel);
		timePanel.add(timeDialerOptions);
		westPanel.add(timePanel, BorderLayout.NORTH);

		final PersonalJPanel buttonPanel = new PersonalJPanel();
		buttonPanel.setLayout(new GridLayout(4, 1, 3, 3));
		
		final PlayAndPauseSpace play = new PlayAndPauseSpace(new FlowLayout(), true);
		
		final PersonalJPanel loopPanel= new PersonalJPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
		final PersonalJButton loop = new PersonalJButton(
				PersonalJButton.LOOP_OFF_IMG, "off");
		loop.setAlignmentX(CENTER_ALIGNMENT);
		loop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(loop.getName().equals("off")){
					loop.setName("on");
					loop.setIcon(PersonalJButton.LOOP_ON_IMG);
					// Loop the groove
				} else{
					loop.setName("off");
					loop.setIcon(PersonalJButton.LOOP_OFF_IMG);
					//UnLoop the groove
				}
			}
		});
		
		loopPanel.add(loop);
		buttonPanel.add(play);
		buttonPanel.add(loopPanel);

		westPanel.add(buttonPanel, BorderLayout.CENTER);
	}

	public Double getDial() {
		return (Double) timeDialerOptions.getSelectedItem();
	}
}
