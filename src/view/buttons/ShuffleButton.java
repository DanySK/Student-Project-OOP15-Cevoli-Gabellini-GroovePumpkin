package view.buttons;

import controller.MusicPlayer;
import static view.config.Utility.*;

/**
 * This button manages the shuffling of the playlist
 * by the controller
 * 
 * @author Alessandro
 *
 */
public class ShuffleButton extends PersonalJButton {

	private static final long serialVersionUID = 5626643991067522666L;
	private static final String TITLE_1= "ON";
	private static final String TITLE_2= "OFF";
	
	protected ShuffleButton(final MusicPlayer controller, final boolean showTitle) {
		super(UNSHUFFLED_IMG);
		super.setController(controller);
		
		doShow(showTitle, TITLE_1);
		
		this.addActionListener(e->{
			if(controller.isShuffleModeActive()){
				controller.setShuffleMode(false);
				this.changeTitle(TITLE_1);
				this.setIcon(UNSHUFFLED_IMG);
			} else{	
				controller.setShuffleMode(true);
				this.changeTitle(TITLE_2);
				this.setIcon(SHUFFLED_IMG);
			}
			
		});
	}
}
