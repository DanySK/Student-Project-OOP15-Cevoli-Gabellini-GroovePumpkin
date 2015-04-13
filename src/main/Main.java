package main;

//import controller.GrooveBoxController;
import controller.MusicPlayer;
import controller.MusicPlayerImpl;
import View.SoundFrame;
import View.config.Configuration;

/**
 * From this site you can convert .mp3/.aac/.m4a into .wav/.midi
 * 
 * @link{http://media.io/it/
 * @link{http://audio.online-convert.com/
 * 
 * @author Alessandro
 *
 */
public class Main {
	private static final MusicPlayer MP = new MusicPlayerImpl();

	// private static final GrooveBoxController groove;

	public static void main(final String... args) {
		new Configuration();
		new SoundFrame(MP, null);
		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// } catch (final Exception e) {
		//
		// }
	}

}
