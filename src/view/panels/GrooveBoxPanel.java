package view.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;

import view.buttons.PersonalJButton;
import view.tables.GrooveBox;
import view.viewModel.GrooveTableModel;
import controller.groovebox.GrooveBoxPlayer;
import static view.buttons.ButtonFactory.*;
import static view.config.Utility.*;
import static view.config.Configuration.*;

/**
 * This class rapresents the GUI space for the groovebox
 * Is divided into a controls panel (where are placed various buttons)
 * and a personalized JTable that rapresent the GrooveBox
 * 
 * @author Alessandro
 *
 */
public class GrooveBoxPanel extends ControllablePane<GrooveBoxPlayer>{

	private static final long serialVersionUID = 1116768170189928089L;
	private final Integer[] items = new Integer[] { 40, 60, 80, 100, 120,
			140, 160, 180 };
	private final JComboBox<Integer> timeDialerOptions = new JComboBox<>(items);
	private GrooveBox grooveBox;
	
	private final PersonalJPanel westPanel = new PersonalJPanel(new BorderLayout(5, 5));
	private final PersonalJPanel timePanel = new PersonalJPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
	private final CmdPane cmdPane;
	private final JScrollPane jsp;
	
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
		timeDialerOptions.addItemListener(i->{
				if(i.getStateChange()==ItemEvent.SELECTED){
					((GrooveBoxPlayer) getController())
						.setTempoInBPM(((Integer)i.getItem()).intValue());
				}
			});
		
		westPanel.setBorder(getADefaultPanelBorder());
		final JLabel timeDialerLabel= new JLabel("Time Dial: ");
		timeDialerLabel.setBackground(WHITE);
		timeDialerLabel.setForeground(DARK_GRAY);
		timePanel.addComponents(timeDialerLabel, timeDialerOptions);
		westPanel.add(timePanel, BorderLayout.NORTH);
		cmdPane= new CmdPane.Builder()
				.setPlay(createButton(PLAY_B, getController(), true))
				.setStop(createButton(STOP_B, getController(), true))
				.setLoop(createButton(LOOP_B, getController(), true))
				.setSave(createButton(SAVE_B, getController(), true))
				.build();
				
		cmdPane.setLayout(new BoxLayout(cmdPane, BoxLayout.Y_AXIS));
		
		addObservers(cmdPane.getWrapper().getPlay().get(), cmdPane.getWrapper().getStop().get());
		final PersonalJButton<GrooveBoxPlayer> reset;
		reset= new PersonalJButton<>(RESET_IMG, "Reset");
		reset.addActionListener(e->{
				controller.reset();
				grooveBox.tableChanged(new TableModelEvent(grooveBox.getModel()));
			});
		cmdPane.add(reset);
		westPanel.add(cmdPane, BorderLayout.CENTER);
		
		jsp= new JScrollPane(grooveBox);
		this.add(westPanel, BorderLayout.WEST);
		this.add(jsp, BorderLayout.CENTER);
	}
	
	/**
	 * 
	 * @return the groove associated to this object
	 */
	public GrooveBox getGrooveBox(){
		return this.grooveBox;
	}
	
	/**
	 * 
	 * @param gb
	 */
	public void setGrooveBox(final GrooveBox gb){
		this.grooveBox=gb;
		this.jsp.removeAll();
		this.jsp.setViewportView(gb);
	}
}
