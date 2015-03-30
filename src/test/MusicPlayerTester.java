package test;

import static org.junit.Assert.*;

import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

import controller.MusicPlayer;
import controller.MusicPlayerImpl;

/**
 * This is an automatic test with JUnit for my classes
 * @author Matteo Gabellini
 *
 */
public class MusicPlayerTester {
	
	@org.junit.Test
	public void testAddAndRemoveSong(){
		final MusicPlayer lettore = new MusicPlayerImpl();		
		final List<URL> checkList = new ArrayList<>();
		
		//Prima parte testa la lettura delle canzoni sampled
		try {
			//Aggiungo una canzone alla checkList e alla playList del lettore
			checkList.add(new URL("file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/NACCARENA master (STEVE).wav"));
			lettore.addSong(new URL("file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/NACCARENA master (STEVE).wav"));
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		

		// Controllo che l'inserimento nella playList sia avvenuto
		assertTrue(checkList.equals(lettore.getPlayList()));
		// Controllo che la traccia corrente sia corretta
		assertEquals(lettore.getCurrentSong().get(), checkList.get(0));
		
		try {
			checkList.add(new URL("file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/GHETTO STORY.wav"));
			lettore.addSong(new URL("file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/GHETTO STORY.wav"));
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		}
		//Controllo che l'inserimento nella playList sia avvenuto
	    assertTrue(checkList.equals(lettore.getPlayList()));
		lettore.goToNextSong();
		assertEquals(lettore.getCurrentSong().get(), checkList.get(1));
		
		lettore.goToPreviousSong();
		assertEquals(lettore.getCurrentSong().get(), checkList.get(0));
		
		// Test rimozione
		lettore.removeSong(1);
		checkList.remove(1);
		assertTrue(checkList.equals(lettore.getPlayList()));

		//Provo a mandare avanti il lettore oltre al limite
		lettore.goToNextSong();
		//Controllo che rimanga come current song la traccia precedente
		assertEquals(lettore.getCurrentSong().get(), checkList.get(0));
	}
	
	
	@org.junit.Test
	public void testSampledMusicPlayer() {
		final MusicPlayer lettorSample = new MusicPlayerImpl();	
		
		//Prima parte testa la lettura delle canzoni sampled
		try {
			//Aggiungo una canzone alla checkList e alla playList del lettore
			lettorSample.addSong(new URL("file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/NACCARENA master (STEVE).wav"));
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		
		this.testReproduction(lettorSample,2000);
				
		try {
			lettorSample.addSong(new URL("file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/GHETTO STORY.wav"));
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		}
		
		lettorSample.goToNextSong();
		
		this.testReproduction(lettorSample, 2000);
		
		lettorSample.stop();
		
	}
	
	
	@org.junit.Test
	public void testMidiMusicPlayer(){
		final MusicPlayer lettoreMidi = new MusicPlayerImpl();		
		
		//Ora proviamo a caricare anche dei midi
		try {
			lettoreMidi.addSong(new URL(
					"file:/Users/matteogabellini/Documents/Materiale Università/Object Oriented Programming/Progetto/chango.mid"));
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		}
		
		this.testReproduction(lettoreMidi, 5000);

		try {
			lettoreMidi.addSong(new URL(
					"file:/Users/matteogabellini/Documents/Materiale Università/Object Oriented Programming/Progetto/jashisth.mid"));
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		}
		
		lettoreMidi.goToNextSong();
		this.testReproduction(lettoreMidi, 5000);
		
		lettoreMidi.stop();
	}
	
	
	private void testReproduction(MusicPlayer lettore, int pauseTime){
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
