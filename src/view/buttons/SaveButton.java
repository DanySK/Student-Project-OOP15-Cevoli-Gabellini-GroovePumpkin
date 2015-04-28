package view.buttons;

import javax.swing.JFileChooser;

import controller.GrooveBoxPlayer;
import static view.config.Utility.*;

/**
 * 
 * @author Alessandro
 *
 */
public class SaveButton extends PersonalJButton {

	private static final long serialVersionUID = 2740908838399780756L;
	private static final String TITLE = "Save";

	protected SaveButton(final GrooveBoxPlayer controller, final boolean showTitle) {
		super(SAVE_IMG);
		super.setController(controller);
		doShow(showTitle, TITLE);

		this.addActionListener(e -> {
			final JFileChooser chooser = new JFileChooser(System
					.getProperty("user.home"));

			chooser.setVisible(true);
			final int val = chooser.showSaveDialog(SaveButton.this);
			if (val == JFileChooser.APPROVE_OPTION) {

				//save the file
				//controller.savePattern();

			} else if (val != JFileChooser.CANCEL_OPTION) {
				showErrorDialog(SaveButton.this, "An Error has occurred");
			}
		});
	}
}
