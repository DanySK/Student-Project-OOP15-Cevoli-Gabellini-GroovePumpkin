package view.buttons.strategies;

import static view.config.Configuration.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.swing.Icon;
import model.PlayerState;
import view.buttons.AbstractStratBtn;
import view.buttons.strategies.consumers.ShuffleConsumer;
import view.interfaces.BtnStrategy;
import controller.musicplayer.Shuffable;

public enum ShuffleStrategy implements
		BtnStrategy<Shuffable, AbstractStratBtn<Shuffable>, PlayerState> {
	
	SHUFFLE("Shuffle", getConfig().getUnshuffleImage(), c -> c.setShuffleMode(true),	new ShuffleConsumer()), 
	UNSHUFFLE("Unshuffle", getConfig().getShuffleImage(), c -> c.setShuffleMode(false), new ShuffleConsumer());

	private String title;
	private Icon img;
	private Consumer<Shuffable> ctrlUser;
	private BiConsumer<AbstractStratBtn<Shuffable>, PlayerState> updater;

	private ShuffleStrategy(final String title,	final Icon img,
			final Consumer<Shuffable> ctrlUser,
			final BiConsumer<AbstractStratBtn<Shuffable>, PlayerState> updater) {
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
	public void doStrategy(final Shuffable t) {
		this.ctrlUser.accept(t);
	}

	@Override
	public void updateUser(final AbstractStratBtn<Shuffable> b,
			final PlayerState s) {
		if (updater != null) {
			this.updater.accept(b, s);
		}
	}
}
