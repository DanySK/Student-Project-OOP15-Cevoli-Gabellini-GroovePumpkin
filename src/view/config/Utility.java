package view.config;

import static model.PlayerState.*;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import controller.MusicPlayer;
import static view.config.Configuration.*;

/**
 * An Utility class
 * 
 * @author Alessandro
 * @author Matteo Gabellini
 *
 */

public final class Utility {
	
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
	 * This method set up an already chosen border 
	 * (CompoundBorder decorated with ad EtchedBorder)
	 * 
	 * @return a built-in Border
	 */
	public static Border getADefaultPanelBorder(){
		return new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), 
				new EmptyBorder(3, 3, 3, 3));
	}
	
	/**
	 * 
	 * @return a built-in CompoundBorder
	 */
	public static Border getADefaultButtonBorder(){
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
				.replace('_', ' ')
				//.replaceFirst(".*\\d([^/\\?]+)", "$1")
				.trim();
		return s.substring(0, s.lastIndexOf('.'));
	}
	
	/**
	 * Return a play adapter for this class.
	 * Bind the SPACE bar so that it will play/pause
	 * the running song
	 * 
	 * @return keyAdp if controller is not null, otherwise null
	 */
	public KeyAdapter getPlayAdapter(final MusicPlayer controller){
		 
		if(controller!= null){
			final KeyAdapter keyAdp=new KeyAdapter() {
				@Override
				public void keyPressed(final KeyEvent e) {
					if(e.getKeyCode()== KeyEvent.VK_SPACE){
						if(controller.getState().equals(RUNNING)){
							controller.pause();
						} else if(controller.getState().equals(PAUSED)){
							controller.play();
						}
					}
				}
			};
			return keyAdp;
		}
		return null;
	}
	
	public static boolean checkObj(final Object o){
		return o!=null;
	}
}
