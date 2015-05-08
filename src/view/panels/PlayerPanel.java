package view.panels;

import java.awt.BorderLayout;
import javax.swing.JSplitPane;
//import view.unused.TimerController;
import controller.MusicPlayer;
import static javax.swing.JSplitPane.*;
import static view.config.Configuration.*;

/**
 * This class rapresents the main player panel, which is divided into 2 other
 * panels: -PlaylistPanel; -MusicPlayerPanel; Both have their specifical
 * functions
 * 
 * @author Alessandro
 *
 */
public class PlayerPanel extends PersonalJPanel {

	private static final long serialVersionUID = -1634789109356603711L;
	private JSplitPane splitter;
	private PlaylistPanel playlist;
	private MusicPlayerPanel player;
	//private TimerController tmCtrl;
	
	/**
	 * Basic constructor that accept a MusicPlayer controller type
	 * 
	 * @param mp
	 */
	public PlayerPanel(final MusicPlayer mp) {
		super(new BorderLayout());
		playlist = new PlaylistPanel(mp);
		player = new MusicPlayerPanel(mp);
		splitter = new JSplitPane(HORIZONTAL_SPLIT, playlist, player);
		splitter.setBackground(WHITE);
		splitter.setForeground(DARK_GRAY);
		splitter.setContinuousLayout(true);
		this.add(splitter);

//		this.tmCtrl= new TimerController(mp);
//		player.setProgressBar(tmCtrl.getProgressbar());
//		player.getProgressBar().setVisible(true);
//		player.setSongTimeLabel(tmCtrl.getLabel());
	}

	/**
	 * 
	 * @return The PlaylistPanel attached to this object
	 */
	public PlaylistPanel getPlaylistPanel() {
		return playlist;
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
	public void setMusicPlayerPanel(final MusicPlayerPanel player) {
		this.remove(splitter);
		this.player = player;
		this.splitter = new JSplitPane(HORIZONTAL_SPLIT, playlist, player);
		this.add(splitter);
	}

	/**
	 * Set a new PlaylistPanel to attach to this object
	 * 
	 * @param playlist
	 */
	public void setPlaylistPanel(final PlaylistPanel playlist) {
		this.remove(splitter);
		this.playlist = playlist;
		this.splitter = new JSplitPane(HORIZONTAL_SPLIT, playlist, player);
		this.add(splitter);
	}
}
