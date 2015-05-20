package controller.musicplayer;


import java.net.URL;

import model.PlayerState;
import model.playlistmanager.ExtendedPlaylistManager;
import model.playlistmanager.ShuffablePlaylistManager;

/**
 * A classic music player is a normal music player that at the end of the song goes to the next
 * 
 * This class use the basic implementation of a music player defined in the abstract class {@link controller.musicplayer.AbstractMusicPlayer}
 * and implements the method of the interface {@link controller.musicplayer.Shuffable} 
 * @author Matteo Gabellini
 *
 */
public class ClassicMusicPlayer extends AbstractMusicPlayer implements Shuffable{
	private final ExtendedPlaylistManager<URL> referenceToPL;
	
	/**
	 * Crete a new instance of ClassicMusicPlayer
	 * @param plManager
	 * 		the playlist manager used from the instance that will be created 
	 * 		if this play list manager is a {@link package model.playlistmanager.ShuffablePlaylistManager}
	 * 		through the method of the interface {@link controller.musicplayer.Shuffable}
	 * 		the instance can be manage the shuffle mode
	 */
	public ClassicMusicPlayer(final ExtendedPlaylistManager<URL> plManager) {
		super(plManager);
		this.referenceToPL = plManager;
	}
	
	/**
	 * This method define the action to do after the song ending
	 * This method was invoked from the stop if the song was finished
	 */
	@Override
	protected void afterSongEnding()  {
		if(this.referenceToPL.getCurrentSongIndex().isPresent() && this.referenceToPL.getCurrentSongIndex().get() != this.getPlayList().size() - 1){
			this.goToNextSong();		
			this.play();
		}
	}
	
	/**
	 * see {@link controller.musicplayer.Shuffled}
	 */
	@Override
	public void setShuffleMode(final boolean active) {
		this.referenceToPL.setFeatureState(ShuffablePlaylistManager.class,active);	
		this.notifyToUpdatable(this.referenceToPL.isFeatureActive(ShuffablePlaylistManager.class)? PlayerState.SHUFFLED : PlayerState.UNSHUFFLED);
	}
}
