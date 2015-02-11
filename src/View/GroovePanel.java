package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 * 
 * @author Alessandro
 *
 */
@SuppressWarnings("unused")
public class GroovePanel extends PersonalJPanel {

	private static final long serialVersionUID = 1116768170189928089L;
	private final JTextField timeDialerText= new JTextField("120");
	
	public GroovePanel() {
		super(new BorderLayout());
		final PersonalJPanel westPanel= new PersonalJPanel(new BorderLayout());
		populateWestPanel(westPanel);
		
		
		this.add(westPanel, BorderLayout.WEST);
	}
	
	private void  populateWestPanel(final PersonalJPanel westPanel){
		
		final JLabel timeDialerLabel= new JLabel("Time Dial: ");
		timeDialerLabel.setBackground(WHITE);
		timeDialerLabel.setForeground(GRAY);
		
		final PersonalJPanel timePanel= new PersonalJPanel(new FlowLayout());
		timePanel.add(timeDialerLabel);
		timePanel.add(timeDialerText);
		westPanel.add(timePanel, BorderLayout.NORTH);
		
		final PersonalJPanel buttonPanel= new PersonalJPanel(new BorderLayout());
		final PersonalJButton play= new PersonalJButton(PersonalJButton.playImg);
		final PersonalJButton loop= new PersonalJButton(PersonalJButton.loopImg);
		buttonPanel.add(play, BorderLayout.NORTH);
		buttonPanel.add(loop, BorderLayout.CENTER);
		westPanel.add(buttonPanel, BorderLayout.CENTER);
		
	}
	
	public JTextField getTextField(){
		return timeDialerText;
	}
}
