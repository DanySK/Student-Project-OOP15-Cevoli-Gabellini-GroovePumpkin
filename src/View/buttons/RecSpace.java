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
		
		doShow(showTitle, REC);

		this.addActionListener(e -> {
			//Start Recording
		});
	}

	@Override
	public void updateStatus(final PlayerState status) {
		
	}
}
