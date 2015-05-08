package view.config;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class Configuration {

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
	public static final ImageIcon SHUF_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "Shuffle_ON.png"));
	public static final ImageIcon UNSHUF_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "Shuffle_OFF.png"));
	public static final ImageIcon LOAD_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "load.png"));
	public static final ImageIcon ADD_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "add.png"));
	public static final ImageIcon REMOVE_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "remove.png"));
	public static final ImageIcon RESET_IMG = new ImageIcon(
			String.join("", DATA, SEPARATOR, "Reset.png"));

	public Configuration() {
		
		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// } catch (final Exception e) {
		//
		// }

		UIManager.put("Button.select", LIGHT_GRAY);
		UIManager.put("TabbedPane.selected", WHITE);
		UIManager.put("TabbedPane.highlight", WHITE);
		UIManager.put("TabbedPane.focus", WHITE);
		UIManager.put("TabbedPane.selectHighlight", WHITE);
		UIManager.put("TabbedPane.background", DARK_GRAY);
		UIManager.put("TabbedPane.foreground", WHITE);
		UIManager.put("TabbedPane.lightHighlight", WHITE);
		UIManager.put("TabbedPane.shadow", GRAY);
		UIManager.put("TabbedPane.darkShadow", DARK_GRAY);
		UIManager.put("Frame.background", DARK_GRAY);
		UIManager.put("ProgressBar.selectionForeground", Color.black);
		UIManager.put("ProgressBar.selectionBackground", Color.black);

	}

}
