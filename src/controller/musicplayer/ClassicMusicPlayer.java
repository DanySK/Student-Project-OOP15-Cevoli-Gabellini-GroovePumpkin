package controller.musicplayer;


import java.net.URL;

import model.PlayerState;
import model.playlistmanager.ExtendedPlaylistManager;
import model.playlistmanager.ShuffablePlaylistManager;

public class ClassicMusicPlayer extends AbstractMusicPlayer implements Shuffable{
	private final ExtendedPlaylistManager<URL> referenceToPL;
	
	public ClassicMusicPlayer(final ExtendedPlaylistManager<URL> plManager) {
		super(plManager);
		this.referenceToPL = plManager;
	}

	@Override
	protected void afterSongEnding()  {
		this.goToNextSong();				
		this.play();
	}

	@Override
	public void setShuffleMode(final boolean active) {
		this.referenceToPL.setFeatureState(ShuffablePlaylistManager.class,active);	
		this.notifyToUpdatable(this.referenceToPL.isFeatureActive(ShuffablePlaylistManager.class)? PlayerState.SHUFFLED : PlayerState.UNSHUFFLED);
	}
}
