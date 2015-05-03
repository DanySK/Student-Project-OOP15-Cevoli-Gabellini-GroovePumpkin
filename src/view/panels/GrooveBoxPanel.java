package view.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import view.buttons.PersonalJButton;
import view.tables.GrooveBox;
import view.viewModel.GrooveTableModel;
import controller.GrooveBoxPlayer;
import controller.Updatable;
import static view.buttons.ButtonFactory.*;
import static view.config.Utility.*;

/**
 * This class rapresents the GUI space for the groovebox
 * Is divided into a controls panel (where are placed various buttons)
 * and a personalized JTable that rapresent the GrooveBox
 * 
 * @author Alessandro
 *
 */

public class GrooveBoxPanel extends PersonalJPanel<GrooveBoxPlayer>{

	private static final long serialVersionUID = 1116768170189928089L;
	private final Integer[] items = new Integer[] { 40, 60, 80, 100, 120,
			140, 160, 180 };
	private final JComboBox<Integer> timeDialerOptions = new JComboBox<>(items);
	private final GrooveBox grooveBox;
	
	/**
	 * 
	 * @param controller
	 */
	public GrooveBoxPanel(final GrooveBoxPlayer controller) {
		super(new BorderLayout(5, 5));
		this.setController(controller);
		grooveBox= new GrooveBox(new GrooveTableModel(controller));
		this.addObservers(grooveBox);
		//controller.addUpdatableObserver(this);
		
		timeDialerOptions.setBackground(WHITE);
		timeDialerOptions.setForeground(DARK_GRAY);
		timeDialerOptions.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(final ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					((GrooveBoxPlayer) getController())
						.setTempoInBPM(((Integer)e.getItem()).intValue());
				}
			}
		});

		final PersonalJPanel<Object> westPanel = new PersonalJPanel<>(new BorderLayout(5,
				5));
		westPanel.setBorder(getADefaultPanelBorder());

		final JLabel timeDialerLabel = new JLabel("Time Dial: ");
		timeDialerLabel.setBackground(WHITE);
		timeDialerLabel.setForeground(DARK_GRAY);

		final PersonalJPanel<Object> timePanel = new PersonalJPanel<>(new FlowLayout(
				FlowLayout.CENTER, 5, 10));
		timePanel.addComponents(timeDialerLabel, timeDialerOptions);
		westPanel.add(timePanel, BorderLayout.NORTH);

		final PersonalJPanel<Object> buttonPanel = new PersonalJPanel<>();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		addObservers((Updatable) createButton(PLAY_BUTTON, getController(), true));
		buttonPanel.addComponents((JButton) getObservers().get(getObservers().size()-1), 
				 createButton(LOOP_BUTTON, getController(), true), 
				 createButton(SAVE_BUTTON, getController(), true), 
				 createButton(LOAD_BUTTON, getController(), true));
		
		final PersonalJButton<GrooveBoxPlayer> reset= new PersonalJButton<>(RESET_IMG, "Reset");
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.reset();
				grooveBox.tableChanged(new TableModelEvent(grooveBox.getModel()));
			}
		});
		buttonPanel.add(reset);
		westPanel.add(buttonPanel, BorderLayout.CENTER);
		
		this.add(westPanel, BorderLayout.WEST);
		final JScrollPane jsp= new JScrollPane(grooveBox);
		this.add(jsp, BorderLayout.CENTER);
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
}
