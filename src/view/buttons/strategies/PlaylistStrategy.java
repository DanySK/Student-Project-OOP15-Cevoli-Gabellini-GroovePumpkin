package view.buttons.strategies;

import static view.config.Configuration.ADD_IMG;
import static view.config.Configuration.REMOVE_IMG;
import static view.config.Utility.anURLPathBuilder;
import static view.config.Utility.showErrorDialog;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.BiConsumer;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import model.PlayerState;
import view.buttons.AbsStratBtn;
import view.interfaces.BtnStrategy;
import view.viewModel.MyFileChooser;
import controller.musicplayer.MusicPlayer;

/**
 * 
 * 
 * @author Alessandro
 *
 */
public enum PlaylistStrategy implements
		BtnStrategy<MusicPlayer, AbsStratBtn<MusicPlayer>, PlayerState> {
	
	ADD("Add", ADD_IMG, (c, u) -> c.addSong(u), null), 
	REMOVE("Remove", REMOVE_IMG, null, null);

	private ImageIcon img;
	private String title;
	private BiConsumer<MusicPlayer, URL> ctrlUser;
	private BiConsumer<AbsStratBtn<MusicPlayer>, PlayerState> updater;

	private int[] selectedIndexes = { -1 };

	private PlaylistStrategy(final String title, final ImageIcon img,
			final BiConsumer<MusicPlayer, URL> ctrlUser,
			final BiConsumer<AbsStratBtn<MusicPlayer>, PlayerState> updater) {
		this.img = img;
		this.title = title;
		this.ctrlUser = ctrlUser;
		this.updater = updater;
	}

	private void remove(final MusicPlayer controller) {
		try {
			for (final int i : selectedIndexes) {
				controller.removeSong(i);
			}
			selectedIndexes = new int[] { -1 };
		} catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
			showErrorDialog(null, "Invalid object selected!");
		}
	}

	private void add(final MusicPlayer controller) {
		// aggiungi una canzone
		final MyFileChooser chooser = new MyFileChooser();

		final int val = chooser.showOpenDialog(null);

		if (val == JFileChooser.APPROVE_OPTION) {
			/*
			 * NOTE: Now I can add Multiple folders and files, but as now, I
			 * can't add subfolder! I need to create a recorsive method, but if
			 * a users choose the rootfolderthat would become a problem :D
			 */
			for (final File i : chooser.getSelectedFiles()) {
				if (i.isDirectory()) {
					for (final File file : i.listFiles(chooser.getMyFileFilter())) {
						try {
							ctrlUser.accept(controller,
									new URL(anURLPathBuilder(file.getAbsolutePath())));
						} catch ( IllegalArgumentException | MalformedURLException e) {
							showErrorDialog(null, "Invalid song format ");
						}
					}
				} else {
					try {
						ctrlUser.accept(controller,
								new URL(anURLPathBuilder(i.getAbsolutePath())));
					} catch ( IllegalArgumentException | MalformedURLException e) {
						showErrorDialog(null, "Invalid song format ");
					}
				}
			}
		} else if (val != JFileChooser.CANCEL_OPTION) {
			showErrorDialog(null, "An Error has occurred");
		}
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
		if (this.equals(ADD)) {
			this.add(controller);
		} else {
			this.remove(controller);
		}
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
	public void updateUser(AbsStratBtn<MusicPlayer> button, PlayerState status) {
		if (updater != null) {
			updater.accept(button, status);
		}
	}

}