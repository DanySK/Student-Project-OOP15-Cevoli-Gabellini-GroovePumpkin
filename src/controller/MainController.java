package controller;

import test.MusicPlayerTester;

/**
 * This is the classes that rappresent the main controller and the start point of the program
 * @author Matteo Gabellini
 *
 */
public class MainController {
	private static final MusicPlayer lettore = MusicPlayerImpl.getInstance();
	private static final GrooveBoxPlayer grooveBox = GrooveBoxController.getInstance();
	
	public static void main(String... args){
		/*
		SoundFrame sf = new SoundFrame(lettore, );
		lettore.addViewListener();
		*/
	}
}
