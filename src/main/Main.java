package main;

//import controller.GrooveBoxController;
import controller.MusicPlayer;
import controller.MusicPlayerImpl;
import View.SoundFrame;

public class Main {
	private static final MusicPlayer mp = new MusicPlayerImpl();
	//private static final GrooveBoxController groove;
	public static void main(String[] args) {
			new SoundFrame(mp, null);
	}

}
