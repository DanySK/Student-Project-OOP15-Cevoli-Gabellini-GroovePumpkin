package Model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

/**
 * An Utility class
 * 
 * @author Alessandro
 * @author Matteo Gabellini
 *
 */

public final class Utility {

	public static final Color GRAY = new Color(50, 50, 50);
	public static final Color DARK_GRAY = new Color(50, 50, 50);
	public static final Color L_GRAY = new Color(180, 180, 180);
	public static final Color WHITE = new Color(255, 255, 255);
	public static final Color ORANGE= new Color(255, 127, 0);
	public static final Color DARK_GREEN= new Color(0, 130, 50);
	public static final Color RED = new Color(235, 0, 0);
	
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
	
	/**
	 * A static method that show a popup error dialog
	 * 
	 * @param p
	 * @param text
	 */
	public static void showErrorDialog(final Component p, final String text){
		JOptionPane.showMessageDialog(p, text, "Error Message",	JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * 
	 * @param filePath
	 * @return The URL built from the given path
	 */
	public static String anURLPathBuilder(final String filePath){
		//System.out.println(filePath);
		//Prendo il separatore di sistema
		final String separator = System.getProperty("file.separator");
		return separator.equals("/") ? String.join("", "file://localhost",
				filePath) : String.join("", "file:/", filePath);
	}
	
	/**
	 * Thank you 
	 * @link{http://stackoverflow.com/questions/4050087/how-to-obtain-the-last-path-segment-of-an-uri}
	 * for the first replacement and 
	 * @link{http://stackoverflow.com/questions/3674930/java-regex-meta-character-and-ordinary-dot}
	 * for '.' removeal
	 * 
	 * @param URLPath
	 */
	public static String convertURLPath(final String URLPath){
		
		//.- -> replace all the '-'
		//.*\\d -> remove all the digits and the previous characters
		//([^/]+) -> keep all the characters until the first '/' occurrence
		final String s= URLPath.replaceFirst(".*\\/([^/]+).*", "$1")
				.replaceFirst(".-([^/]+)", "$1")
				.replaceFirst(".*\\d([^/]+)", "$1")
				.trim();
		return s.substring(0, s.lastIndexOf("."));
	}
}
