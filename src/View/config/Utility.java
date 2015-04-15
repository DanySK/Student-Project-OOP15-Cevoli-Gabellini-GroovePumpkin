package View.config;

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
	
	public static final int DOUBLE_CLICK= 2;

	public static final Color DARK_GRAY = new Color(50, 50, 50);
	public static final Color GRAY = new Color(180, 180, 180);
	public static final Color LIGHT_GRAY = new Color(240, 240, 240);
	public static final Color WHITE = new Color(255, 255, 255);
	public static final Color DARK_ORANGE= new Color(255, 94, 0);
	public static final Color ORANGE= new Color(255, 127, 0);
	public static final Color LIGHT_ORANGE= new Color(255, 200, 112);
	public static final Color DARK_GREEN= new Color(0, 130, 50);
	public static final Color RED = new Color(235, 0, 0);
	
	/**
	 * Those are all the Icons applicables over the button
	 * 
	 */
	public static final String DATA= "data";
	public static final String SEPARATOR=  System.getProperty("file.separator");
	
	public static final ImageIcon PAUSE_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR , "Pause.png"));
	public static final ImageIcon PLAY_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "Play.png"));
	public static final ImageIcon FW_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "FW.png"));
	public static final ImageIcon RW_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "RW.png"));
	public static final ImageIcon SAVE_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "Save.png"));
	public static final ImageIcon REC_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "Rec.png"));
	public static final ImageIcon STOP_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "Stop.png"));
	public static final ImageIcon LOOP_ON_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "Loop_ON.png"));
	public static final ImageIcon LOOP_OFF_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "Loop_OFF.png"));
	public static final ImageIcon SHUFFLED_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "Shuffle_ON.png"));
	public static final ImageIcon UNSHUFFLED_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "Shuffle_OFF.png"));
	public static final ImageIcon LOAD_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "load.png"));
	public static final ImageIcon ADD_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "add.png"));
	public static final ImageIcon REMOVE_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "remove.png"));
	
	private Utility() {
	}
	
	/**
	 * 
	 * @param title
	 * @return a built-in TitledBorder
	 */
	public static Border getACompoundTitledBorder(final String title) {

		return new CompoundBorder(new TitledBorder(new SoftBevelBorder(
				SoftBevelBorder.RAISED, WHITE, WHITE), title,
				TitledBorder.ABOVE_TOP, TitledBorder.CENTER), 
				new EmptyBorder(4, 16, 4, 16));
	}
	
	/**
	 * 
	 * @return a built-in CompoundBorder
	 */
	public static Border getADefaultBorder(){
		return new CompoundBorder(new SoftBevelBorder(
				SoftBevelBorder.RAISED, WHITE, WHITE), 
				new EmptyBorder(4, 16, 4, 16));
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
		//Prendo il separatore di sistema
		return SEPARATOR.equals("/") ? String.join("", "file://localhost",
				filePath) : String.join("", "file:/", filePath);
	}
	
	/**
	 * Thank you 
	 * @link{http://stackoverflow.com/questions/4050087/how-to-obtain-the-last-path-segment-of-an-uri}
	 * for the first replacement
	 * 
	 * @param URLPath
	 */
	public static String convertURLPath(final String URLPath){
		
		//.- -> replace all the '-'
		//.*\\d -> remove all the digits and the previous characters
		//([^/]+) -> keep all the characters until the first '/' occurrence
		final String s= URLPath.replaceFirst(".*\\/([^/\\?]+).*", "$1")
				.replaceFirst(".*-([^/\\?]+)", "$1")
				//.replaceFirst(".*\\d([^/\\?]+)", "$1")
				.trim();
		return s.substring(0, s.lastIndexOf('.'));
	}
}
