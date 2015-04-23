package view.buttons;

import view.interfaces.Updatable;
import model.PlayerState;
import controller.Player;
import static view.config.Utility.*;

/**
 * 
 * @author Alessandro
 *
 */
public class StopSpace extends PersonalJButton implements Updatable{

	private static final long serialVersionUID = -5881639820393820954L;
	private static final String TITLE="Stop";
	
	protected StopSpace(final Player controller, final boolean showTitle) {
		super(STOP_IMG);
		super.setController(controller);
		doShow(showTitle, TITLE);
		this.setEnabled(false);
		
		this.addActionListener(e->{
			controller.stop();
		});
	}

	@Override
	public void updateStatus(final PlayerState status) {
		
		if(status.equals(PlayerState.STOPPED)) {
			this.setEnabled(false);
		} else {
			this.setEnabled(true);
		}
	}
}