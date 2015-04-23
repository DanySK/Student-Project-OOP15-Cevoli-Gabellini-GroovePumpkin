package bbb;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import controller.MusicPlayer;
import static bbb.Utility.*;

/**
 * This class implements the action for adding a Single song, Multiple songs or
 * a whole directory of songs
 * 
 * @author Alessandro
 *
 */
public class AddSpace extends PersonalJButton {

	private static final long serialVersionUID = -7690938463308760102L;
	private static final String TITLE = "Add";

	protected AddSpace(final MusicPlayer controller, final boolean showTitle) {
		super(ADD_IMG);
		super.setController(controller);

		doShow(showTitle, TITLE);

		this.addActionListener(e -> {
			// aggiungi una canzone
			final JFileChooser chooser = new JFileChooser(System
					.getProperty("user.home"));
			final MyFileFilter filter = new MyFileFilter();
			//This way only supported files will be showed
			chooser.removeChoosableFileFilter(chooser.getFileFilter());
			chooser.setFileFilter(filter);
			
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setMultiSelectionEnabled(true);
			chooser.setVisible(true);

			final int val = chooser.showOpenDialog(AddSpace.this);
			final List<File> f = new ArrayList<>();

			if (val == JFileChooser.APPROVE_OPTION) {
				
				/*
				 * NOTE: Now I can add Multiple folders and files, but as now, I
				 * can't add subfolder! I need to create a recorsive method, but
				 * if a users choose the rootfolderthat would become a problem
				 * :D
				 */
				for (final File i : chooser.getSelectedFiles()) {
					if (i.isDirectory()) {
						for (final File file : i.listFiles(filter)) {
							f.add(file);
						}
					} else {
						f.add(i);
					}
				}

				try {
					for (final File i : f) {
						controller.addSong(new URL(anURLPathBuilder(i
								.getAbsolutePath())));
					}
				} catch (Exception e1) {
					showErrorDialog(AddSpace.this, "Invalid song format ");
				}

			} else if (val != JFileChooser.CANCEL_OPTION) {
				showErrorDialog(AddSpace.this, "An Error has occurred");
			}
		});
	}

	private static class MyFileFilter extends FileFilter implements
			FilenameFilter {
		@Override
		public String getDescription() {
			return "*.midi, *.wav";
		}

		@Override
		public boolean accept(File f) {

			if (f.isDirectory() || f.getName().endsWith(".midi")
					|| f.getName().endsWith(".wav")) {
				return true;
			}

			return false;
		}

		@Override
		public boolean accept(final File dir, final String name) {

			if (name.endsWith(".midi") || name.endsWith(".wav")) {
				return true;
			}

			return false;
		}
	}
}
