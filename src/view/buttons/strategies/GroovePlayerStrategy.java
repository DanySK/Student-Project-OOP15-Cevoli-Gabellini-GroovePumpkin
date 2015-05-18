package view.buttons.strategies;

import java.io.File;
import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import model.PlayerState;
import view.buttons.AbstractStratBtn;
import view.interfaces.BtnStrategy;
import view.model.MyFileChooser;
import view.model.MyFileFilter;
import controller.groovebox.GrooveBoxPlayer;
import static view.config.Utility.*;
import static view.config.Configuration.*;

/**
 * This strategy class implements the function of saving and loading patterns
 * from a groovebox player
 * 
 * @author Alessandro
 *
 */
public enum GroovePlayerStrategy implements
		BtnStrategy<GrooveBoxPlayer, AbstractStratBtn<GrooveBoxPlayer>, PlayerState> {

	SAVE("Save", SAVE_IMG, g -> {
			final MyFileChooser chooser = new MyFileChooser(
					JFileChooser.FILES_AND_DIRECTORIES, 
					MyFileFilter.ExtensionStrategy.MIDI);
			chooser.setMultiSelectionEnabled(false);
			chooser.setVisible(true);
			final int val = chooser.showSaveDialog(null);
			if (val == JFileChooser.APPROVE_OPTION) {
				try {
					g.saveTrack(String.join("", chooser.getSelectedFile()
							.getAbsolutePath(), ".midi"));
				} catch (IOException ex) {
					showErrorDialog(null,
							"An error has occurred while attempting to save the pattern!");
				}
			} else if (val != JFileChooser.CANCEL_OPTION) {
				showErrorDialog(null, "An Error has occurred");
			}
		}, null), 
	LOAD("Load", LOAD_IMG, g -> {
			// carica un pattern per la groovebox
			final MyFileChooser chooser = new MyFileChooser(
					JFileChooser.FILES_AND_DIRECTORIES, 
					MyFileFilter.ExtensionStrategy.MIDI);
			chooser.setMultiSelectionEnabled(false);
			chooser.setVisible(true);

			final int val = chooser.showOpenDialog(null);
			if (val == JFileChooser.APPROVE_OPTION) {
				@SuppressWarnings("unused")
				final File f = chooser.getSelectedFile();
				// choose the pattern to be loaded
		} else if (val != JFileChooser.CANCEL_OPTION) {
			showErrorDialog(null, "An Error has occurred");
		}
	}, null), 
	RESET("Reset", RESET_IMG, g -> g.reset(), null);

	private ImageIcon img;
	private String title;
	private Consumer<GrooveBoxPlayer> ctrlUser;
	private BiConsumer<AbstractStratBtn<GrooveBoxPlayer>, PlayerState> updater;

	private GroovePlayerStrategy(final String title, final ImageIcon img,
			final Consumer<GrooveBoxPlayer> ctrlUser,
			final BiConsumer<AbstractStratBtn<GrooveBoxPlayer>, PlayerState> updater) {
		this.img = img;
		this.title = title;
		this.ctrlUser = ctrlUser;
		this.updater = updater;
	}

	@Override
	public void doStrategy(final GrooveBoxPlayer t) {
		ctrlUser.accept(t);
	}

	@Override
	public ImageIcon getImage() {
		return img;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void updateUser(final AbstractStratBtn<GrooveBoxPlayer> button,
			final PlayerState status) {
		if (updater != null) {
			updater.accept(button, status);
		}
	}
}
