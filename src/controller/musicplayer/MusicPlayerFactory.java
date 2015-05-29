package controller.musicplayer;

import java.net.URL;

import model.playlistmanager.BasicPlaylistManager;
import model.playlistmanager.ShuffablePlaylistFeature;
import model.playlistmanager.choicestrategy.ClassicStrategy;

/**
 * A Factory for creating the different type of music player
 * @author Matteo Gabellini
 *
 */
public final class MusicPlayerFactory {
	
	private MusicPlayerFactory(){		
	}
	
	/** 
	 * Create new classic music player with base function
	 * @return new istance of a classic music player
	 */
	public static MusicPlayer createClassicMusicPlayer(){
		return new ClassicMusicPlayer(new BasicPlaylistManager<URL>(new ClassicStrategy<>()), null);
	}
	
	/**
	 * Create a classic music player with the base function and also the shuffle management
	 * @return new instance of a classic music player
	 */
	public static MusicPlayer createShuffableMusicPlayer(){
		return new ClassicMusicPlayer(new BasicPlaylistManager<URL>(new ClassicStrategy<>()), new ShuffablePlaylistFeature<URL>());
	}
	
	/**
	 * Create a new music player with the base function and also the loop management
	 * @return new loopable music player
	 */
	public static MusicPlayer createLoopableMusicPlayer(){
		return new LoopableMusicPlayer(new BasicPlaylistManager<URL>(new ClassicStrategy<>()), null);
	}
	
	/**
	 * Create a new music player with the base function, the loop and shuffle management
	 * @return new loopable and shuffable music player
	 */
	public static MusicPlayer createLoopableAndShuffableMP(){
		return new LoopableMusicPlayer(new BasicPlaylistManager<URL>(new ClassicStrategy<>()), new ShuffablePlaylistFeature<>());
	}
}
