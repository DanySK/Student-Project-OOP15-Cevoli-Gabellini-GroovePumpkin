package controller;

import java.net.MalformedURLException;
import java.net.URL;

public class MusicTester {
	public static void test(MusicPlayer lettore) {
		try {
			lettore.addSong(new URL("file://localhost/Users/matteogabellini/Music/asia.wav"));
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lettore.play();
		try {
			lettore.addSong(new URL("file://localhost/Users/matteogabellini/Music/l'abeille.wav"));
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lettore.goToNextSong();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
