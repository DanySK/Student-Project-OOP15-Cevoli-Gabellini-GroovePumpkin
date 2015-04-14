package View.buttons;

import controller.MusicPlayer;
import static View.config.Utility.*;

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
				controller.setLoop(false);
				this.setIcon(LOOP_OFF_IMG);
			} else {
				controller.setLoop(true);
				this.setIcon(LOOP_ON_IMG);
			}
		});
	}
}
