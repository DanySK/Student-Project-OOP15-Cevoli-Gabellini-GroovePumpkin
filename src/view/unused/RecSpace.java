package view.unused;

import view.config.Configuration;
import model.PlayerState;
import view.buttons.PersonalJButton;
import controller.UpdatableObserver;

/**
 * 
 * @author Alessandro
 *
 */
public class RecSpace<C> extends PersonalJButton<C> implements UpdatableObserver{

	private static final long serialVersionUID = -2653526539862883988L;
	public static final String REC = "Rec";
	public static final String STOP = "Stop";

	protected RecSpace(final Object rps, final boolean showTitle) {

		super(Configuration.getConfig().getRecImage());
		
		doShow(showTitle, REC);

		this.addActionListener(e -> {
			//Start Recording
		});
	}

	@Override
	public void updateStatus(final PlayerState status) {
		
	}
}
