package View;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import controller.GrooveBoxController;
import controller.MusicPlayer;
/**
 * This class creates an already populated JTabbedPane for the SoundFrame class
 * 
 * @author Alessandro
 *
 */
public class SoundTab extends JTabbedPane {

	private static final long serialVersionUID = 5184587254735736323L;
	private static final Color GRAY= new Color(50, 50, 50);
	private static final Color WHITE= new Color(255, 255, 255);
	
	public SoundTab(final int posistion, final MusicPlayer mp, final GrooveBoxController groove) {
		
		super(JTabbedPane.TOP);
		
		final PersonalJPanel play= new PersonalJPanel(new BorderLayout());
		
		final JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				new PlaylistPanel(mp), new MusicPlayerPanel(mp));
		splitter.setBackground(WHITE);
		splitter.setForeground(GRAY);
		splitter.setContinuousLayout(true);
		
		play.add(splitter);
		this.add("Play Music", play);
		
		final GroovePanel grooveBox= new GroovePanel(groove);
		this.add("Groove Box", grooveBox);	
		
		this.setBackgroundAt(0, WHITE);
		this.setBackgroundAt(1, WHITE);
		this.setForegroundAt(0, GRAY);
		this.setForegroundAt(1, GRAY);
	}
}
