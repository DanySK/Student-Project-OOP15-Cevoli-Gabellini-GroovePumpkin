package View.buttons;

import controller.MusicPlayer;
import static Model.Utility.*;

/**
 * This button manages the shuffling of the playlist
 * by the controller
 * 
 * @author Alessandro
 *
 */
public class ShuffleSpace extends PersonalJButton {

	private static final long serialVersionUID = 5626643991067522666L;
	private static final String TITLE_1= "Shuffle";
	private static final String TITLE_2= "Unshuffle";
	
	protected ShuffleSpace(final MusicPlayer controller, final boolean showTitle) {
		super(SHUFFLE_IMG);
		super.setController(controller);
		
		doShow(showTitle, TITLE_1);
		
		this.addActionListener(e->{
			if(controller.isShuffleModeActive()){
				controller.setShuffleMode(false);
				changeTitle(TITLE_1);
			} else{	
				controller.setShuffleMode(true);
				changeTitle(TITLE_2);
			}
			
		});
	}
}
