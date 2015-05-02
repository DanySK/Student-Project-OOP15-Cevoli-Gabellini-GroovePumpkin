package view.buttons;

import controller.LoopablePlayer;
//import controller.Player;
import static view.config.Utility.*;

/**
 * 
 * @author Alessandro
 *
 */
public class LoopButton extends PersonalJButton<LoopablePlayer> {

	private static final long serialVersionUID = -7087889831741979163L;
	private static final String TITLE= "Loop";
	
	protected LoopButton(final LoopablePlayer controller, final boolean showTitle) {
		super(LOOP_OFF_IMG);
		this.setController(controller);
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