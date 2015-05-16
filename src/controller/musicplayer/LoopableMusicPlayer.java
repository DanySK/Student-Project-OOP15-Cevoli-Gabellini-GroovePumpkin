package controller.musicplayer;

import java.net.URL;

import controller.Loopable;
import model.LoopManager;
import model.PlayerState;
import model.playlistmanager.ExtendedPlaylistManager;

/**
 * 
 * An extension of a classic music player that add the management of the Loop mode
 * 
 * @author Matteo Gabellini
 *
 */
public class LoopableMusicPlayer extends ClassicMusicPlayer implements Loopable {
	final private LoopManager loopModel;
	
	public LoopableMusicPlayer(final ExtendedPlaylistManager<URL> plManager) {
		super(plManager);
		this.loopModel = new LoopManager();
	}
	
	@Override
	protected void afterSongEnding() {
		if (this.isLoopModeActive()) {
			this.play();
		} else {
			super.afterSongEnding();
		}
	}

	@Override
	public synchronized void setLoop(final boolean value) {
		this.loopModel.setLoopMode(value);
		this.notifyToUpdatable(value ? PlayerState.LOOPED : PlayerState.UNLOOPED);
	}

	@Override
	public boolean isLoopModeActive() {
		return this.loopModel.isLoopModeActive();
	}
	
}
