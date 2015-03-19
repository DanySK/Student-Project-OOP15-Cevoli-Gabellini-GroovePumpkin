package controller;

import test.MusicPlayerTester;

/**
 * This is the classes that rappresent the main controller and the start point of the program
 * @author Matteo Gabellini
 *
 */
public class MainController {
	private static final MusicPlayerImpl lettore = new MusicPlayerImpl();
	//private static final GrooveBoxIml
	
	public static void main(String... args){
		/*
		SoundFrame sf = new SoundFrame(lettore, );
		lettore.addViewListener();
		*/
		
		MusicPlayerTester.test(lettore);
	}
}
