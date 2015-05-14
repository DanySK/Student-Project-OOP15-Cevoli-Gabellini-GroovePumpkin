package test;

import static org.junit.Assert.*;

import java.net.URL;

import controller.musicplayer.MusicPlayer;
import controller.musicplayer.MusicPlayerFactory;
import controller.musicplayer.Shuffable;
import model.playlistmanager.ClassicPlaylistManager;
import model.playlistmanager.ExtendedPlaylistManager;
import model.playlistmanager.ShuffablePlaylistManager;

public class PlaylistFeatureTest {
	@org.junit.Test
	public void testControls() {
		ExtendedPlaylistManager<URL> plfeatures = new ShuffablePlaylistManager<>(new ClassicPlaylistManager<>());
		System.out.println("Classe: " + plfeatures.getClass());
		assertEquals(plfeatures.getClass() , ShuffablePlaylistManager.class);
		assertNotEquals(plfeatures.getClass(), ClassicPlaylistManager.class);
		
		MusicPlayer MP = MusicPlayerFactory.createLoopableAndShuffableMP();
		assertEquals(MP instanceof Shuffable, true);
	}
}
