package controller.musicplayer;

import java.net.URL;

import model.LoopManager;
import model.PlayerState;
import model.playlistmanager.ExtendedPlaylistManager;
import model.playlistmanager.PlaylistManager;

/**
 * 
 * A decoration of a MusicPlayer that add the management of the Loop mode
 * 
 * @author Matteo Gabellini
 *
 */
public class LoopableMusicPlayer extends BasicMusicPlayer implements Loopable {
	private LoopManager loopModel;
	
	public LoopableMusicPlayer(ExtendedPlaylistManager<URL> plManager) {
		super(plManager);
		this.loopModel = new LoopManager();
	}
	
	@Override
	protected void afterSongEnding() {
		if (!this.isLoopModeActive()) {
			super.afterSongEnding();
		} else {
			this.play();
		}
	}

	@Override
	public synchronized void setLoop(boolean value) {
		this.loopModel.setLoopMode(value);
		this.notifyToUpdatable(value ? PlayerState.LOOPED : PlayerState.UNLOOPED);
	}

	@Override
	public boolean isLoopModeActive() {
		return this.loopModel.isLoopModeActive();
	}
	
}
