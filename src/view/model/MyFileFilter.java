package view.model;

import java.io.File;
import java.io.FilenameFilter;

import javax.swing.filechooser.FileFilter;

public class MyFileFilter extends FileFilter implements FilenameFilter {
	@Override
	public String getDescription() {
		return "*.midi, *.wav";
	}

	@Override
	public boolean accept(final File f) {

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