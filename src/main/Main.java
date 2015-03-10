package main;

import controller.MusicPlayer;
import controller.MusicPlayerImpl;
import View.SoundFrame;

public class Main {
	private static final MusicPlayer mp = new MusicPlayerImpl();
	//private static final GrooveBoxController
	public static void main(String[] args) {
			new SoundFrame(mp, null);
	}

}
