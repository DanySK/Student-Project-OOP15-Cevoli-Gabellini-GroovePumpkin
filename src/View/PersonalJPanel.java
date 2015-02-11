package View;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class PersonalJPanel extends JPanel {
	
	private static final long serialVersionUID = -7098020132793828090L;
	protected static final Color GRAY= new Color(50, 50, 50);
	protected static final Color WHITE= new Color(255, 255, 255);
	
	public PersonalJPanel(){
		this.setBackground(WHITE);
		this.setForeground(GRAY);
	}

	public PersonalJPanel(final LayoutManager layout) {
		this();
		this.setLayout(layout);
	}
}
