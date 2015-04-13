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
import static View.buttons.ButtonFactory.*;
import static View.config.Utility.*;

/**
 * This class rapresents the GUI space for the groovebox
 * Is divided into a controls panel (where are placed various buttons)
 * and a personalized JTable that rapresent the GrooveBox
 * 
 * @author Alessandro
 *
 */

public class GroovePanel extends PersonalJPanel implements Updatable{

	private static final long serialVersionUID = 1116768170189928089L;

	private final Double[] items = new Double[] { 40d, 60d, 80d, 100d, 120d,
			140d, 160d, 180d };

	private final JComboBox<Double> timeDialerOptions = new JComboBox<>(items);
	private final GrooveBox grooveBox = new GrooveBox(new GrooveTableModel(GrooveValues.initAGrooveBoxList()));
	
	@SuppressWarnings("unused")
	private GrooveBoxController controller;
	
	private final List<Updatable> observer= new ArrayList<>();
	
	/**
	 * 
	 * @param controller
	 */
	public GroovePanel(final GrooveBoxController controller) {
		super(new BorderLayout(5, 5));
		observer.add(grooveBox);
		this.controller= controller;
		//controller.addUpdatableObserver(this);
		
		timeDialerOptions.setBackground(WHITE);
		timeDialerOptions.setForeground(DARK_GRAY);
		timeDialerOptions.setFocusable(false);

		final PersonalJPanel westPanel = new PersonalJPanel(new BorderLayout(5,
				5));
		westPanel.setBuiltInBorder();

		final JLabel timeDialerLabel = new JLabel("Time Dial: ");
		timeDialerLabel.setBackground(WHITE);
		timeDialerLabel.setForeground(DARK_GRAY);

		final PersonalJPanel timePanel = new PersonalJPanel(new FlowLayout(
				FlowLayout.CENTER, 5, 10));
		timePanel.add(timeDialerLabel);
		timePanel.add(timeDialerOptions);
		westPanel.add(timePanel, BorderLayout.NORTH);

		final PersonalJPanel buttonPanel = new PersonalJPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		observer.add((Updatable) createButton(PLAY_BUTTON, true, null));
		buttonPanel.add((JButton) observer.get(observer.size()-1));
		buttonPanel.add(createButton(LOOP_BUTTON, true, controller));
		buttonPanel.add(createButton(SAVE_BUTTON, true, controller));
		buttonPanel.add(createButton(LOAD_BUTTON, true, controller));
		westPanel.add(buttonPanel, BorderLayout.CENTER);
		
		this.add(westPanel, BorderLayout.WEST);
		this.add(new JScrollPane(grooveBox), BorderLayout.CENTER);
	}
	
	/**
	 * @return The time dial in beat for minute
	 */
	public Double getDial() {
		return (Double) timeDialerOptions.getSelectedItem();
	}
	
	/**
	 * 
	 * @return the groove associated to this object
	 */
	public GrooveBox getGrooveBox(){
		return this.grooveBox;
	}
	
	@Override
	public void updateStatus(final PlayerState status) {
			
		for(Updatable u : observer){
				u.updateStatus(status);
		}
	}
}
