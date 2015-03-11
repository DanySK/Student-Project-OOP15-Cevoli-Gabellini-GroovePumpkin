package View.buttons;

import javax.swing.JButton;

import controller.GrooveBoxController;
import controller.MusicPlayer;
import static Model.Utility.*;

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
		if (type == PLAY_BUTTON) {
			return new PlaySpace((MusicPlayer) controller, PLAY_IMG, showTitle);
		} else if (type == STOP_BUTTON) {
			return new StopSpace((MusicPlayer) controller, showTitle);
		} else if (type == LOOP_BUTTON) {
			return new LoopSpace((MusicPlayer) controller, showTitle);
		} else if (type == REC_BUTTON) {
			return new RecSpace(controller, showTitle);
		} else if (type == SAVE_BUTTON) {
			return new SaveSpace((GrooveBoxController) controller, showTitle);
		} else if (type == LOAD_BUTTON) {
			return new LoadSpace((GrooveBoxController) controller, showTitle);
		} else if (type == SHUFFLE_BUTTON) {
			return new ShuffleSpace((MusicPlayer) controller, showTitle);
		} else if (type == PAUSE_BUTTON) {
			return new PlaySpace((MusicPlayer) controller, PAUSE_IMG, showTitle);
		} else if (type == ADD_BUTTON) {
			return new AddSpace((MusicPlayer) controller, showTitle);
		} else {
			return null;
		}
	}
}
