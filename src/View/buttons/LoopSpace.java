package View.buttons;

import java.util.List;

public class LoopSpace extends PersonalJButton {

	private static final long serialVersionUID = -7087889831741979163L;
	private static final String NAME= "Loop";
	public static final String OFF= "Off";
	public static final String ON= "On";
	
	protected LoopSpace(final List<?> playlist, final boolean showTitle) {
		super(PersonalJButton.LOOP_OFF_IMG, OFF);
		
		if(showTitle){
			this.showTitle(NAME);
		}
		
		this.addActionListener(e->{
			if (LoopSpace.this.getID().equals(OFF)) {
				LoopSpace.this.setID(ON);
				LoopSpace.this.setIcon(PersonalJButton.LOOP_ON_IMG);
				// Loop the groove
			} else {
				LoopSpace.this.setID(OFF);
				LoopSpace.this.setIcon(PersonalJButton.LOOP_OFF_IMG);
				// UnLoop the groove
			}
		});
	}
}
