package View.buttons;

import Model.PlayerState;
import View.Updatable;
import static Model.Utility.*;

/**
 * 
 * @author Alessandro
 *
 */
public class RecSpace extends PersonalJButton implements Updatable{

	private static final long serialVersionUID = -2653526539862883988L;
	public static final String REC = "Rec";
	public static final String STOP = "Stop";

	protected RecSpace(final Object rps, final boolean showTitle) {

		super(REC_IMG);
		
		if(showTitle){
			this.setTitle(REC);
		}

		this.addActionListener(e -> {
			//Start Recording
		});
	}

	public boolean isRecording() {
		return super.getTitledBorder().getTitle().equals("Stop");
	}

	@Override
	public void updateStatus(final PlayerState status) {
		
		if (RecSpace.this.getTitledBorder().getTitle().equals(REC)) {
			RecSpace.this.getTitledBorder().setTitle(STOP);
			RecSpace.this.setIcon(STOP_IMG);
			
		} else {
			RecSpace.this.getTitledBorder().setTitle(REC);
			RecSpace.this.setIcon(REC_IMG);
		}
	}
}
