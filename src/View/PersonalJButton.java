package View;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * A personal JButton class that stores all the imagines for the oject of this class
 * and the color of Background and Foreground
 * 
 * @author Alessandro
 *
 */
public class PersonalJButton extends JButton {

	private static final long serialVersionUID = -7937866815197131576L;

	protected static final Color GRAY = new Color(50, 50, 50);
	protected static final Color WHITE = new Color(255, 255, 255);

	protected static final ImageIcon PAUSE_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Pause.png");
	protected static final ImageIcon PLAY_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Play.png");
	protected static final ImageIcon FW_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "FW.png");
	protected static final ImageIcon RW_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "RW.png");

	protected static final ImageIcon SAVE_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Save.png");
	protected static final ImageIcon REC_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Rec.png");
	protected static final ImageIcon STOP_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Stop.png");
	protected static final ImageIcon LOOP_ON_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "loop_GREEN.png");
	protected static final ImageIcon LOOP_OFF_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "loop_RED.png");

	private String id = new String("");

	public PersonalJButton() {
		this.setBackground(WHITE);
		this.setForeground(GRAY);
		this.setAlignmentX(CENTER_ALIGNMENT);
	}

	/**
	 * 
	 * @param text
	 *            , the text do be applied to setText(text)
	 */
	public PersonalJButton(final String text) {
		this();
		this.setText(text);
	}

	/**
	 * 
	 * @param img
	 *            , an image to be applied to the button
	 */
	public PersonalJButton(final ImageIcon img) {
		this();
		this.setIcon(img);
	}

	/**
	 * 
	 * @param img
	 *            , an image to be applied to the button
	 * @param name
	 *            , a name to be associated to the button
	 */
	public PersonalJButton(final ImageIcon img, final String id) {
		this(img);
		this.id = id;
	}
	
	/**
	 * @return the id of this button
	 */
	public String getName() {
		return id;
	}
	
	/**
	 * @param newID the new ID to be associated with this button
	 */
	public void setName(final String newID) {
		this.id = newID;
	}
}