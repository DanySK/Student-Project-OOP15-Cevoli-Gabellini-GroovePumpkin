package controller;

import controller.musicplayer.MusicPlayer;
import controller.musicplayer.MusicPlayerFactory;

/**
 * This is the classes that rappresent the main controller and the start point of the program
 * @author Matteo Gabellini
 *
 */
public class MainController {
	private static final MusicPlayer lettore = MusicPlayerFactory.createLoopableAndShuffableMP();
	private static final GrooveBoxPlayer grooveBox = GrooveBoxController.getInstance();
	
	public static void main(String... args){
		/*
		SoundFrame sf = new SoundFrame(lettore, );
		lettore.addViewListener();
		*/
	}
}
