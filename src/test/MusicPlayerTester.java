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
	public void testSampledMusicPlayer() {
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
		//Controllo che l'inserimento nella playList sia avvenuto
		assertTrue(checkList.equals(lettore.getPlayList()));
		//Controllo che la traccia corrente sia corretta
		assertEquals(lettore.getCurrentSong(), checkList.get(0));
		
		this.testReproduction(lettore,2000);
				
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
		assertEquals(lettore.getCurrentSong(), checkList.get(1));
		
		this.testReproduction(lettore, 2000);
		
		//Ora proviamo a caricare anche dei midi
		try {
			checkList.add(new URL("file:/Users/matteogabellini/Documents/Materiale Università/Object Oriented Programming/Progetto/chango.mid"));
			lettore.addSong(new URL("file:/Users/matteogabellini/Documents/Materiale Università/Object Oriented Programming/Progetto/chango.mid"));
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
		assertEquals(lettore.getCurrentSong(), checkList.get(2));		
		
		this.testReproduction(lettore,5000);
		
		try {
			checkList.add(new URL("file:/Users/matteogabellini/Documents/Materiale Università/Object Oriented Programming/Progetto/jashisth.mid"));
			lettore.addSong(new URL("file:/Users/matteogabellini/Documents/Materiale Università/Object Oriented Programming/Progetto/jashisth.mid"));
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
		assertEquals(lettore.getCurrentSong(), checkList.get(3));		
		
		this.testReproduction(lettore,5000);
		

		try {
			checkList.add(new URL("file:/Users/matteogabellini/Music/airo.wav"));
			lettore.addSong(new URL("file:/Users/matteogabellini/Music/airo.wav"));
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
		assertEquals(lettore.getCurrentSong(), checkList.get(4));
		
		this.testReproduction(lettore, 5000);			
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
	
	/*
	@org.junit.Test
	public void testExeption(){
		final MusicPlayer lettore = new MusicPlayerImpl();		
		final List<URL> checkList = new ArrayList<>();
		
	}*/
	
}
