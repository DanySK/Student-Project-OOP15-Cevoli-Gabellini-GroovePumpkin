package controller.musicplayer;


import java.net.URL;
import java.util.Optional;

import model.PlayerState;
import model.playlistmanager.FeaturesHandled;
import model.playlistmanager.PlaylistFeatures;
import model.playlistmanager.PlaylistManager;

/**
 * A classic music player is a normal music player that at the end of the song goes to the next
 * 
 * This class use the basic implementation of a music player defined in the abstract class {@link controller.musicplayer.AbstractMusicPlayer}
 * and implements the method of the interface {@link controller.musicplayer.PlaylistFeatureCommand} 
 * @author Matteo Gabellini
 *
 */
public class ClassicMusicPlayer extends AbstractMusicPlayer implements PlaylistFeatureCommand{
	private final PlaylistManager<URL> referenceToPL;
	private Optional<PlaylistFeatures<URL>> plFeatures;
	/**
	 * Crete a new instance of ClassicMusicPlayer
	 * @param plManager
	 * 		the playlist manager used from the instance that will be created 
	 * 		if this play list manager is a {@link package model.playlistmanager.ShuffablePlaylistManager}
	 * 		through the method of the interface {@link controller.musicplayer.PlaylistFeatureCommand}
	 * 		the instance can be manage the shuffle mode
	 */
	public ClassicMusicPlayer(final PlaylistManager<URL> plManager, final PlaylistFeatures<URL> features) {
		super(plManager);
		this.referenceToPL = plManager;
		this.plFeatures = Optional.ofNullable(features);
	}
	
	
	/**
	 * This method define the action to do after the song ending
	 * This method was invoked from the stop if the song was finished
	 */
	@Override
	protected void afterSongEnding()  {
		if(this.referenceToPL.getCurrentSongIndex().isPresent()){
			final int previousIndex = this.referenceToPL.getCurrentSongIndex().get();
			this.goToNextSong();	
			if(this.referenceToPL.getCurrentSongIndex().get() != previousIndex){
				this.play();
			}
		}
	}
	
	@Override
	public void setPlaylistFeature(final FeaturesHandled feature,final boolean value){
		if (this.plFeatures.isPresent()) {
			this.plFeatures.get().setFeatureState(feature.getFeatureClass(),
					this.referenceToPL, value);

			if (feature.equals(FeaturesHandled.SHUFFLE)) {
				this.notifyToUpdatable(this.plFeatures.get().isFeatureActive(feature
						.getFeatureClass()) ? PlayerState.SHUFFLED
						: PlayerState.UNSHUFFLED);

			}
		}
	}
}
