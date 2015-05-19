package view.panels;

import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;
import static view.config.Configuration.*;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import view.controller.time.TimerController;
import controller.musicplayer.MusicPlayer;

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
	private TimerController tmCtrl;
	
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
		
//		final AbstractStratBtn<Loopable> b1;
//		final AbstractStratBtn<Shuffable> b2;
//		
//		if(mp instanceof Loopable || mp instanceof Shuffable){
//			if(mp instanceof Loopable && mp instanceof Shuffable){
//				
//				b1=createButton(LOOP_B, (Loopable) mp, false);
//				b2=createButton(SHUFFLE_B, (Shuffable) mp, false);
//				
//				player.getCommandPane().add(new CmdPane.Builder()
//				.setLoop(b1)
//				.setShuffle(b2)
//				.build(new FlowLayout(1, -5, 0)));
//				((PersonalJPanel)player.getComponent(0))
//						.add(player.getCommandPane().get(1), BorderLayout.SOUTH);
//				player.addUpdatableObservers(b1, b2);
//			} else if(mp instanceof Loopable){
//				b1=createButton(LOOP_B, (Loopable) mp, false);
//				player.getCommandPane().add(new CmdPane.Builder()
//				.setLoop(b1)
//				.build(new FlowLayout(1, -5, 0)));
//				((PersonalJPanel)player.getComponent(0))
//						.add(player.getCommandPane().get(1), BorderLayout.SOUTH);
//				player.addUpdatableObservers(b1);
//			} else if(mp instanceof Shuffable){
//				b2=createButton(SHUFFLE_B, (Shuffable) mp, false);
//				player.getCommandPane().add(new CmdPane.Builder()
//				.setShuffle(b2)
//				.build(new FlowLayout(1, -5, 0)));
//				((PersonalJPanel)player.getComponent(0))
//						.add(player.getCommandPane().get(1), BorderLayout.SOUTH);
//				player.addUpdatableObservers(b2);
//			}
//		}
		
		this.tmCtrl= new TimerController(mp);
		player.setSongTimeLabel(tmCtrl.getLabel());
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
