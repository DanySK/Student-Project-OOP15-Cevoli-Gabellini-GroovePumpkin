package View.buttons;

import Model.PlayerState;
import View.Updatable;

public class StopSpace extends PersonalJButton implements Updatable{

	private static final long serialVersionUID = -5881639820393820954L;
	private static final String NAME="Stop";
	
	protected StopSpace(final PlaySpace play, final boolean showTitle) {
		
		super(PersonalJButton.STOP_IMG);
		
		if(showTitle){
			this.showTitle(NAME);
		}
		this.addActionListener(e->{
			if(!play.isPaused()){
				//Stop the player
			}
		});
	}

	@Override
	public boolean updateStatus(final PlayerState status) {
		// TODO Auto-generated method stub
		return false;
	}
}
