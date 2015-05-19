package main;

import view.config.Configuration;
import view.frames.SoundFrame;
import controller.groovebox.GrooveBoxController;
import controller.groovebox.GrooveBoxPlayer;
import controller.musicplayer.MusicPlayer;
import controller.musicplayer.MusicPlayerFactory;

/**
 * From this site you can convert .mp3/.aac/.m4a into .wav/.midi
 * 
 * @link{http://media.io/it/}
 * @link{http://audio.online-convert.com/} <- The best imho
 * 
 * @author Alessandro
 *
 */
public final class Main {
	private static final MusicPlayer MP = MusicPlayerFactory
			.createLoopableAndShuffableMP();
	private static final GrooveBoxPlayer GBC = GrooveBoxController
			.getInstance();

	@SuppressWarnings("unused")
	public static void main(final String... args) {
		final Configuration config= Configuration.getConfig();
		final SoundFrame jf= new SoundFrame(MP, GBC);
	}

}
