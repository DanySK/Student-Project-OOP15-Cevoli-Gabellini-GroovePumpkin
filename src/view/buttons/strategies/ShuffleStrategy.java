package view.buttons.strategies;

import static view.config.Configuration.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.swing.Icon;

import model.PlayerState;
import model.playlistmanager.FeaturesHandled;
import view.buttons.AbstractStratBtn;
import view.buttons.strategies.consumers.ShuffleConsumer;
import view.interfaces.BtnStrategy;
import controller.musicplayer.PlaylistFeatureCommand;

/**
 * This enum implements the strategy to to Shuffle and Ushuffle 
 * an object that implements Shuffable Interface
 * 
 * @author Alessandro
 *
 */
public enum ShuffleStrategy implements
		BtnStrategy<PlaylistFeatureCommand, AbstractStratBtn<PlaylistFeatureCommand>, PlayerState> {
	
	SHUFFLE("Shuffle", getConfig().getUnshuffleImage(), 
			c -> c.setPlaylistFeature(FeaturesHandled.SHUFFLE, true),
			new ShuffleConsumer()), 
	UNSHUFFLE("Unshuffle", getConfig().getShuffleImage(), 
			c -> c.setPlaylistFeature(FeaturesHandled.SHUFFLE, false), 
			new ShuffleConsumer());

	private String title;
	private Icon img;
	private Consumer<PlaylistFeatureCommand> ctrlUser;
	private BiConsumer<AbstractStratBtn<PlaylistFeatureCommand>, PlayerState> updater;

	private ShuffleStrategy(final String title,	final Icon img,
			final Consumer<PlaylistFeatureCommand> ctrlUser,
			final BiConsumer<AbstractStratBtn<PlaylistFeatureCommand>, PlayerState> updater) {
		this.title = title;
		this.img = img;
		this.ctrlUser = ctrlUser;
		this.updater = updater;
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
	public void doStrategy(final PlaylistFeatureCommand t) {
		this.ctrlUser.accept(t);
	}

	@Override
	public void updateUser(final AbstractStratBtn<PlaylistFeatureCommand> b,
			final PlayerState s) {
		if (updater != null) {
			this.updater.accept(b, s);
		}
	}
}
