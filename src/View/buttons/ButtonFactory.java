package View.buttons;

import javax.swing.JButton;
import controller.MusicPlayer;

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

	private ButtonFactory() {

	}
	
	/**
	 * A simple factory class to simply creates functional Button
	 * 
	 * NOTE: Not all the buttons need all the parameters! <For now>
	 * 
	 * 
	 * @param type the type of button to create -> choose between the given costants of this class
	 * @param showTitle if the button have to show a TitledBorder or the built-in border
	 * @param controller to communicate with
	 * @return the chosen type of button or null if a wrong value is chosen
	 */

	public static JButton createButton(final int type, final boolean showTitle,
			final Object controller) {
		if (type == PLAY_BUTTON) {
			return new PlaySpace((MusicPlayer) controller, PersonalJButton.PLAY_IMG, showTitle);
		} else if (type == STOP_BUTTON) {
			return new StopSpace((MusicPlayer) controller, showTitle);
		} else if (type == LOOP_BUTTON) {
			return new LoopSpace((MusicPlayer) controller, showTitle);
		} else if (type == REC_BUTTON) {
			return new RecSpace(controller, showTitle);
		} else if (type == SAVE_BUTTON) {
			return new SaveSpace(showTitle);
		} else if (type == LOAD_BUTTON) {
			return null;
		} else if (type == SHUFFLE_BUTTON) {
			return null;
		} else if (type == PAUSE_BUTTON) {
			return new PlaySpace((MusicPlayer) controller, PersonalJButton.PAUSE_IMG, showTitle);
		} else {
			return null;
		}
	}
}
