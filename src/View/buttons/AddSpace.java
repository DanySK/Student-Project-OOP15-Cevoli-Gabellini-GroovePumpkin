package View.buttons;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import controller.MusicPlayer;
import static Model.Utility.*;

public class AddSpace extends PersonalJButton {

	private static final long serialVersionUID = -7690938463308760102L;
	private static final String TITLE= "Add";
	
	protected AddSpace(final MusicPlayer controller, final boolean showTitle) {
		super(ADD_IMG);
		super.setController(controller);
		
		if(showTitle){
			this.setTitle(TITLE);
		}
		
		this.addActionListener(e->{
			// aggiungi una canzone
			JFileChooser chooser = new JFileChooser(System
					.getProperty("user.home"));

			chooser.addChoosableFileFilter(new MyFileFilter());
			chooser.setVisible(true);
			
			int val = chooser.showOpenDialog(AddSpace.this);
			if (val == JFileChooser.APPROVE_OPTION) {
				final File f = chooser.getSelectedFile();
				
				//controller.
				
				System.out.println(f.getName());
			} else if (val != JFileChooser.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(AddSpace.this,
						"An Error has occurred", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	private static class MyFileFilter extends FileFilter{
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
	}
}
