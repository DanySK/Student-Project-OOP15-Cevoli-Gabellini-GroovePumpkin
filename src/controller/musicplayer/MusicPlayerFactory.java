package controller.musicplayer;

import java.net.URL;

import model.playlistmanager.ClassicPlaylistManager;
import model.playlistmanager.ShuffablePlaylistManager;

/**
 * A Factory for creating the different type of music player
 * @author Matteo Gabellini
 *
 */
public class MusicPlayerFactory {

	public static MusicPlayer createClassicMusicPlayer(){
		return new ClassicMusicPlayer(new ClassicPlaylistManager<URL>());
	}
	
	public static MusicPlayer createShuffableMusicPlayer(){
		return new ClassicMusicPlayer(new ShuffablePlaylistManager<>());
	}
	
	public static MusicPlayer createLoopableMusicPlayer(){
		return new LoopableMusicPlayer(new ClassicPlaylistManager<>());
	}
	
	public static MusicPlayer createLoopableAndShuffableMP(){
		return new LoopableMusicPlayer(new ShuffablePlaylistManager<URL>());
	}
}
