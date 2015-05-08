package view.buttons.strategies;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.swing.ImageIcon;

import model.PlayerState;
import view.buttons.AbsStratBtn;
import view.interfaces.BtnStrategy;
import controller.MusicPlayer;
import static view.config.Configuration.*;

/**
 * This strategy class implements the actions for
 * Rewinding, Forwarding and Shuffling of an object 
 * that can communicate with a MusicPlayer controller type
 * 
 * @author Alessandro
 *
 */
public enum ShifterStrategy implements 
		BtnStrategy<MusicPlayer, AbsStratBtn<MusicPlayer>, PlayerState>{
	
	FORWARD("Forward", FW_IMG, c -> c.goToNextSong(), null), 
	BACKWARD("Rewind", RW_IMG, c -> c.goToPreviousSong(), null);
	
	private String title;
	private ImageIcon img;
	private Consumer<MusicPlayer> ctrlUser;
	private BiConsumer<AbsStratBtn<MusicPlayer>, PlayerState> updater;

	private ShifterStrategy(final String title, final ImageIcon img,
			final Consumer<MusicPlayer> ctrlUser,
			final BiConsumer<AbsStratBtn<MusicPlayer>, PlayerState> updater) {
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
		this.ctrlUser.accept(t);
	}

	@Override
	public void updateUser(final AbsStratBtn<MusicPlayer> b, final PlayerState s) {
		if(updater!=null){
			this.updater.accept(b, s);
		}
	}
}
