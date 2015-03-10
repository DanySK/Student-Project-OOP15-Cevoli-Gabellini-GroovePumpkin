package View.buttons;

import Model.PlayerState;
import View.Updatable;

/**
 * 
 * @author Alessandro
 * @param <Recspace>
 *
 */
public class RecSpace extends PersonalJButton implements Updatable{

	private static final long serialVersionUID = -2653526539862883988L;
	public static final String REC = "Rec";
	public static final String STOP = "Stop";

	protected RecSpace(final Object rps, final boolean showTitle) {

		super(PersonalJButton.REC_IMG);
		
		if(showTitle){
			this.showTitle(REC);
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
			RecSpace.this.setIcon(PersonalJButton.STOP_IMG);
			
		} else {
			RecSpace.this.getTitledBorder().setTitle(REC);
			RecSpace.this.setIcon(PersonalJButton.REC_IMG);
		}
	}
}
