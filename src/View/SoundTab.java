package View;

import java.awt.Color;
import java.lang.management.GarbageCollectorMXBean;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
/**
 * This class creates an already populated JTabbedPane for the SoundFrame class
 * 
 * @author Alessandro
 *
 */
@SuppressWarnings("unused")
public class SoundTab extends JTabbedPane {

	private static final long serialVersionUID = 5184587254735736323L;
	private static final Color GRAY= new Color(50, 50, 50);
	private static final Color WHITE= new Color(255, 255, 255);
	
	public SoundTab(final int posistion) {
		
		super(JTabbedPane.TOP);
		
		final PlayPanel play= new PlayPanel();
		this.add("Play Music", play);
		
		final GroovePanel grooveBox= new GroovePanel();
		this.add("Groove Box", grooveBox);	
		
		this.setBackgroundAt(0, WHITE);
		this.setBackgroundAt(1, WHITE);
		this.setForegroundAt(0, GRAY);
		this.setForegroundAt(1, GRAY);
	}
}
