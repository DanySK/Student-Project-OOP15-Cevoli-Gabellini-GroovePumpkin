package View;

import java.awt.Color;

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
	
	public SoundTab(final int posistion) {
		
		super(JTabbedPane.TOP);
		this.setOpaque(true);
		this.setBackground(new Color(50, 50, 50));
		
		final PlaybackPanel play= new PlaybackPanel();
		this.add("Play Music", play);
		
		final GroovePanel grooveBox= new GroovePanel();
		this.add("Groove Box", grooveBox);	
		
		this.setBackgroundAt(0, new Color(255, 255, 255));
		this.setBackgroundAt(1, new Color(255, 255, 255));
		this.setForegroundAt(0, new Color(50, 50, 50));
		this.setForegroundAt(1, new Color(50, 50, 50));
	}
}
