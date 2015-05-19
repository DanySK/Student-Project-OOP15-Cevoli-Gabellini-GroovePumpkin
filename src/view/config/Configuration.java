package view.config;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public final class Configuration {

	public static final int DOUBLE_CLICK= 2;
	public static final String SEP=  System.getProperty("file.separator");
	
	public static final Color DARK_GRAY = new Color(50, 50, 50);
	public static final Color GRAY = new Color(180, 180, 180);
	public static final Color LIGHT_GRAY = new Color(240, 240, 240);
	public static final Color WHITE = new Color(255, 255, 255);
	public static final Color DARK_ORANGE= new Color(255, 94, 0);
	public static final Color ORANGE= new Color(255, 127, 0);
	public static final Color LIGHT_ORANGE= new Color(255, 200, 112);
	public static final Color DARK_GREEN= new Color(0, 130, 50);
	public static final Color RED = new Color(235, 0, 0);
	
	
	private static final String PATH= "/icons/";
	private final static Configuration SINGLETON = new Configuration();
	
	/**
	 * Those are all the Icons applicables over the button
	 * 
	 */
	private final Icon pauseImg;
	private final Icon playImg;
	private final Icon fwImg;
	private final Icon bwImg;
	private final Icon saveImg;
	private final Icon recImg;
	private final Icon stopImg;
	private final Icon loopOnImg;
	private final Icon loopOffImg;
	private final Icon shuffleImg;
	private final Icon unshuffleImg;
	private final Icon loadImg;
	private final Icon addImg;
	private final Icon removeImg;
	private final Icon resetImg;

	private Configuration() {

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
//		
		pauseImg = createIcon(String.join("", PATH, "Pause.png"));
		playImg = createIcon(String.join("", PATH, "Play.png"));
		fwImg = createIcon(String.join("", PATH, "FW.png"));
		bwImg = createIcon(String.join("", PATH, "RW.png"));
		saveImg = createIcon(String.join("", PATH, "Save.png"));
		recImg = createIcon(String.join("", PATH, "Rec.png"));
		stopImg = createIcon(String.join("", PATH, "Stop.png"));
		loopOnImg = createIcon(String.join("", PATH, "Loop_ON.png"));
		loopOffImg = createIcon(String.join("", PATH, "Loop_OFF.png"));
		shuffleImg = createIcon(String.join("", PATH, "Shuffle_ON.png"));
		unshuffleImg = createIcon(String.join("", PATH, "Shuffle_OFF.png"));
		loadImg = createIcon(String.join("", PATH, "Load.png"));
		addImg = createIcon(String.join("", PATH, "Add.png"));
		removeImg = createIcon(String.join("", PATH, "Remove.png"));
		resetImg = createIcon(String.join("", PATH, "Reset.png"));
	}
	
	
	
	/** Returns an ImageIcon, or null if the path was invalid. */
	private Icon createIcon(final String path) {
		final InputStream input= this.getClass().getResourceAsStream(path);
		Icon img= null;
		if(input!=null){
			try {
				img= new ImageIcon( ImageIO.read(input));
				input.close();
			} catch (IOException e) {
				Utility.showErrorDialog(null, e.getMessage());
			}
		}
		
		return img== null? new MissingIcon() : img;
	}

	/**
	 * @return the pauseImg
	 */
	public Icon getPauseImage() {
		return pauseImg;
	}

	/**
	 * @return the playImg
	 */
	public Icon getPlayImage() {
		return playImg;
	}

	/**
	 * @return the fwImg
	 */
	public Icon getFwImage() {
		return fwImg;
	}

	/**
	 * @return the bwImg
	 */
	public Icon getBwImage() {
		return bwImg;
	}

	/**
	 * @return the saveImg
	 */
	public Icon getSaveImage() {
		return saveImg;
	}

	/**
	 * @return the recImg
	 */
	public Icon getRecImage() {
		return recImg;
	}

	/**
	 * @return the stopImg
	 */
	public Icon getStopImage() {
		return stopImg;
	}

	/**
	 * @return the loopOnImg
	 */
	public Icon getLoopOnImage() {
		return loopOnImg;
	}

	/**
	 * @return the loopOffImg
	 */
	public Icon getLoopOffImage() {
		return loopOffImg;
	}

	/**
	 * @return the shuffleImg
	 */
	public Icon getShuffleImage() {
		return shuffleImg;
	}

	/**
	 * @return the unshuffleImg
	 */
	public Icon getUnshuffleImage() {
		return unshuffleImg;
	}

	/**
	 * @return the loadImg
	 */
	public Icon getLoadImage() {
		return loadImg;
	}

	/**
	 * @return the addImg
	 */
	public Icon getAddImage() {
		return addImg;
	}

	/**
	 * @return the removeImg
	 */
	public Icon getRemoveImage() {
		return removeImg;
	}

	/**
	 * @return the resetImg
	 */
	public Icon getResetImage() {
		return resetImg;
	}

	public static Configuration getConfig(){
		return Configuration.SINGLETON;
	}

}
