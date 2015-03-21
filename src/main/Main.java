package main;

//import controller.GrooveBoxController;
import controller.MusicPlayer;
import controller.MusicPlayerImpl;
import View.SoundFrame;
/**
 * From this site you can convert .mp3/.aac/.m4a into .wav/.midi
 * 
 * link{http://www.ofoct.com/category/audio-converter}
 * 
 * 
 * @author Alessandro
 *
 */
public class Main {
	private static final MusicPlayer MP = new MusicPlayerImpl();
	//private static final GrooveBoxController groove;
	
	public static void main(final String... args) {
			new SoundFrame(MP, null);
	}

}
