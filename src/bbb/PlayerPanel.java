package bbb;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;

import static bbb.Utility.*;
import static javax.swing.JSplitPane.*;
import controller.MusicPlayer;

/**
 * This class rapresents the main player panel,
 * which is divided into 2 other panels:
 * -PlaylistPanel;
 * -MusicPlayerPanel;
 * Both have their specifical functions 
 * 
 * @author Alessandro
 *
 */
public class PlayerPanel extends PersonalJPanel {

	private static final long serialVersionUID = -1634789109356603711L;
	private JSplitPane splitter;
	private PlaylistPanel list;
	private MusicPlayerPanel player;
	
	/**
	 * 
	 * 
	 * @param mp
	 */
	public PlayerPanel(final MusicPlayer mp) {
		super(new BorderLayout());
		list = new PlaylistPanel(mp);
		player = new MusicPlayerPanel(mp);
		splitter = new JSplitPane(HORIZONTAL_SPLIT, list, player);
		
		splitter.setBackground(WHITE);
		splitter.setForeground(DARK_GRAY);
		splitter.setContinuousLayout(true);

		this.add(splitter);
	}
	
	/**
	 * 
	 * @return The PlaylistPanel attached to this object
	 */
	public PlaylistPanel getPlaylistPanel() {
		return list;
	}

	/**
	 * 
	 * @return The MusicPlayerPanel attached to this object 
	 */
	public MusicPlayerPanel getMusicPlayerPanel() {
		return player;
	}
	
	/**
	 * Set a new MusicPlayerPanel to attach to this object
	 * 
	 * @param player
	 */
	public void setMusicPlayerPanel(final MusicPlayerPanel player){
		this.remove(splitter);
		this.player=player;
		this.splitter=new JSplitPane(HORIZONTAL_SPLIT, list, player);
		this.add(splitter);
	}
	
	/**
	 * Set a new PlaylistPanel to attach to this object
	 * 
	 * @param list
	 */
	public void setPlaylistPanel(final PlaylistPanel list) {
		this.remove(splitter);
		this.list=list;
		this.splitter=new JSplitPane(HORIZONTAL_SPLIT, list, player);
		this.add(splitter);
	}
}
