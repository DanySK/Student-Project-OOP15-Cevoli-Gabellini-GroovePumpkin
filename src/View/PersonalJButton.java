package View;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PersonalJButton extends JButton {

	private static final long serialVersionUID = -7937866815197131576L;

	protected static final Color GRAY = new Color(50, 50, 50);
	protected static final Color WHITE = new Color(255, 255, 255);

	protected static final ImageIcon pauseImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Pause.png");
	protected static final ImageIcon playImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Play.png");
	protected static final ImageIcon fwImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "FW.png");
	protected static final ImageIcon rwImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "RW.png");

	protected static final ImageIcon saveImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Save.png");
	protected static final ImageIcon recImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Rec.png");
	protected static final ImageIcon stopImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Stop.png");
	protected static final ImageIcon loopImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "loop(1).png");

	public PersonalJButton() {
		this.setBackground(WHITE);
		this.setForeground(GRAY);
	}

	public PersonalJButton(final String name) {
		this();
		this.setText(name);
	}

	public PersonalJButton(final ImageIcon img) {
		this();
		this.setIcon(img);
	}

}