package View;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * A Personalized White&DarkGrey JPanel
 * 
 * @author Alessandro
 *
 */

public class PersonalJPanel extends JPanel {

	private static final long serialVersionUID = -7098020132793828090L;
	protected static final Color GRAY = new Color(50, 50, 50);
	protected static final Color WHITE = new Color(255, 255, 255);

	public PersonalJPanel() {
		super();
		this.setBackground(WHITE);
		this.setForeground(GRAY);
		//this.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(3, 3, 3, 3)));
		//new SoftBevelBorder(SoftBevelBorder.RAISED)
	}

	public PersonalJPanel(final LayoutManager layout) {
		this();
		this.setLayout(layout);
	}
}
