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
import controller.MusicPlayer;

public enum ShuffleStrategy implements
		BtnStrategy<MusicPlayer, AbsStratBtn<MusicPlayer>, PlayerState> {
	
	SHUFFLE("Shuffle", UNSHUF_IMG, c -> c.setShuffleMode(true),	new ShuffleConsumer()), 
	UNSHUFFLE("Unshuffle", SHUF_IMG, c -> c.setShuffleMode(false), new ShuffleConsumer());

	private String title;
	private ImageIcon img;
	private Consumer<MusicPlayer> ctrlUser;
	private BiConsumer<AbsStratBtn<MusicPlayer>, PlayerState> updater;

	private ShuffleStrategy(final String title,	final ImageIcon img,
			final Consumer<MusicPlayer> ctrlUser,
			final BiConsumer<AbsStratBtn<MusicPlayer>, PlayerState> updater) {
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
	public void doStrategy(final MusicPlayer t) {
		this.ctrlUser.accept(t);
	}

	@Override
	public void updateUser(final AbsStratBtn<MusicPlayer> b,
			final PlayerState s) {
		if (updater != null) {
			this.updater.accept(b, s);
		}
	}
}
