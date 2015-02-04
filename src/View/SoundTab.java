package View;

import java.awt.Color;

import javax.swing.JTabbedPane;
/**
 * This class creates an already populated JTabbedPane for the SoundFrame class
 * 
 * @author Alessandro
 *
 */
public class SoundTab extends JTabbedPane {

	private static final long serialVersionUID = 5184587254735736323L;
	
	public SoundTab(final int posistion) {
		
		super(JTabbedPane.TOP);
		this.setBackground(new Color(255, 255, 255));
		
		final PlaybackPanel play= new PlaybackPanel();
		play.setBackground(new Color(255, 255, 255));
		this.add("Play Music", play);
		
		final GroovePanel grooveBox= new GroovePanel();
		
		this.add("Groove Box", grooveBox);	
		
	}
}
