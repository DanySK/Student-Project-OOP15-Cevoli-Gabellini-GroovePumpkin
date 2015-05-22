package test;

import static org.junit.Assert.*;

import java.net.URL;

import controller.musicplayer.MusicPlayer;
import controller.musicplayer.MusicPlayerFactory;
import controller.musicplayer.PlaylistFeatureCommand;
import model.playlistmanager.ClassicPlaylistManager;
import model.playlistmanager.ExtendedPlaylistManager;
import model.playlistmanager.ShuffablePlaylistFeature;

public class PlaylistFeatureTest {
	@org.junit.Test
	public void testControls() {
		ExtendedPlaylistManager<URL> plfeatures = new ShuffablePlaylistFeature<>(new ClassicPlaylistManager<>());
		System.out.println("Classe: " + plfeatures.getClass());
		assertEquals(plfeatures.getClass() , ShuffablePlaylistFeature.class);
		assertNotEquals(plfeatures.getClass(), ClassicPlaylistManager.class);
		
		MusicPlayer MP = MusicPlayerFactory.createLoopableAndShuffableMP();
		assertEquals(MP instanceof PlaylistFeatureCommand, true);
	}
}
