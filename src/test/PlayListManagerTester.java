package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import controller.musicplayer.MusicPlayer;
import controller.musicplayer.MusicPlayerFactory;

public class PlayListManagerTester {
	private static final String FIRST_SONG =  "file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/NACCARENA master (STEVE).wav";
	private static final String SECOND_SONG = "file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/GHETTO STORY.wav";
	
	@org.junit.Test
	public void testAddAndRemoveSong() {
		final MusicPlayer lettore = MusicPlayerFactory.createLoopableAndShuffableMP();
		final List<URL> checkList = new ArrayList<>();

		try {
			// Aggiungo una canzone alla checkList e alla playList del lettore
			checkList
					.add(new URL(FIRST_SONG));
			lettore.addSong(new URL(FIRST_SONG));
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

		// Test rimozione
		lettore.removeSong(0);
		checkList.remove(0);
		assertTrue(checkList.equals(lettore.getPlayList()));

		try {
			// Aggiungo una canzone alla checkList e alla playList del lettore
			checkList
					.add(new URL(FIRST_SONG));
			lettore.addSong(new URL(FIRST_SONG));
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			checkList
					.add(new URL(SECOND_SONG));
			lettore.addSong(new URL(SECOND_SONG));
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		}

		// Controllo che l'inserimento nella playList sia avvenuto
		assertTrue(checkList.equals(lettore.getPlayList()));
		lettore.goToNextSong();
		assertEquals(lettore.getCurrentSong().get(), checkList.get(1));

		lettore.goToPreviousSong();
		assertEquals(lettore.getCurrentSong().get(), checkList.get(0));

		// Test rimozione
		lettore.removeSong(1);
		checkList.remove(1);
		assertTrue(checkList.equals(lettore.getPlayList()));

		// Provo a mandare avanti il lettore oltre al limite
		lettore.goToNextSong();
		// Controllo che rimanga come current song la traccia precedente
		assertEquals(lettore.getCurrentSong().get(), checkList.get(0));
	}

	
}
