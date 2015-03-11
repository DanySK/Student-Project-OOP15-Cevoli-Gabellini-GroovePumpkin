package Model;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

/**
 * An Utility class
 * 
 * @author Alessandro
 *
 */

public final class Utility {

	public static final Color GRAY = new Color(50, 50, 50);
	public static final Color L_GRAY = new Color(180, 180, 180);
	public static final Color WHITE = new Color(255, 255, 255);
	public static final Color ORANGE= new Color(255, 127, 0);
	
	/**
	 * Those are all the Icons applicables over the button
	 * 
	 */
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
	public static final ImageIcon LOAD_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "load.png");
	public static final ImageIcon ADD_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "add.png");
	public static final ImageIcon REMOVE_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "remove.png");
	
	private Utility() {
	}
	
	/**
	 * 
	 * @param title
	 * @return a built-in TitledBorder
	 */
	public static Border getACompoundTitledBorder(final String title) {

		return new CompoundBorder(new TitledBorder(new SoftBevelBorder(
				SoftBevelBorder.RAISED, L_GRAY, GRAY), title,
				TitledBorder.ABOVE_TOP, TitledBorder.CENTER), new EmptyBorder(
				4, 16, 4, 16));
	}
	
	/**
	 * 
	 * @return a built-in CompoundBorder
	 */
	public static Border getADefaultBorder(){
		return new CompoundBorder(new SoftBevelBorder(
				SoftBevelBorder.RAISED, L_GRAY, GRAY), new EmptyBorder(6, 16,
				6, 16));
	}
}
