package view.buttons;

import static view.buttons.strategies.PlayerStrategy.*;
import static view.buttons.strategies.PlaylistStrategy.*;
import static view.buttons.strategies.MusicPlayerStrategy.*;
import static view.buttons.strategies.LoopStrategy.*;
import static view.buttons.strategies.GroovePlayerStrategy.*;
import view.buttons.strategies.ButtonStrategy;
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

	/**
	 * 
	 */
	public static final ButtonStrategy<Player, AbstractStrategicalButton<Player>> PLAY_BUTTON = PLAY;
	public static final ButtonStrategy<Player, AbstractStrategicalButton<Player>> PAUSE_BUTTON = PAUSE;
	public static final ButtonStrategy<Player, AbstractStrategicalButton<Player>> STOP_BUTTON = STOP;
	public static final ButtonStrategy<LoopablePlayer, AbstractStrategicalButton<LoopablePlayer>> LOOP_BUTTON = LOOP;
	public static final ButtonStrategy<MusicPlayer, AbstractStrategicalButton<MusicPlayer>> SHUFFLE_BUTTON = SHUFFLE;
	public static final ButtonStrategy<GrooveBoxPlayer, AbstractStrategicalButton<GrooveBoxPlayer>> SAVE_BUTTON = SAVE;
	public static final ButtonStrategy<GrooveBoxPlayer, AbstractStrategicalButton<GrooveBoxPlayer>> LOAD_BUTTON = LOAD;
	public static final ButtonStrategy<MusicPlayer, AbstractStrategicalButton<MusicPlayer>> FW_BUTTON = FORWARD;
	public static final ButtonStrategy<MusicPlayer, AbstractStrategicalButton<MusicPlayer>> RW_BUTTON = BACKWARD;
	public static final ButtonStrategy<MusicPlayer, AbstractStrategicalButton<MusicPlayer>> ADD_BUTTON = ADD;
	public static final ButtonStrategy<MusicPlayer, AbstractStrategicalButton<MusicPlayer>> REMOVE_BUTTON = REMOVE;

	private ButtonFactory() {
	}

	/**
	 * This method creates the chosen type of button.

	 * NOTE: Now It's fully generic!
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
	public static <C extends Player> FunctionalButton<C> createButton(
			final ButtonStrategy<C, AbstractStrategicalButton<C>> strategy, 
			final C controller, final boolean showTitle) {

		return new FunctionalButton<C>(controller, showTitle, strategy);
	}
}
