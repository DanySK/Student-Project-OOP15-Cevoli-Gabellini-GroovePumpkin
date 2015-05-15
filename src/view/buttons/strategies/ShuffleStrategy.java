package view.buttons.strategies;

import static view.config.Configuration.SHUF_IMG;
import static view.config.Configuration.UNSHUF_IMG;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.swing.ImageIcon;

import model.PlayerState;
import view.buttons.AbsStratBtn;
import view.buttons.strategies.consumers.ShuffleConsumer;
import view.interfaces.BtnStrategy;
import controller.musicplayer.Shuffable;

public enum ShuffleStrategy implements
		BtnStrategy<Shuffable, AbsStratBtn<Shuffable>, PlayerState> {
	
	SHUFFLE("Shuffle", UNSHUF_IMG, c -> c.setShuffleMode(true),	new ShuffleConsumer()), 
	UNSHUFFLE("Unshuffle", SHUF_IMG, c -> c.setShuffleMode(false), new ShuffleConsumer());

	private String title;
	private ImageIcon img;
	private Consumer<Shuffable> ctrlUser;
	private BiConsumer<AbsStratBtn<Shuffable>, PlayerState> updater;

	private ShuffleStrategy(final String title,	final ImageIcon img,
			final Consumer<Shuffable> ctrlUser,
			final BiConsumer<AbsStratBtn<Shuffable>, PlayerState> updater) {
		this.title = title;
		this.img = img;
		this.ctrlUser = ctrlUser;
		this.updater = updater;
	}

	public String getTitle() {
		return title;
	}

	public ImageIcon getImage() {
		return img;
	}

	@Override
	public void doStrategy(final Shuffable t) {
		this.ctrlUser.accept(t);
	}

	@Override
	public void updateUser(final AbsStratBtn<Shuffable> b,
			final PlayerState s) {
		if (updater != null) {
			this.updater.accept(b, s);
		}
	}
}
