package View.buttons;

import controller.MusicPlayer;
import Model.PlayerState;
import View.Updatable;
import static Model.Utility.*;

public class StopSpace extends PersonalJButton implements Updatable{

	private static final long serialVersionUID = -5881639820393820954L;
	private static final String NAME="Stop";
	
	protected StopSpace(final MusicPlayer controller, final boolean showTitle) {
		
		super(STOP_IMG);
		super.setController(controller);

		if(showTitle){
			this.setTitle(NAME);
		}
		
		this.setEnabled(false);
		
		this.addActionListener(e->{
			try{
				controller.stop();
			} catch(Exception ex){
				//Open error pane
			}
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
