package view.buttons;

import javax.swing.JButton;

import view.buttons.strategies.MusicPlayerStrategy;
import view.buttons.strategies.PlayerStrategy;
import controller.GrooveBoxPlayer;
import controller.LoopablePlayer;
import controller.MusicPlayer;
import controller.Player;

/**
 * A simple factory class to simply creates functional Button
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
	public static final int FW_BUTTON= 7;
	public static final int RW_BUTTON= 8;
	public static final int ADD_BUTTON = 9;
	public static final int REMOVE_BUTTON = 10;

	private ButtonFactory() {
	}

	/**
	 * This method creates the chosen type of button
	 * 
	 * @param type
	 *            the type of button to create -> choose between the given
	 *            costants of this class
	 * @param showTitle
	 *            if the button have to show a TitledBorder or the built-in
	 *            border
	 * @param controller
	 *            to communicate with
	 * @return the chosen type of button or a normal button if a wrong value is chosen
	 */
	public static JButton createButton(final int type, final boolean showTitle,
			final Player controller) {

		switch (type) {
		case 0:
			return new PlayerButton((Player) controller, PlayerStrategy.PLAY, showTitle);
		case 1:
			return new PlayerButton((Player) controller, PlayerStrategy.PAUSE, showTitle);
		case 2:
			return new PlayerButton((Player) controller, PlayerStrategy.STOP, showTitle);
		case 3:
			return new LoopButton((LoopablePlayer) controller, showTitle);
		case 4:
			return new MusicPlayerButton((MusicPlayer) controller, showTitle, MusicPlayerStrategy.SHUFFLE);
		case 5:
			return new SaveButton((GrooveBoxPlayer) controller, showTitle);
		case 6:
			return new LoadButton((GrooveBoxPlayer) controller, showTitle);
		case 7:
			return new MusicPlayerButton((MusicPlayer) controller, showTitle, MusicPlayerStrategy.FORWARD);
		case 8:
			return new MusicPlayerButton((MusicPlayer) controller, showTitle, MusicPlayerStrategy.REWIND);
		case 9:
			return new AddButton((MusicPlayer) controller, showTitle);
		case 10:
			return new RemoveButton((MusicPlayer) controller, showTitle);
		default:
			return new JButton("Hai sbagliato type, pirla.");
		}
	}
}
