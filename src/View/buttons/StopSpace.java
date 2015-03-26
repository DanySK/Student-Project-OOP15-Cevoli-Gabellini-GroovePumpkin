package View.buttons;

import controller.Player;
import Model.PlayerState;
import View.Updatable;
import static Model.Utility.*;

/**
 * 
 * @author Alessandro
 *
 */
public class StopSpace extends PersonalJButton implements Updatable{

	private static final long serialVersionUID = -5881639820393820954L;
	private static final String NAME="Stop";
	
	protected StopSpace(final Player controller, final boolean showTitle) {
		
		super(STOP_IMG);
		super.setController(controller);

		if(showTitle){
			this.setTitle(NAME);
		}
		
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
