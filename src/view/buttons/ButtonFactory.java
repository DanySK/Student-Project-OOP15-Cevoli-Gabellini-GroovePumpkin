package view.buttons;

import static view.buttons.strategies.PlayerStrategy.*;
import static view.buttons.strategies.PlaylistStrategy.*;
import static view.buttons.strategies.MusicPlayerStrategy.*;
import static view.buttons.strategies.LoopStrategy.*;
import static view.buttons.strategies.GroovePlayerStrategy.*;
import controller.*;

/**
 * A simple factory class to simply creates functional Buttons
 * 
 * NOTE:
 * 
 * StopSpace, AddSpace, RemoveSpace and ShuffleSpace require a controller that
 * implements the MusicPlayer Interface!
 * 
 * LoopSpace and PlaySpace/PauseSpace wants a generic PlayController interface
 * implementation
 * 
 * LoadSpace want a GrooveBoxController interface implementation
 * 
 * @author Alessandro
 *
 */
public final class ButtonFactory {

	public static final int PLAY_BUTTON = 0;
	public static final int PAUSE_BUTTON = 1;
	public static final int STOP_BUTTON = 2;
	public static final int LOOP_BUTTON = 3;
	public static final int SHUFFLE_BUTTON = 4;
	public static final int SAVE_BUTTON = 5;
	public static final int LOAD_BUTTON = 6;
	public static final int FW_BUTTON = 7;
	public static final int RW_BUTTON = 8;
	public static final int ADD_BUTTON = 9;
	public static final int REMOVE_BUTTON = 10;

	private ButtonFactory() {
	}

	/**
	 * This method creates the chosen type of button.
	 * 
	 * NOTE: To make this fully generics I should make generics the various
	 * strategies too. That we'll be done if there'll be time left
	 * 
	 * @param type
	 *            the type of button to create -> choose between the given
	 *            costants of this class
	 * @param showTitle
	 *            if the button have to show a TitledBorder or the built-in
	 *            border
	 * @param controller
	 *            to communicate with
	 * @return the chosen type of button or a normal button if a wrong value is
	 *         chosen
	 */
	public static <C extends Player> FunctionalButton<? extends Player> createButton(
			final int type, final boolean showTitle, final C controller) {

		switch (type) {
		case 0:
			return new FunctionalButton<Player>(controller, showTitle, PLAY);
		case 1:
			return new FunctionalButton<Player>(controller, showTitle, PAUSE);
		case 2:
			return new FunctionalButton<Player>(controller, showTitle, STOP);
		case 3:
			return new FunctionalButton<LoopablePlayer>((LoopablePlayer) controller, showTitle, LOOP);
		case 4:
			return new FunctionalButton<MusicPlayer>((MusicPlayer) controller, showTitle, SHUFFLE);
		case 5:
			return new FunctionalButton<GrooveBoxPlayer>((GrooveBoxPlayer) controller, showTitle, SAVE);
		case 6:
			return new FunctionalButton<GrooveBoxPlayer>((GrooveBoxPlayer) controller, showTitle, LOAD);
		case 7:
			return new FunctionalButton<MusicPlayer>((MusicPlayer) controller, showTitle, FORWARD);
		case 8:
			return new FunctionalButton<MusicPlayer>((MusicPlayer) controller, showTitle, REWIND);
		case 9:
			return new FunctionalButton<MusicPlayer>((MusicPlayer) controller, showTitle, ADD);
		case 10:
			return new FunctionalButton<MusicPlayer>((MusicPlayer) controller, showTitle, REMOVE);
		default:
			return null;
		}
	}
}
