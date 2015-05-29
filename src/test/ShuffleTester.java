package test;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import view.interfaces.UpdatableObserver;
import model.PlayerState;
import model.playlistmanager.FeaturesHandled;
import controller.musicplayer.MusicPlayer;
import controller.musicplayer.MusicPlayerFactory;
import controller.musicplayer.PlaylistFeatureCommand;


/**
 * An automatic test for the shuffle mode
 * 
 * @author Matteo Gabellini
 *
 */

public class ShuffleTester {
	private final int WAIT_TIME = 5000;
	private static final String FIRST_SAMPLED_SONG = "file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/NACCARENA master (STEVE).wav";
	private static final String SECOND_SAMPLED_SONG = "file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/GHETTO STORY.wav";
	private static final String FIRST_MIDI_SONG = "file:/Users/matteogabellini/Documents/Materiale Università/2ANNO/Object Oriented Programming/Progetto/chango.mid";
	private static final String SECOND_MIDI_SONG = "file:/Users/matteogabellini/Documents/Materiale Università/2ANNO/Object Oriented Programming/Progetto/jashisth.mid";
	
	
	PlayerState statoRiproduttore = PlayerState.STOPPED;
	
	@org.junit.Test
	public void testShuffleMode() {
		UpdatableObserver component = new UpdatableObserver() {
			@Override
			public void updateStatus(PlayerState status) {
				statoRiproduttore = status;
			}
		};
		final MusicPlayer riproduttore = MusicPlayerFactory
				.createShuffableMusicPlayer();

		riproduttore.addUpdatableObservers(component);
		try {
			riproduttore
					.addSong(new URL(FIRST_SAMPLED_SONG));
			riproduttore
					.addSong(new URL(FIRST_MIDI_SONG));
			riproduttore
					.addSong(new URL(SECOND_SAMPLED_SONG));

			riproduttore
					.addSong(new URL(SECOND_MIDI_SONG));

		} catch (IllegalArgumentException e1) {
			fail(e1.getMessage());
		} catch (MalformedURLException e1) {
			fail(e1.getMessage());
		}

		if(riproduttore instanceof PlaylistFeatureCommand){
			((PlaylistFeatureCommand) riproduttore).setPlaylistFeature(FeaturesHandled.SHUFFLE, true);;
			assertEquals(this.statoRiproduttore, PlayerState.SHUFFLED);
		}
		riproduttore.play();
		
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		riproduttore.goToNextSong();
		
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Optional<URL> previousSong = riproduttore.getCurrentSong();
		riproduttore.goToNextSong();
		
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertNotEquals(previousSong.get(), riproduttore.getCurrentSong().get());
		riproduttore.goToPreviousSong();
		assertEquals(previousSong.get(), riproduttore.getCurrentSong().get());
		
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < 5; i++){
			riproduttore.goToNextSong();
			try {
				Thread.sleep(WAIT_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i < 7; i++){
			riproduttore.goToPreviousSong();
			try {
				Thread.sleep(WAIT_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
