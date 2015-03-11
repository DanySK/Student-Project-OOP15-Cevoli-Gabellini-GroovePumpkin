package View.buttons;

import controller.MusicPlayer;
import static Model.Utility.*;

public class ShuffleSpace extends PersonalJButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5626643991067522666L;
	private static final String TITLE= "Shuffle";
	private static final String ON= "On";
	private static final String OFF="Off";
	
	protected ShuffleSpace(final MusicPlayer controller, final boolean showTitle) {
		super(SHUFFLE_IMG);
		super.setController(controller);
		super.setID(OFF);
		if(showTitle){
			this.setTitle(TITLE);
		}
		
		this.addActionListener(e->{
			if(this.getID().equals(OFF)){
				this.setID(ON);
				//controller.shuffle(true);
			} else{
				this.setID(OFF);
				//controller.shuffle(false);
			}
			
		});
	}
}
