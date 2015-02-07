package View;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PersonalJPanel extends JPanel {
	
	private static final long serialVersionUID = -7098020132793828090L;
	protected static final Color GRAY= new Color(50, 50, 50);
	protected static final Color WHITE= new Color(255, 255, 255);
	
	protected static final ImageIcon pauseImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Pause.png");
	protected static final ImageIcon playImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Play.png");
	protected static final ImageIcon fwImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "FW.png");
	protected static final ImageIcon rwImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "RW.png");

	public PersonalJPanel(final LayoutManager layout) {
		super(layout);
		this.setBackground(WHITE);
		this.setForeground(GRAY);
	}
}
