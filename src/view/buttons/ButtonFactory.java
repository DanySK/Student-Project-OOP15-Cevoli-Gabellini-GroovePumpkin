package view.buttons;

import javax.swing.JButton;

import controller.GrooveBoxPlayer;
import controller.LoopablePlayer;
import controller.MusicPlayer;
import controller.Player;
import static view.config.Utility.*;

/**
 * 
 * @author Alessandro
 *
 */
public final class ButtonFactory {

	public static final int PLAY_BUTTON = 0;
	public static final int STOP_BUTTON = 1;
	public static final int LOOP_BUTTON = 2;
	public static final int REC_BUTTON = 3;
	public static final int SAVE_BUTTON = 4;
	public static final int LOAD_BUTTON = 5;
	public static final int SHUFFLE_BUTTON = 6;
	public static final int PAUSE_BUTTON = 7;
	public static final int ADD_BUTTON = 8;
	public static final int REMOVE_BUTTON = 9;

	private ButtonFactory() {
	}

	/**
	 * A simple factory class to simply creates functional Button
	 * 
	 * NOTE:
	 * 
	 * StopSpace, AddSpace, RemoveSpace and ShuffleSpace require a controller
	 * that implements the MusicPlayer Interface!
	 * 
	 * LoopSpace and PlaySpace/PauseSpace wants a generic PlayController
	 * interface implementation
	 * 
	 * LoadSpace want a GrooveBoxController interface implementation
	 * 
	 * 
	 * @param type
	 *            the type of button to create -> choose between the given
	 *            costants of this class
	 * @param showTitle
	 *            if the button have to show a TitledBorder or the built-in
	 *            border
	 * @param controller
	 *            to communicate with
	 * @return the chosen type of button or null if a wrong value is chosen
	 */

	public static JButton createButton(final int type, final boolean showTitle,
			final Object controller) {

		switch (type) {
		case 0:
			return new PlayPauseSpace((Player) controller, PLAY_IMG, showTitle);
		case 1:
			return new StopSpace((Player) controller, showTitle);
		case 2:
			return new LoopSpace((LoopablePlayer) controller, showTitle);
		case 3:
			return new RecSpace(controller, showTitle);
		case 4:
			return new SaveSpace((GrooveBoxPlayer) controller, showTitle);
		case 5:
			return new LoadSpace((GrooveBoxPlayer) controller, showTitle);
		case 6:
			return new ShuffleSpace((MusicPlayer) controller, showTitle);
		case 7:
			return new PlayPauseSpace((Player) controller, PAUSE_IMG, showTitle);
		case 8:
			return new AddSpace((MusicPlayer) controller, showTitle);
		case 9:
			return new RemoveSpace((MusicPlayer) controller, showTitle);
		default:
			return new JButton("Hai sbagliato type, pirla.");

		}
	}
}
