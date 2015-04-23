package view.panels;

import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import static view.config.Utility.*;

/**
 * A Personalized White&DarkGrey JPanel, 
 * with a personalizable Layout
 * 
 * @author Alessandro
 *
 */

public class PersonalJPanel extends JPanel {

	private static final long serialVersionUID = -7098020132793828090L;
	
	/**
	 * A basic constructor for a White&Gray panel
	 * 
	 */
	public PersonalJPanel() {
		super();
		this.setBackground(WHITE);
		this.setForeground(DARK_GRAY);
	}
	
	/**
	 * A basic constructor for a White&Gray panel
	 * with the given layout
	 * 
	 * @param layout
	 */
	public PersonalJPanel(final LayoutManager layout) {
		this();
		this.setLayout(layout);
	}
	
	/**
	 * This method set up an already choosen border 
	 * (CompoundBorder decorated with ad EtchedBorder)
	 */
	public void setBuiltInBorder(){
		this.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), 
				new EmptyBorder(3, 3, 3, 3)));
	}
}
