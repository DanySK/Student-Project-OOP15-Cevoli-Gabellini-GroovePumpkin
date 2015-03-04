package View.buttons;

public class StopSpace extends PersonalJButton {

	private static final long serialVersionUID = -5881639820393820954L;
	private static final String NAME="Stop";
	
	public StopSpace(final PlaySpace play, final boolean showTitle) {
		
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
}
