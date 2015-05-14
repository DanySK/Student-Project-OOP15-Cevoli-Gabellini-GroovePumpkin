package test;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import model.PlayerState;
import controller.Updatable;
import controller.musicplayer.LoopableMusicPlayer;
import controller.musicplayer.MusicPlayer;
import controller.musicplayer.MusicPlayerFactory;
import controller.musicplayer.Shuffable;

/**
 * This is an automatic test with JUnit for my classes
 * 
 * @author Matteo Gabellini
 *
 */
public class MusicPlayerTester {
	
	@org.junit.Test
	public void testSampledMusicPlayer() {
		final MusicPlayer lettoreSample = MusicPlayerFactory.createClassicMusicPlayer();
		try {
			lettoreSample
					.addSong(new URL(
							"file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/NACCARENA master (STEVE).wav"));
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		this.testReproduction(lettoreSample, 2000);

		try {
			lettoreSample
					.addSong(new URL(
							"file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/GHETTO STORY.wav"));
		} catch (IllegalArgumentException e1) {
			fail(e1.getMessage());
		} catch (MalformedURLException e1) {
			fail(e1.getMessage());
		}

		lettoreSample.goToNextSong();

		this.testReproduction(lettoreSample, 2000);

		lettoreSample.stop();

	}

	@org.junit.Test
	public void testMidiMusicPlayer() {
		final MusicPlayer lettoreMidi =  MusicPlayerFactory.createClassicMusicPlayer();
		try {
			lettoreMidi
					.addSong(new URL(
							"file:/Users/matteogabellini/Documents/Materiale Università/2ANNO/Object Oriented Programming/Progetto/chango.mid"));
		} catch (IllegalArgumentException e1) {
			fail(e1.getMessage());
		} catch (MalformedURLException e1) {
			fail(e1.getMessage());
		}

		this.testReproduction(lettoreMidi, 5000);

		try {
			lettoreMidi
					.addSong(new URL(
							"file:/Users/matteogabellini/Documents/Materiale Università/2ANNO/Object Oriented Programming/Progetto/jashisth.mid"));
		} catch (IllegalArgumentException e1) {
			fail(e1.getMessage());
		} catch (MalformedURLException e1) {
			fail(e1.getMessage());
		}

		lettoreMidi.goToNextSong();
		this.testReproduction(lettoreMidi, 5000);

		lettoreMidi.stop();
	}
	
	@SuppressWarnings("unused")

	private void testReproduction(MusicPlayer lettore, int pauseTime) {
		double elapsedTime;

		System.out.println("Start Reproduction!!!!");
		lettore.play();

		try {
			Thread.sleep(pauseTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Controllo che il tempo della traccia scorra
		assertTrue(lettore.getElapsedTime() > 0);
		System.out.println("Pause!!!!");
		lettore.pause();
		elapsedTime = lettore.getElapsedTime();

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
