package controller.musicplayer;

import java.net.URL;

import controller.Loopable;
import model.LoopManager;
import model.PlayerState;
import model.playlistmanager.ExtendedPlaylistManager;

/**
 * 
 * An extension of the class {@link controller.musicplayer.ClassicMusicPlayer} that add the management of the Loop mode
 * 
 * This class implement the interface {@link controller.Loopable}
 * @author Matteo Gabellini
 *
 */
public class LoopableMusicPlayer extends ClassicMusicPlayer implements Loopable {
	final private LoopManager loopModel;
	
	public LoopableMusicPlayer(final ExtendedPlaylistManager<URL> plManager) {
		super(plManager);
		this.loopModel = new LoopManager();
	}
	
	/**
	 * When song ended and the {@link controller.musicplayer.AbstractMusicPlayer#stop} method invoked
	 * this method if the loop was active the current song will be repeated  
	 */
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
