package view.buttons.strategies;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import model.PlayerState;
import view.buttons.StrategicalButton;
import view.viewModel.MyFileChooser;
import controller.MusicPlayer;
import static view.config.Utility.*;

public enum PlaylistStrategy implements
		ButtonStrategy<MusicPlayer, StrategicalButton<MusicPlayer>> {
	ADD("Add", ADD_IMG, (c, u) -> c.addSong(u), null), REMOVE("Remove",
			REMOVE_IMG, null, null);

	private ImageIcon img;
	private String title;
	private BiConsumer<MusicPlayer, URL> ctrlUser;
	private BiConsumer<StrategicalButton<MusicPlayer>, PlayerState> updater;

	private int[] selectedIndexes = { -1 };

	private PlaylistStrategy(
			final String title,
			final ImageIcon img,
			final BiConsumer<MusicPlayer, URL> ctrlUser,
			final BiConsumer<StrategicalButton<MusicPlayer>, PlayerState> updater) {
		this.img = img;
		this.title = title;
		this.ctrlUser = ctrlUser;
		this.updater = updater;
	}

	private void removeStrategy(final MusicPlayer controller) {
		try {
			for (final int i : selectedIndexes) {
				controller.removeSong(i);
			}
			selectedIndexes = new int[] { -1 };
		} catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
			showErrorDialog(null, "Invalid object selected!");
		}
	}

	private void addStrategy(final MusicPlayer controller) {
		// aggiungi una canzone
		final MyFileChooser chooser = new MyFileChooser();

		final int val = chooser.showOpenDialog(null);
		final List<File> f = new ArrayList<>();

		if (val == JFileChooser.APPROVE_OPTION) {

			/*
			 * NOTE: Now I can add Multiple folders and files, but as now, I
			 * can't add subfolder! I need to create a recorsive method, but if
			 * a users choose the rootfolderthat would become a problem :D
			 */
			for (final File i : chooser.getSelectedFiles()) {
				if (i.isDirectory()) {
					for (final File file : i.listFiles(chooser
							.getMyFileFilter())) {
						f.add(file);
					}
				} else {
					f.add(i);
				}
			}

			try {
				for (final File i : f) {
					ctrlUser.accept(controller,
							new URL(anURLPathBuilder(i.getAbsolutePath())));
				}
			} catch (IllegalArgumentException | MalformedURLException e1) {
				e1.printStackTrace();
				showErrorDialog(null, "Invalid song format ");
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
			this.addStrategy(controller);
		} else {
			this.removeStrategy(controller);
		}
	}

	public void setSelectedIndexes(final int... idx) {
		this.selectedIndexes = idx;
	}

	@Override
	public void update(StrategicalButton<MusicPlayer> button, PlayerState status) {
		if (updater != null) {
			updater.accept(button, status);
		}
	}

}