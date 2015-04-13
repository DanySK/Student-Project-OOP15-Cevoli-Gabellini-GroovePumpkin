package View.buttons;

import javax.swing.JFileChooser;
import controller.GrooveBoxController;
import static Model.Utility.*;

/**
 * 
 * @author Alessandro
 *
 */
public class SaveSpace extends PersonalJButton {

	private static final long serialVersionUID = 2740908838399780756L;
	private static final String TITLE = "Save";

	protected SaveSpace(final GrooveBoxController controller, final boolean showTitle) {
		super(SAVE_IMG);

		doShow(showTitle, TITLE);

		this.addActionListener(e -> {
			final JFileChooser chooser = new JFileChooser(System
					.getProperty("user.home"));

			chooser.setVisible(true);
			final int val = chooser.showSaveDialog(SaveSpace.this);
			if (val == JFileChooser.APPROVE_OPTION) {

				// save the file
				//controller.savePattern();

			} else if (val != JFileChooser.CANCEL_OPTION) {
				showErrorDialog(SaveSpace.this, "An Error has occurred");
			}
		});
	}
}
