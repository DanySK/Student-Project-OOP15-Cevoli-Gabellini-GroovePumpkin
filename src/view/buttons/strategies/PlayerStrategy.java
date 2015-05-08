package view.buttons.strategies;

import static view.config.Utility.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.swing.ImageIcon;

import model.PlayerState;
import controller.Player;
import view.buttons.AbsStratBtn;
import view.buttons.strategies.consumers.PlayPauseConsumer;
import view.buttons.strategies.consumers.StopConsumer;
import view.interfaces.BtnStrategy;
import static view.config.Configuration.*;

/**
 * This enum implements Play, Pause and Stop strategies 
 * that could be used by an object that
 * can communicate with a Player controller type
 * 
 * @author Alessandro
 *
 */
public enum PlayerStrategy implements 
		BtnStrategy<Player, AbsStratBtn<Player>, PlayerState>{

	PLAY("Play", PLAY_IMG, c -> c.play(), new PlayPauseConsumer()), 
	PAUSE("Pause", PAUSE_IMG, c -> c.pause(), new PlayPauseConsumer()), 
	STOP("Stop", STOP_IMG, c -> c.stop(), new StopConsumer());

	private String title;
	private ImageIcon img;
	private Consumer<Player> ctrlUser;
	private BiConsumer<AbsStratBtn<Player>, PlayerState> updater;

	private PlayerStrategy(final String title, final ImageIcon img,
			final Consumer<Player> ctrlUser,
			final BiConsumer<AbsStratBtn<Player>, PlayerState> updater) {
		
		this.title = title;
		this.img = img;
		this.ctrlUser = ctrlUser;
		this.updater= updater;
	}

	public String getTitle() {
		return title;
	}

	public ImageIcon getImage() {
		return img;
	}

	@Override
	public void doStrategy(final Player controller) {
		try {
			this.ctrlUser.accept(controller);
		} catch (final Exception e) {
			showErrorDialog(null, "No item found");
		}
	}
	
	@Override
	public void updateUser(final AbsStratBtn<Player> b, final PlayerState s) {
		if(updater!=null){
			updater.accept(b, s);
		}
	}
}
