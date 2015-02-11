package View;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * A Personalized White&DarkGrey JPanel
 * @author Alessandro
 *
 */

public class PersonalJPanel extends JPanel {
	
	private static final long serialVersionUID = -7098020132793828090L;
	protected static final Color GRAY= new Color(50, 50, 50);
	protected static final Color WHITE= new Color(255, 255, 255);
	
	//final PersonalJPanel emptyR = new PersonalJPanel();
	//final EmptyBorder empty = new EmptyBorder(10, 10, 4, 10);
	
	public PersonalJPanel(){
		super();
		this.setBackground(WHITE);
		this.setForeground(GRAY);
	}

	public PersonalJPanel(final LayoutManager layout) {
		this();
		this.setLayout(layout);
	}
}
