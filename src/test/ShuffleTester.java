package test;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import model.PlayerState;
import controller.Updatable;
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
	
	PlayerState statoRiproduttore = PlayerState.STOPPED;
	
	@org.junit.Test
	public void testShuffleMode() {
		Updatable component = new Updatable() {
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
					.addSong(new URL(
							"file:/Users/matteogabellini/Documents/Materiale Università/2ANNO/Object Oriented Programming/Progetto/chango.mid"));
			riproduttore
					.addSong(new URL(
							"file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/NACCARENA master (STEVE).wav"));
			riproduttore
					.addSong(new URL(
							"file:/Users/matteogabellini/Documents/Materiale Università/2ANNO/Object Oriented Programming/Progetto/chango.mid"));

			riproduttore
					.addSong(new URL(
							"file:/Users/matteogabellini/Documents/Materiale Università/2ANNO/Object Oriented Programming/Progetto/jashisth.mid"));

		} catch (IllegalArgumentException e1) {
			fail(e1.getMessage());
		} catch (MalformedURLException e1) {
			fail(e1.getMessage());
		}

		if(riproduttore instanceof PlaylistFeatureCommand){
			((PlaylistFeatureCommand) riproduttore).setShuffleMode(true);
			assertEquals(this.statoRiproduttore, PlayerState.SHUFFLED);
		}
		riproduttore.play();
		
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		riproduttore.goToNextSong();
		
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Optional<URL> currentSong = riproduttore.getCurrentSong();
		riproduttore.goToNextSong();
		
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotEquals(currentSong.get(), riproduttore.getCurrentSong().get());
		riproduttore.goToPreviousSong();
		assertEquals(currentSong.get(), riproduttore.getCurrentSong().get());
		
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < 5; i++){
			riproduttore.goToNextSong();
			try {
				Thread.sleep(WAIT_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i < 7; i++){
			riproduttore.goToPreviousSong();
			try {
				Thread.sleep(WAIT_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
