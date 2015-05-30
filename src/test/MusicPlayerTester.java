package test;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import controller.musicplayer.MusicPlayer;
import controller.musicplayer.MusicPlayerFactory;

/**
 * This is an automatic test with JUnit for the MusicPlayer
 * 
 * @author Matteo Gabellini
 *
 */
public class MusicPlayerTester {
	private static final String FIRST_SAMPLED_SONG = "/songForTest/sampled/Prova 1.wav";
	private static final String SECOND_SAMPLED_SONG = "/songForTest/sampled/Prova 2.wav";
	private static final String FIRST_MIDI_SONG = "/songForTest/midi/chango.mid";
	private static final String SECOND_MIDI_SONG = "/songForTest/midi/jashisth.mid";
	
	
	
	@org.junit.Test
	public void testSampledMusicPlayer() {
		final MusicPlayer lettore = MusicPlayerFactory.createClassicMusicPlayer();
		try {
			lettore.addSong(new URL("file:" + System.getProperty("user.dir") + FIRST_SAMPLED_SONG));
			lettore.addSong(new URL("file:" + System.getProperty("user.dir") + SECOND_SAMPLED_SONG));
			lettore.addSong(new URL("file:" + System.getProperty("user.dir") + FIRST_MIDI_SONG));
			lettore.addSong(new URL("file:" + System.getProperty("user.dir") + SECOND_MIDI_SONG));
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		List<URL> checkPlaylist = lettore.getPlayList();
		
		this.testReproduction(lettore, 2000);
		assertTrue(lettore.getCurrentSong().get().equals(checkPlaylist.get(0)));
	
		lettore.goToNextSong();

		this.testReproduction(lettore, 2000);
		assertTrue(lettore.getCurrentSong().get().equals(checkPlaylist.get(1)));
		
		lettore.goToPreviousSong();
		this.testReproduction(lettore, 2000);
		assertTrue(lettore.getCurrentSong().get().equals(checkPlaylist.get(0)));
		lettore.goToSong(2);
		this.testReproduction(lettore, 2000);
		assertTrue(lettore.getCurrentSong().get().equals(checkPlaylist.get(2)));
		lettore.goToPreviousSong();
		assertTrue(lettore.getCurrentSong().get().equals(checkPlaylist.get(1)));
		lettore.goToNextSong();
		lettore.goToNextSong();
		assertTrue(lettore.getCurrentSong().get().equals(checkPlaylist.get(3)));
		this.testReproduction(lettore, 2000);
		lettore.goToNextSong();
		lettore.goToNextSong();
		assertTrue(lettore.getCurrentSong().get().equals(checkPlaylist.get(3)));
		this.testReproduction(lettore, 2000);

		lettore.stop();

	}

	private void testReproduction(final MusicPlayer lettore, final int pauseTime) {

		System.out.println("Start Reproduction!!!!");
		lettore.play();

		try {
			Thread.sleep(pauseTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Controllo che il tempo della traccia scorra
		assertTrue(lettore.getCurrentSongInfosManager().get().getElapsedTime() > 0);
		System.out.println("Pause!!!!");
		lettore.pause();

		// Attendo il tempo deciso dal parametro pauseTime millisecondi
		try {
			Thread.sleep(pauseTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Resume!!!!");
		lettore.play();

		// Attendo il tempo deciso dal parametro pauseTime secondi
		try {
			Thread.sleep(pauseTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Stop Reproduction!!!!");
		lettore.stop();

		// Attendo il tempo deciso dal parametro pauseTime secondi
		try {
			Thread.sleep(pauseTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Play another time!!!!");

		lettore.play();

		// Attendo il tempo deciso dal parametro pauseTime secondi
		try {
			Thread.sleep(pauseTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
