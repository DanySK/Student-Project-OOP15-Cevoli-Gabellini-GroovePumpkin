package view.buttons.strategies;

import java.io.File;
import java.io.IOException;
import java.util.function.BiConsumer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.PlayerState;
import view.buttons.AbsStratBtn;
import view.interfaces.BtnStrategy;
import view.model.MyFileChooser;
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
		BtnStrategy<GrooveBoxPlayer, AbsStratBtn<GrooveBoxPlayer>, PlayerState> {
	
	SAVE("Save", SAVE_IMG, (g,s)->{
		try{
			g.saveTrack(s);
		}catch(IOException ex){
			showErrorDialog(null, "An error has occurred while attempting to save the pattern!");
		}
	}, null), 
	LOAD("Load", LOAD_IMG, null, null);

	private ImageIcon img;
	private String title;

	private BiConsumer<GrooveBoxPlayer, String> ctrlUser;
	private BiConsumer<AbsStratBtn<GrooveBoxPlayer>, PlayerState> updater;

	private GroovePlayerStrategy(final String title, final ImageIcon img,
			final BiConsumer<GrooveBoxPlayer, String> ctrlUser,
			final BiConsumer<AbsStratBtn<GrooveBoxPlayer>, PlayerState> updater) {
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
			@SuppressWarnings("unused")
			final File f = chooser.getSelectedFile();

			// controller.

			//System.out.println(f.getName());
		} else if (val != JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "An Error has occurred",
					"Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void save(final GrooveBoxPlayer t) {
		final MyFileChooser chooser = new MyFileChooser(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setMultiSelectionEnabled(false);
		chooser.setVisible(true);
		final int val = chooser.showSaveDialog(null);
		if (val == JFileChooser.APPROVE_OPTION) {
			
			this.ctrlUser.accept(t, String.join("", chooser.getSelectedFile().getAbsolutePath(), ".midi"));
			
		} else if (val != JFileChooser.CANCEL_OPTION) {
			showErrorDialog(null, "An Error has occurred");
		}
	}

	@Override
	public void doStrategy(final GrooveBoxPlayer t) {
		if (this.equals(SAVE)) {
			save(t);
		} else {
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
	public void updateUser(final AbsStratBtn<GrooveBoxPlayer> button,
			final PlayerState status) {
		if (updater != null) {
			updater.accept(button, status);
		}
	}
}
