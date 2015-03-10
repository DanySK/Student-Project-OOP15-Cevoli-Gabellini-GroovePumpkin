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

	protected RecSpace(final PlaySpace rps, final boolean showTitle) {

		super(PersonalJButton.REC_IMG);
		
		if(showTitle){
			this.showTitle(REC);
		}

		this.addActionListener(e -> {

			if (RecSpace.this.getTitledBorder().getTitle().equals(REC)) {
				RecSpace.this.getTitledBorder().setTitle(STOP);
				RecSpace.this.setIcon(PersonalJButton.STOP_IMG);
				rps.setEnabled(true);
				// start recording the sound

			} else {
				RecSpace.this.getTitledBorder().setTitle(REC);
				RecSpace.this.setIcon(PersonalJButton.REC_IMG);
				rps.getTitledBorder().setTitle("Pause");
				rps.setIcon(PersonalJButton.PAUSE_IMG);
				rps.setEnabled(false);
				// stop recording

			}
		});
	}

	public boolean isRecording() {
		return super.getTitledBorder().getTitle().equals("Stop");
	}

	@Override
	public boolean updateStatus(final PlayerState status) {
		// TODO Auto-generated method stub
		return false;
	}
}
