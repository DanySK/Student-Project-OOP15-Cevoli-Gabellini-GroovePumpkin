package View;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

/**
 * A personal JButton class that stores all the imagines for the oject of this
 * class and the color of Background and Foreground; It manages also the border
 * of the button
 * 
 * @author Alessandro
 *
 */
public class PersonalJButton extends JButton {

	private static final long serialVersionUID = -7937866815197131576L;

	public static final Color GRAY = new Color(50, 50, 50);
	public static final Color L_GRAY = new Color(180, 180, 180);
	public static final Color WHITE = new Color(255, 255, 255);

	public static final ImageIcon PAUSE_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Pause.png");
	public static final ImageIcon PLAY_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Play.png");
	public static final ImageIcon FW_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "FW.png");
	public static final ImageIcon RW_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "RW.png");

	public static final ImageIcon SAVE_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Save.png");
	public static final ImageIcon REC_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Rec.png");
	public static final ImageIcon STOP_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Stop.png");
	public static final ImageIcon LOOP_ON_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "loop_GREEN.png");
	public static final ImageIcon LOOP_OFF_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "loop_RED.png");
	public static final ImageIcon SHUFFLE_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "shuffle.png");
	

	private String id = new String("");

	/**
	 * Basic constructor for the personal JButton
	 */
	public PersonalJButton() {

		this.setBorder(new CompoundBorder(new SoftBevelBorder(
				SoftBevelBorder.RAISED, L_GRAY, GRAY), new EmptyBorder(6, 16,
				6, 16)));

		this.setBackground(WHITE);
		this.setForeground(GRAY);
		this.setAlignmentX(CENTER_ALIGNMENT);
	}

	/**
	 * @param text to be shown upon the button
	 */
	public PersonalJButton(final String text) {
		this();
		this.setText(text);
	}

	/**
	 * @param img to be applied to the button
	 */
	public PersonalJButton(final ImageIcon img) {
		this();
		this.setIcon(img);
	}

	/**
	 * @param img to be applied to the button
	 * @param ID to be associated to the button
	 */
	public PersonalJButton(final ImageIcon img, final String id) {
		this(img);
		this.id = id;
	}

	public static Border getACompoundTitledBorder(final String title) {

		return new CompoundBorder(new TitledBorder(new SoftBevelBorder(
				SoftBevelBorder.RAISED, L_GRAY, GRAY), title,
				TitledBorder.ABOVE_TOP, TitledBorder.CENTER), new EmptyBorder(
				4, 16, 4, 16));
	}

	/**
	 * 
	 * @param name
	 *            to be shown on the TitledBorder
	 */

	public void showTitle(final String name) {

		this.setBorder(getACompoundTitledBorder(name));
	}

	/**
	 * 
	 * @return the TitledBorder if it exists, otherways null
	 */
	public TitledBorder getTitledBorder() {

		if (this.getBorder() instanceof CompoundBorder
				&& ((CompoundBorder) this.getBorder())
						.getOutsideBorder() instanceof TitledBorder){
			return ((TitledBorder) ((CompoundBorder) this.getBorder())
					.getOutsideBorder());
		}
		
		return null;
	}

	/**
	 * @return the id of this button
	 */
	public String getID() {
		return id;
	}

	/**
	 * @param newID the new ID to be associated with this button
	 */
	public void setID(final String newID) {
		this.id = newID;
	}
}