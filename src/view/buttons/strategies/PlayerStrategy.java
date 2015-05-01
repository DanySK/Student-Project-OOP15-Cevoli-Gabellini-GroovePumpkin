package view.buttons.strategies;

import static view.config.Utility.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.swing.ImageIcon;

import model.PlayerState;
import view.buttons.PlayerButton;
import view.viewModel.PlayPauseConsumer;
import view.viewModel.StopConsumer;
import view.viewModel.Strategy;
import controller.Player;

/**
 * This enum implements various strategies 
 * that could be used by an object
 * 
 * @author Alessandro
 *
 */
public enum PlayerStrategy implements Strategy<Player>, BiConsumer<PlayerButton, PlayerState>{

	PLAY("Play", PLAY_IMG, c -> c.play(), new PlayPauseConsumer()), 
	PAUSE("Pause", PAUSE_IMG, c -> c.pause(), new PlayPauseConsumer()), 
	STOP("Stop", STOP_IMG, c -> c.stop(), new StopConsumer());

	private String title;
	private ImageIcon img;
	private Consumer<Player> ctrlUser;
	private BiConsumer<PlayerButton, PlayerState> updater;

	private PlayerStrategy(final String title, final ImageIcon img,
			final Consumer<Player> ctrlUser,
			final BiConsumer<PlayerButton, PlayerState> updater) {
		
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
	public void accept(final PlayerButton b, final PlayerState s) {
		updater.accept(b, s);
	}
}
