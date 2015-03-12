package View.buttons;

import controller.MusicPlayer;
import static Model.Utility.*;

public class ShuffleSpace extends PersonalJButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5626643991067522666L;
	private static final String TITLE= "Shuffle";
	
	protected ShuffleSpace(final MusicPlayer controller, final boolean showTitle) {
		super(SHUFFLE_IMG);
		super.setController(controller);
		
		if(showTitle){
			this.setTitle(TITLE);
		}
		
		this.addActionListener(e->{
			if(controller.isShuffleModeActive()){
				controller.setShuffleMode(false);
			} else{	
				controller.setShuffleMode(true);
			}
			
		});
	}
}
