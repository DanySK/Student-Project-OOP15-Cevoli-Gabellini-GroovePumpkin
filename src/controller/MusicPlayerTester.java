package controller;

import java.net.MalformedURLException;
import java.net.URL;

public class MusicPlayerTester {
	public static void test(MusicPlayer lettore) {
		try {
			lettore.addSong(new URL("file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/NACCARENA master (STEVE).wav"));
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Traccia Corrente" + lettore.getCurrentSong());
		
  		lettore.play();
		
  		
		for (int i = 0; i < 10; i++) {
			System.out.println("Tempo trascorso " + lettore.getElapsedTime());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		lettore.pause();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
		lettore.play();
		
		
		try {
			lettore.addSong(new URL("file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/GHETTO STORY.wav"));
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		lettore.goToNextSong();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lettore.play();
	}
}
