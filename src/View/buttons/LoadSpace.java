package View.buttons;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import controller.GrooveBoxController;
import static Model.Utility.*;

/**
 * 
 * @author Alessandro
 *
 */
public class LoadSpace extends PersonalJButton {

	private static final long serialVersionUID = 3196123775349555103L;
	private static final String TITLE = "Load";

	protected LoadSpace(final GrooveBoxController controller,
			final boolean showTitle) {
		super(LOAD_IMG);
		super.setController(controller);
		doShow(showTitle, TITLE);

		this.addActionListener(e -> {
			// carica un pattern per la groovebox
			final JFileChooser chooser = new JFileChooser(System
					.getProperty("user.home"));

			// chooser.addChoosableFileFilter(new MyFileFilter());
			chooser.setVisible(true);

			final int val = chooser.showOpenDialog(LoadSpace.this);
			if (val == JFileChooser.APPROVE_OPTION) {
				final File f = chooser.getSelectedFile();

				// controller.

				System.out.println(f.getName());
			} else if (val != JFileChooser.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(LoadSpace.this,
						"An Error has occurred", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			}
		});
	}
}
