package main;

import view.config.Configuration;
import view.frames.SoundFrame;
import controller.GrooveBoxController;
import controller.GrooveBoxPlayer;
import controller.musicplayer.LoopableMusicPlayer;
import controller.musicplayer.MusicPlayer;

/**
 * From this site you can convert .mp3/.aac/.m4a into .wav/.midi
 * 
 * @link{http://media.io/it/
 * @link{http://audio.online-convert.com/
 * 
 * @author Alessandro
 *
 */
public final class Main {
	private static final MusicPlayer MP = LoopableMusicPlayer.getInstance();
	private static final GrooveBoxPlayer GBC= GrooveBoxController.getInstance();

	private Main() {
		
	}
	
	
	public static void main(final String... args) {
		new Configuration();
		new SoundFrame(MP, GBC);
	}

}
