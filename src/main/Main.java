package main;

import view.SoundFrame;
import view.config.Configuration;
import controller.GrooveBoxController;
import controller.GrooveBoxPlayer;
import controller.MusicPlayer;
import controller.MusicPlayerImpl;

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
	private static final MusicPlayer MP = new MusicPlayerImpl();
	private static final GrooveBoxPlayer GBC= new GrooveBoxController();

	private Main() {
		
	}
	
	
	public static void main(final String... args) {
		new Configuration();
		new SoundFrame(MP, GBC);
		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// } catch (final Exception e) {
		//
		// }
	}

}
