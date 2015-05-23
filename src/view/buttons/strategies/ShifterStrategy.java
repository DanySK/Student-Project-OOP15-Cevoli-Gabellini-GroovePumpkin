package view.buttons.strategies;

import static view.config.Configuration.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.swing.Icon;
import model.PlayerState;
import view.buttons.AbstractStratBtn;
import view.interfaces.BtnStrategy;
import controller.musicplayer.MusicPlayer;

/**
 * This strategy class implements the actions for
 * Rewinding, Forwarding an object 
 * that implements a MusicPlayer controller type
 * 
 * @author Alessandro
 *
 */
public enum ShifterStrategy implements 
		BtnStrategy<MusicPlayer, AbstractStratBtn<MusicPlayer>, PlayerState>{
	
	FORWARD("Forward", getConfig().getFwImage(), c -> c.goToNextSong(), null), 
	BACKWARD("Backward", getConfig().getBwImage(), c -> c.goToPreviousSong(), null);
	
	private String title;
	private Icon img;
	private Consumer<MusicPlayer> ctrlUser;
	private BiConsumer<AbstractStratBtn<MusicPlayer>, PlayerState> updater;

	private ShifterStrategy(final String title, final Icon img,
			final Consumer<MusicPlayer> ctrlUser,
			final BiConsumer<AbstractStratBtn<MusicPlayer>, PlayerState> updater) {
		this.title = title;
		this.img = img;
		this.ctrlUser= ctrlUser;
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
	public void doStrategy(final MusicPlayer t) {
		this.ctrlUser.accept(t);
	}

	@Override
	public void updateUser(final AbstractStratBtn<MusicPlayer> b, final PlayerState s) {
		if(updater!=null){
			this.updater.accept(b, s);
		}
	}
}
