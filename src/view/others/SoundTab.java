package view.others;

import java.awt.BorderLayout;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.panels.GrooveBoxPanel;
import view.panels.PlayerPanel;
import controller.musicplayer.MusicPlayer;
import controller.groovebox.GrooveBoxPlayer;
import static view.config.Configuration.*;

/**
 * This class creates an already populated JTabbedPane for the SoundFrame class
 * Composed by a player panel and a Groovebox Panel
 * 
 * @author Alessandro
 *
 */
public class SoundTab extends JTabbedPane {

	private static final long serialVersionUID = 5184587254735736323L;
	private GrooveBoxPanel groovebox;
	private PlayerPanel player;
	
	public SoundTab(final MusicPlayer mp, final GrooveBoxPlayer groove) {
		
		super(JTabbedPane.TOP);
		this.setDoubleBuffered(true);
		this.setFocusable(false);
		this.setForeground(DARK_GRAY);
		final PlayerPanel play= new PlayerPanel(mp);
		this.add("Play Music", play);
		
		final GrooveBoxPanel grooveBox= new GrooveBoxPanel(groove);
		this.add("Groove Box", grooveBox);	
		
		this.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if(((SoundTab) e.getSource()).getSelectedIndex()>=1){
					try{
						mp.stop();
					} catch(Exception ex){
						
					}
				} else {
					try{
						groove.stop();
					} catch(Exception ex){
						
					}
				}
			}
		});
		
		
		this.setBackgroundAt(0, WHITE);
		this.setBackgroundAt(1, WHITE);
		this.setForegroundAt(0, DARK_GRAY);
		this.setForegroundAt(1, DARK_GRAY);
	}
	
	/**
	 * 
	 * @return
	 */
	public GrooveBoxPanel getGroovePanel(){
		return groovebox;
	}
	/**
	 * 
	 * @return
	 */
	public PlayerPanel getPlaylistPanel(){
		return player;
	}
	
	/**
	 * 
	 * @param player
	 */
	public void setPlayerPanel(final PlayerPanel player){
		this.remove(this.player);
		this.player=player;
		this.add(this.player, BorderLayout.NORTH);
	}
	
	/**
	 * 
	 * @param groovebox
	 */
	public void setGroovePanel(final GrooveBoxPanel groovebox){
		this.remove(this.groovebox);
		this.groovebox=groovebox;
		this.add(this.groovebox, BorderLayout.NORTH);
	}
}
