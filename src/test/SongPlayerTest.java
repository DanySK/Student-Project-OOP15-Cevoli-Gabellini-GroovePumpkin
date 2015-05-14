package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import controller.musicplayer.MusicPlayer;
import controller.songplayer.SampledSongPlayer;
import controller.songplayer.SongPlayer;

/**
 * This is an automatic test with JUnit for classes that manage the reproduction
 * of a single song
 * 
 * @author Matteo Gabellini
 *
 */
public class SongPlayerTest {

	@org.junit.Test
	public void testSampledSongPlayer() {
		URL songPath = null;
		try {
			songPath = new URL(
					"file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/NACCARENA master (STEVE).wav");
		} catch (MalformedURLException e1) {
			fail();
		}

		AudioInputStream audioStream;
		Optional<SongPlayer> sSPlayer = Optional.empty();
		
		try {
			audioStream = AudioSystem.getAudioInputStream(songPath);
			sSPlayer = Optional.of(new SampledSongPlayer(audioStream));
		} catch (UnsupportedAudioFileException e1) {
			fail();
		} catch (LineUnavailableException e) {
			fail();
		} catch (IOException e1) {
			fail();
		}
		
		
		if(sSPlayer.isPresent()){
			this.testReproduction(sSPlayer.get(), 3000);
		} else {
			 fail();
		}
		

	}
	
	
	private void testReproduction(SongPlayer lettore, int pauseTime){
		double elapsedTime;
		
		System.out.println("Start Reproduction!!!!");
		lettore.play();
		
		try {
			Thread.sleep(pauseTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Controllo che il tempo della traccia scorra
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
