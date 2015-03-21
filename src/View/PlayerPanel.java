package View;

import static Model.Utility.*;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import controller.MusicPlayer;

public class PlayerPanel extends PersonalJPanel {

	private static final long serialVersionUID = -1634789109356603711L;
	private JSplitPane splitter;
	private PlaylistPanel list;
	private MusicPlayerPanel player;

	public PlayerPanel(final MusicPlayer mp) {
		super(new BorderLayout());
		list = new PlaylistPanel(mp);
		player = new MusicPlayerPanel(mp);
		final JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				list, player);

		splitter.setBackground(WHITE);
		splitter.setForeground(GRAY);
		splitter.setContinuousLayout(true);

		this.add(splitter);
	}

	public PlaylistPanel getPlaylistPanel() {
		return list;
	}

	public MusicPlayerPanel getMusicPlayerPanel() {
		return player;
	}

	public void setMusicPlayerPanel(final MusicPlayerPanel player){
		this.remove(splitter);
		this.player=player;
		this.splitter=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				list, player);
		this.add(splitter);
	}

	public void setPlaylistPanel(final PlaylistPanel list) {
		this.remove(splitter);
		this.list=list;
		this.splitter=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				list, player);
		this.add(splitter);
	}
}
