package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;

import controller.musicplayer.LoopableMusicPlayer;
import controller.musicplayer.MusicPlayer;
import controller.musicplayer.MusicPlayerFactory;

public class LoopTester {
	@org.junit.Test
	public void testLoopMode() {
		final MusicPlayer riproduttore = MusicPlayerFactory
				.createLoopableMusicPlayer();
		try {
			riproduttore
					.addSong(new URL(
							"file:/Users/matteogabellini/Documents/Materiale Università/2ANNO/Object Oriented Programming/Progetto/chango.mid"));
			riproduttore
					.addSong(new URL(
							"file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/NACCARENA master (STEVE).wav"));

		} catch (IllegalArgumentException e1) {
			fail(e1.getMessage());
		} catch (MalformedURLException e1) {
			fail(e1.getMessage());
		}

		LoopableMusicPlayer lmpTmp = (LoopableMusicPlayer) riproduttore;

		lmpTmp.setLoop(true);
		riproduttore.play();
		System.out.println("Durata Traccia: "
				+ riproduttore.getCurrentSongInfosManager().get().getDuration());
		double durationOfSong = riproduttore.getCurrentSongInfosManager().get().getDuration();
		int tPosition = ((int) (durationOfSong * 1000)) - 1000;
		riproduttore.getCurrentSongInfosManager().get().setPosition(tPosition);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Tempo trascorso: " + riproduttore.getCurrentSongInfosManager().get().getElapsedTime());
		assertTrue(riproduttore.getCurrentSongInfosManager().get().getElapsedTime() < tPosition);
		assertTrue(riproduttore.getPlayList().get(0)
				.equals(riproduttore.getCurrentSong().get()));

		riproduttore.stop();
	}

}
