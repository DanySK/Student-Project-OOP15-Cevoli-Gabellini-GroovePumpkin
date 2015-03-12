package View.buttons;

import controller.MusicPlayer;
import static Model.Utility.*;

public class LoopSpace extends PersonalJButton {

	private static final long serialVersionUID = -7087889831741979163L;
	private static final String NAME= "Loop";
	
	protected LoopSpace(final MusicPlayer controller, final boolean showTitle) {
		super(LOOP_OFF_IMG);
		
		if(showTitle){
			this.setTitle(NAME);
		}
		
		this.addActionListener(e->{
			if (controller.isLoopModeActive()) {
				this.setIcon(LOOP_ON_IMG);
				controller.setLoop(false);
			} else {
				this.setIcon(LOOP_OFF_IMG);
				controller.setLoop(true);
			}
		});
	}
}
