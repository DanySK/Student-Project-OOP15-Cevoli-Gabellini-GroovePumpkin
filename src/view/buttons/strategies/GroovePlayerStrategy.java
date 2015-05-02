package view.buttons.strategies;

import java.io.File;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.PlayerState;
import view.buttons.StrategicalButton;
import controller.GrooveBoxPlayer;
import static view.config.Utility.*;

/**
 * This strategy class implements the function of
 * saving and loading patterns from a groovebox player
 * 
 * @author Alessandro
 *
 */
public enum GroovePlayerStrategy implements
		ButtonStrategy<GrooveBoxPlayer, StrategicalButton<GrooveBoxPlayer>> {
	SAVE("Save", SAVE_IMG, null, null), 
	LOAD("Load", LOAD_IMG, null, null);

	private ImageIcon img;
	private String title;
	
	@SuppressWarnings("unused")
	private Consumer<GrooveBoxPlayer> ctrlUser;
	private BiConsumer<StrategicalButton<GrooveBoxPlayer>, PlayerState> updater;

	private GroovePlayerStrategy(
			final String title,
			final ImageIcon img,
			final Consumer<GrooveBoxPlayer> ctrlUser,
			final BiConsumer<StrategicalButton<GrooveBoxPlayer>, PlayerState> updater) {
		this.img = img;
		this.title = title;
		this.ctrlUser = ctrlUser;
		this.updater = updater;
	}

	private void load(final GrooveBoxPlayer t) {
		// carica un pattern per la groovebox
		final JFileChooser chooser = new JFileChooser(
				System.getProperty("user.home"));

		// chooser.addChoosableFileFilter(new MyFileFilter());
		chooser.setVisible(true);

		final int val = chooser.showOpenDialog(null);
		if (val == JFileChooser.APPROVE_OPTION) {
			final File f = chooser.getSelectedFile();

			// controller.

			System.out.println(f.getName());
		} else if (val != JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null,
					"An Error has occurred", "Error Message",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void save(final GrooveBoxPlayer t) {
		final JFileChooser chooser = new JFileChooser(System
				.getProperty("user.home"));

		chooser.setVisible(true);
		final int val = chooser.showSaveDialog(null);
		if (val == JFileChooser.APPROVE_OPTION) {

			//save the file
			//controller.savePattern();

		} else if (val != JFileChooser.CANCEL_OPTION) {
			showErrorDialog(null, "An Error has occurred");
		}
	}

	@Override
	public void doStrategy(final GrooveBoxPlayer t) {
		if(this.equals(SAVE)){
			save(t);
		}else{
			load(t);
		}
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
	public void update(StrategicalButton<GrooveBoxPlayer> button,
			PlayerState status) {
		if (updater != null) {
			updater.accept(button, status);
		}
	}
}
