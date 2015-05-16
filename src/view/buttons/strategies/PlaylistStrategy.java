package view.buttons.strategies;

import static view.config.Configuration.*;
import static view.config.Utility.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.BiConsumer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import model.PlayerState;
import view.buttons.AbstractStratBtn;
import view.interfaces.BtnStrategy;
import view.model.MyFileChooser;
import controller.musicplayer.MusicPlayer;

/**
 * 
 * 
 * @author Alessandro
 *
 */
public enum PlaylistStrategy implements
		BtnStrategy<MusicPlayer, AbstractStratBtn<MusicPlayer>, PlayerState> {
	
	ADD("Add", ADD_IMG, (c, idx) ->{
		final MyFileChooser chooser = new MyFileChooser(JFileChooser.FILES_AND_DIRECTORIES);
		final int val = chooser.showOpenDialog(null);

		if (val == JFileChooser.APPROVE_OPTION) {
			/*
			 * NOTE: Now I can add Multiple folders and files, but as now, I
			 * can't add subfolder! I need to create a recorsive method, but if
			 * a users choose the rootfolderthat would become a problem :D
			 */
			for (final File f : chooser.getSelectedFiles()) {
				if (f.isDirectory()) {
					for (final File file : f.listFiles(chooser.getMyFileFilter())) {
						try {
							c.addSong(new URL(anURLPathBuilder(file.getAbsolutePath())));
						} catch ( IllegalArgumentException | MalformedURLException e) {
							showErrorDialog(null, "Invalid song format ");
						}
					}
				} else {
					try {
						c.addSong(new URL(anURLPathBuilder(f.getAbsolutePath())));
					} catch ( IllegalArgumentException | MalformedURLException e) {
						showErrorDialog(null, "Invalid song format ");
					}
				}
			}
		} else if (val != JFileChooser.CANCEL_OPTION) {
			showErrorDialog(null, "An Error has occurred");
		}
	}, null), 
	REMOVE("Remove", REMOVE_IMG, (c, idx)->{
		try {
			for (final int i : idx) {
				c.removeSong(i);
			}
			idx = new int[] { -1 };
		} catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
			showErrorDialog(null, "Invalid object selected!");
		}
	}, null);

	private ImageIcon img;
	private String title;
	private BiConsumer<MusicPlayer, int[]> ctrlUser;
	private BiConsumer<AbstractStratBtn<MusicPlayer>, PlayerState> updater;

	private int[] selectedIndexes = { -1 };

	private PlaylistStrategy(final String title, final ImageIcon img,
			final BiConsumer<MusicPlayer, int[]> ctrlUser,
			final BiConsumer<AbstractStratBtn<MusicPlayer>, PlayerState> updater) {
		this.img = img;
		this.title = title;
		this.ctrlUser = ctrlUser;
		this.updater = updater;
	}

	@Override
	public ImageIcon getImage() {

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