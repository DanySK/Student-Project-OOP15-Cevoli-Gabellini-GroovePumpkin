package View.buttons;

import controller.MusicPlayer;
import static Model.Utility.*;

public class LoopSpace extends PersonalJButton {

	private static final long serialVersionUID = -7087889831741979163L;
	private static final String NAME= "Loop";
	public static final String OFF= "Off";
	public static final String ON= "On";
	
	protected LoopSpace(final MusicPlayer controller, final boolean showTitle) {
		super(LOOP_OFF_IMG);
		super.setID(OFF);
		
		if(showTitle){
			this.setTitle(NAME);
		}
		
		this.addActionListener(e->{
			if (this.getID().equals(OFF)) {
				this.setID(ON);
				this.setIcon(LOOP_ON_IMG);
				// Loop the groove
			} else {
				this.setID(OFF);
				this.setIcon(LOOP_OFF_IMG);
				// UnLoop the groove
			}
		});
	}
}
