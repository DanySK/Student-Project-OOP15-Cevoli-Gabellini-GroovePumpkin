package View.buttons;

import controller.MusicPlayer;
import static Model.Utility.*;

/**
 * 
 * @author Alessandro
 *
 */
public class LoopSpace extends PersonalJButton {

	private static final long serialVersionUID = -7087889831741979163L;
	private static final String TITLE= "Loop";
	
	protected LoopSpace(final MusicPlayer controller, final boolean showTitle) {
		super(LOOP_OFF_IMG);
		
		doShow(showTitle, TITLE);
		
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
