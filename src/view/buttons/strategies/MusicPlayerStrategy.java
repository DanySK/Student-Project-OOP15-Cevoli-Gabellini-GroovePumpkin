package view.buttons.strategies;

import static view.config.Utility.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.swing.ImageIcon;

import model.PlayerState;
import view.buttons.MusicPlayerButton;
import view.viewModel.ShuffleConsumer;
import view.viewModel.Strategy;
import controller.MusicPlayer;

public enum MusicPlayerStrategy implements Strategy<MusicPlayer>,
		BiConsumer<MusicPlayerButton, PlayerState> {
	
	FORWARD("Forward", FW_IMG, c -> c.goToNextSong(), null), 
	REWIND("Rewind", RW_IMG, c -> c.goToPreviousSong(), null),
	SHUFFLE("Shuffle", UNSHUFFLED_IMG, c -> c.setShuffleMode(true), new ShuffleConsumer()),
	UNSHUFFLE("Unshuffle", SHUFFLED_IMG, c -> c.setShuffleMode(false), new ShuffleConsumer());
	
	private String title;
	private ImageIcon img;
	private Consumer<MusicPlayer> ctrlUser;
	private BiConsumer<MusicPlayerButton, PlayerState> updater;

	private MusicPlayerStrategy(final String title, final ImageIcon img,
			final Consumer<MusicPlayer> ctrlUser,
			final BiConsumer<MusicPlayerButton, PlayerState> updater) {
		this.title = title;
		this.img = img;
		this.ctrlUser= ctrlUser;
		this.updater= updater;
	}

	public String getTitle() {
		return title;
	}

	public ImageIcon getImage() {
		return img;
	}

	@Override
	public void doStrategy(final MusicPlayer t) {
		//System.out.println(this);
		this.ctrlUser.accept(t);
	}

	@Override
	public void accept(final MusicPlayerButton b, final PlayerState s) {
		if(updater!=null){
			this.updater.accept(b, s);
		}
	}
}
