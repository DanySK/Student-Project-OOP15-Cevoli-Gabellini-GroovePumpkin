package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;

import controller.musicplayer.LoopableMusicPlayer;
import controller.musicplayer.MusicPlayer;
import controller.musicplayer.MusicPlayerFactory;

/**
 * This is a test for the loop 
 * @author matteogabellini
 *
 */
public class LoopTester {
	private static final String SAMPLED_SONG = "file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/GHETTO STORY.wav";
	private static final String MIDI_SONG = "file:/Users/matteogabellini/Documents/Materiale Università/2ANNO/Object Oriented Programming/Progetto/chango.mid";
	
	@org.junit.Test
	public void testLoopMode() {
		final MusicPlayer riproduttore = MusicPlayerFactory
				.createLoopableMusicPlayer();
		try {
			riproduttore
					.addSong(new URL(SAMPLED_SONG));
			riproduttore
					.addSong(new URL(MIDI_SONG));
		} catch (IllegalArgumentException e1) {
			fail(e1.getMessage());
		} catch (MalformedURLException e1) {
			fail(e1.getMessage());
		}

		final LoopableMusicPlayer lmpTmp = (LoopableMusicPlayer) riproduttore;

		lmpTmp.setLoop(true);
		riproduttore.play();
		System.out.println("Durata Traccia: "
				+ riproduttore.getCurrentSongInfosManager().get().getDuration());
		final double durationOfSong = riproduttore.getCurrentSongInfosManager().get().getDuration();
		final int tPosition = ((int) (durationOfSong * 1000000)) - 7000000;
		riproduttore.getCurrentSongInfosManager().get().setPosition(tPosition);

		try {
			Thread.sleep(9000);
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
