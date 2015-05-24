package view.buttons;

import static view.buttons.strategies.PlayerStrategy.*;
import static view.buttons.strategies.PlaylistStrategy.*;
import static view.buttons.strategies.ShifterStrategy.*;
import static view.buttons.strategies.LoopStrategy.*;
import static view.buttons.strategies.GroovePlayerStrategy.*;
import static view.buttons.strategies.ShuffleStrategy.*;
import java.awt.event.KeyEvent;
import model.PlayerState;
import org.hamcrest.Factory;
import view.interfaces.BtnStrategy;
import view.model.AbstractKeyListener;
import controller.*;
import controller.groovebox.GrooveBoxPlayer;
import controller.musicplayer.MusicPlayer;
import controller.musicplayer.PlaylistFeatureCommand;


/**
 * A simple factory class to simply 
 * creates functional Buttons
 * 
 * NOTE:
 * Each chosen strategy depends on the 
 * generic argument which define the controller
 * 
 * @author Alessandro
 *
 */
public final class ButtonFactory {

	/**
	 * 
	 */
	public static final BtnStrategy<Player, AbstractStratBtn<Player>, PlayerState> PLAY_B = PLAY;
	public static final BtnStrategy<Player, AbstractStratBtn<Player>, PlayerState> PAUSE_B = PAUSE;
	public static final BtnStrategy<Player, AbstractStratBtn<Player>, PlayerState> STOP_B = STOP;
	public static final BtnStrategy<Loopable, AbstractStratBtn<Loopable>, PlayerState> LOOP_B = LOOP;
	public static final BtnStrategy<PlaylistFeatureCommand, AbstractStratBtn<PlaylistFeatureCommand>, PlayerState> SHUFFLE_B = SHUFFLE;
	public static final BtnStrategy<GrooveBoxPlayer, AbstractStratBtn<GrooveBoxPlayer>, PlayerState> SAVE_B = SAVE;
	public static final BtnStrategy<GrooveBoxPlayer, AbstractStratBtn<GrooveBoxPlayer>, PlayerState> LOAD_B = LOAD;
	public static final BtnStrategy<GrooveBoxPlayer, AbstractStratBtn<GrooveBoxPlayer>, PlayerState> RESET_B = RESET; 
	public static final BtnStrategy<MusicPlayer, AbstractStratBtn<MusicPlayer>, PlayerState> FW_B = FORWARD;
	public static final BtnStrategy<MusicPlayer, AbstractStratBtn<MusicPlayer>, PlayerState> RW_B = BACKWARD;
	public static final BtnStrategy<MusicPlayer, AbstractStratBtn<MusicPlayer>, PlayerState> ADD_B = ADD;
	public static final BtnStrategy<MusicPlayer, AbstractStratBtn<MusicPlayer>, PlayerState> REMOVE_B = REMOVE;

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
	public static <C> AbstractStratBtn<C> createButton(
			final BtnStrategy<C, AbstractStratBtn<C>, PlayerState> strategy,
			final C controller, final boolean showTitle) {
		
		final AbstractStratBtn<C> b= new AbstractStratBtn<C>(controller, strategy, showTitle){

			private static final long serialVersionUID = 3239121438125525581L;
		};
		
		if (strategy.equals(STOP)) {
			b.setEnabled(false);
		} else if(strategy.equals(PLAY) 
				|| strategy.equals(PAUSE)){
			System.out.println(strategy);          
			b.addKeyListener(new AbstractKeyListener() {
				
				@Override
				public void keyPressed(final KeyEvent e) {
					if(e.getKeyChar()== KeyEvent.VK_SPACE){
						b.doStrategy();
					}
				}
			});
		}
		
		b.addActionListener(e -> {
			// go to the next song
			b.doStrategy();
		});
		
		return b;
	}
}
