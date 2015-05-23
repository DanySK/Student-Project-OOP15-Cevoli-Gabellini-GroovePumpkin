package view.buttons.strategies;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.BiConsumer;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import model.PlayerState;
import view.buttons.AbstractStratBtn;
import view.interfaces.BtnStrategy;
import view.model.MyFileChooser;
import view.model.MyFileFilter;
import controller.musicplayer.MusicPlayer;
import static view.config.Configuration.*;
import static view.config.Utility.*;

/**
 * This enum implements the strategy to Add and Remove 
 * an object that implements a MusicPlayer controller type
 * 
 * @author Alessandro
 *
 */
public enum PlaylistStrategy implements
		BtnStrategy<MusicPlayer, AbstractStratBtn<MusicPlayer>, PlayerState> {
	
	ADD("Add", getConfig().getAddImage(), (ctrl, idx) ->{
		final MyFileChooser fc = new MyFileChooser(
				JFileChooser.FILES_AND_DIRECTORIES, 
				MyFileFilter.ExtensionStrategy.MIDI_AND_WAVE);
		final int val = fc.showOpenDialog(null);
		
		if (val == JFileChooser.APPROVE_OPTION) {
			/*
			 * NOTE: Now I can add Multiple folders and files, but as now, I
			 * can't add subfolder! I need to create a recorsive method, but if
			 * a users choose the rootfolder that would became a problem :D
			 */
			for (final File f : fc.getSelectedFiles()) {
				if (f.isDirectory()) {
					for (final File file : f.listFiles(fc.getMyFileFilter())) {
						try {
							ctrl.addSong(new URL(anURLPathBuilder(file.getAbsolutePath())));
						} catch ( IllegalArgumentException | MalformedURLException e) {
							showErrorDialog(null, "Invalid song format ");
						}
					}
				} else {
					try {
						ctrl.addSong(new URL(anURLPathBuilder(f.getAbsolutePath())));
					} catch ( IllegalArgumentException | MalformedURLException e) {
						showErrorDialog(null, "Invalid song format ");
					}
				}
			}
		} else if (val != JFileChooser.CANCEL_OPTION) {
			showErrorDialog(null, "An Error has occurred");
		}
	}, null), 
	REMOVE("Remove", getConfig().getRemoveImage(), (ctrl, idx)->{
		try {
			for (final int i : idx) {
				ctrl.removeSong(i);
			}
			idx = new int[] { -1 };
		} catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
			showErrorDialog(null, "Invalid object selected!");
		}
	}, null);

	private Icon img;
	private String title;
	private BiConsumer<MusicPlayer, int[]> ctrlUser;
	private BiConsumer<AbstractStratBtn<MusicPlayer>, PlayerState> updater;

	private int[] selectedIndexes = { -1 };

	private PlaylistStrategy(final String title, final Icon img,
			final BiConsumer<MusicPlayer, int[]> ctrlUser,
			final BiConsumer<AbstractStratBtn<MusicPlayer>, PlayerState> updater) {
		this.img = img;
		this.title = title;
		this.ctrlUser = ctrlUser;
		this.updater = updater;
	}

	@Override
	public Icon getImage() {

		return this.img;
	}

	@Override
	public String getTitle() {

		return this.title;
	}

	@Override
	public void doStrategy(MusicPlayer controller) {
		this.ctrlUser.accept(controller, selectedIndexes);
	}
	
	/**
	 * Set which indexes have been selected 
	 * 
	 * @param idx
	 */
	public void setSelectedIndexes(final int... idx) {
		this.selectedIndexes = idx;
	}

	@Override
	public void updateUser(final AbstractStratBtn<MusicPlayer> button, 
			final PlayerState status) {
		if (updater != null) {
			updater.accept(button, status);
		}
	}

}