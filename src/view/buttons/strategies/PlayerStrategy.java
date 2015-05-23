package view.buttons.strategies;

import static view.config.Configuration.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.swing.Icon;
import model.PlayerState;
import view.buttons.AbstractStratBtn;
import view.buttons.strategies.consumers.PlayPauseConsumer;
import view.buttons.strategies.consumers.StopConsumer;
import view.interfaces.BtnStrategy;
import controller.Player;

/**
* This enum implements the strategy to Play, Pause and Stop 
 * an object that implements a Player controller type
 * 
 * @author Alessandro
 *
 */
public enum PlayerStrategy implements 
		BtnStrategy<Player, AbstractStratBtn<Player>, PlayerState>{

	PLAY("Play", getConfig().getPlayImage(), c -> c.play(), new PlayPauseConsumer()), 
	PAUSE("Pause", getConfig().getPauseImage(), c -> c.pause(), new PlayPauseConsumer()), 
	STOP("Stop", getConfig().getStopImage(), c -> c.stop(), new StopConsumer());

	private final String title;
	private final  Icon img;
	private final Consumer<Player> ctrlUser;
	private final BiConsumer<AbstractStratBtn<Player>, PlayerState> updater;

	private PlayerStrategy(final String title, final Icon img,
			final Consumer<Player> ctrlUser,
			final BiConsumer<AbstractStratBtn<Player>, PlayerState> updater) {
		
		this.title = title;
		this.img = img;
		this.ctrlUser = ctrlUser;
		this.updater= updater;
	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public Icon getImage() {
		return img;
	}

	@Override
	public void doStrategy(final Player controller) {
			this.ctrlUser.accept(controller);
	}
	
	@Override
	public void updateUser(final AbstractStratBtn<Player> b, final PlayerState s) {
		if(updater!=null){
			updater.accept(b, s);
		}
	}
}
