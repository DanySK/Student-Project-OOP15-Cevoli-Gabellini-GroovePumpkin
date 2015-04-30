package view.buttons;

import model.PlayerState;
import static model.PlayerState.*;
import controller.Player;
import static view.config.Utility.*;

/**
 * 
 * @author Alessandro
 *
 */
public class StopButton extends TJB{

	private static final long serialVersionUID = -5881639820393820954L;
	private static final String TITLE="Stop";
	
	protected StopButton(final Player controller, final boolean showTitle) {
		super(STOP_IMG);
		super.setController(controller);
		doShow(showTitle, TITLE);
		this.setEnabled(false);
		
		this.addActionListener(e->{
			//System.out.println("Metto in Pausa\n");
			controller.stop();
		});
	}

	@Override
	public void updateStatus(final PlayerState status) {
		
		if(status.equals(STOPPED) || status.equals(REMOVED)) {
			this.setEnabled(false);
		} else if(status.equals(RUNNING) && !this.isEnabled()) {
			this.setEnabled(true);
		}
	}
}
