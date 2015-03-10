package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import controller.GrooveBoxController;
import Model.GrooveValues;
import Model.GrooveTableModel;
import Model.PlayerState;
import View.buttons.ButtonFactory;


/**
 * This class rapresents the GUI space for the groovebox
 * 
 * @author Alessandro
 *
 */

public class GroovePanel extends PersonalJPanel implements Updatable{

	private static final long serialVersionUID = 1116768170189928089L;

	private final Double[] items = new Double[] { 40d, 60d, 80d, 100d, 120d,
			140d, 160d, 180d };

	private final JComboBox<Double> timeDialerOptions = new JComboBox<>(items);
	private final GrooveBox grooveBox = new GrooveBox(new GrooveTableModel(GrooveValues.
			initAGrooveBoxList(new ArrayList<GrooveValues>(50))));
	private GrooveBoxController controller;
	private final List<Updatable> buttons= new ArrayList<>();
	
	public GroovePanel(final GrooveBoxController controller) {
		super(new BorderLayout(5, 5));
		
		this.controller= controller;
		
		timeDialerOptions.setBackground(WHITE);
		timeDialerOptions.setForeground(GRAY);

		final PersonalJPanel westPanel = new PersonalJPanel(new BorderLayout(5,
				5));
		westPanel.setBuiltInBorder();

		populateWestPanel(westPanel);
		this.add(westPanel, BorderLayout.WEST);
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
		
		buttons.add((Updatable) ButtonFactory.createButton(ButtonFactory.PLAY_BUTTON, true, null));
		buttonPanel.add((JButton) buttons.get(buttons.size()-1));
		buttonPanel.add(ButtonFactory.createButton(ButtonFactory.LOOP_BUTTON, true, grooveBox.getGrooveList()));
		
		buttonPanel.add(ButtonFactory.createButton(ButtonFactory.SAVE_BUTTON, true, null));

		westPanel.add(buttonPanel, BorderLayout.CENTER);
	}

	/**
	 * @return The time dial in beat for minute
	 */
	public Double getDial() {
		return (Double) timeDialerOptions.getSelectedItem();
	}

	@Override
	public void updateStatus(final PlayerState status) {
		for(Updatable u : buttons){
			u.updateStatus(status);
		}
	}
}
