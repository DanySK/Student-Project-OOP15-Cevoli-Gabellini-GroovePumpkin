package view.buttons;

import static view.buttons.strategies.PlayerStrategy.*;
import static view.buttons.strategies.PlaylistStrategy.*;
import static view.buttons.strategies.ShifterStrategy.*;
import static view.buttons.strategies.LoopStrategy.*;
import static view.buttons.strategies.GroovePlayerStrategy.*;
import static view.buttons.strategies.ShuffleStrategy.*;
import model.PlayerState;

import org.hamcrest.Factory;

import view.interfaces.BtnStrategy;
import controller.*;

/**
 * A simple factory class to simply creates functional Buttons
 * 
 * NOTE:
 * 
 * Each chosen strategy depends on the generic argument which define the controller
 * 
 * @author Alessandro
 *
 */
public final class ButtonFactory {

	/**
	 * 
	 */
	public static final BtnStrategy<Player, AbsStratBtn<Player>, PlayerState> PLAY_B = PLAY;
	public static final BtnStrategy<Player, AbsStratBtn<Player>, PlayerState> PAUSE_B = PAUSE;
	public static final BtnStrategy<Player, AbsStratBtn<Player>, PlayerState> STOP_B = STOP;
	public static final BtnStrategy<LoopablePlayer, AbsStratBtn<LoopablePlayer>, PlayerState> LOOP_B = LOOP;
	public static final BtnStrategy<MusicPlayer, AbsStratBtn<MusicPlayer>, PlayerState> SHUFFLE_B = SHUFFLE;
	public static final BtnStrategy<GrooveBoxPlayer, AbsStratBtn<GrooveBoxPlayer>, PlayerState> SAVE_B = SAVE;
	public static final BtnStrategy<GrooveBoxPlayer, AbsStratBtn<GrooveBoxPlayer>, PlayerState> LOAD_B = LOAD;
	public static final BtnStrategy<MusicPlayer, AbsStratBtn<MusicPlayer>, PlayerState> FW_B = FORWARD;
	public static final BtnStrategy<MusicPlayer, AbsStratBtn<MusicPlayer>, PlayerState> RW_B = BACKWARD;
	public static final BtnStrategy<MusicPlayer, AbsStratBtn<MusicPlayer>, PlayerState> ADD_B = ADD;
	public static final BtnStrategy<MusicPlayer, AbsStratBtn<MusicPlayer>, PlayerState> REMOVE_B = REMOVE;

	private ButtonFactory() {
	}

	/**
	 * This method creates the chosen type of button.
	 * 
	 * NOTE: Now It's fully generic!
	 * 
	 * @param Strategy
	 *            the type of button to create -> choose between the given
	 *            costants of this class
	 * @param showTitle
	 *            if the button have to show a TitledBorder or the built-in
	 *            border
	 * @param controller
	 *            to communicate with
	 * @return the chosen type of button 
	 */
	@Factory
	public static <C> AbsStratBtn<C> createButton(
			final BtnStrategy<C, AbsStratBtn<C>, PlayerState> strategy,
			final C controller, final boolean showTitle) {

		return new FunctionalButton<C>(controller, showTitle, strategy);
	}
}
