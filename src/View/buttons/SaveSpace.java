package View.buttons;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * 
 * @author Alessandro
 *
 */
public class SaveSpace extends PersonalJButton {

	private static final long serialVersionUID = 2740908838399780756L;
	private static final String LABEL = "Save";

	public SaveSpace(final boolean showLabel) {

		super(PersonalJButton.SAVE_IMG);

		if (showLabel) {
			super.showTitle(LABEL);
		}

		this.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser(System
					.getProperty("user.home"));

			chooser.setVisible(true);
			int val = chooser.showSaveDialog(SaveSpace.this);
			if (val == JFileChooser.APPROVE_OPTION) {

				// save the file

			} else if (val != JFileChooser.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(SaveSpace.this,
						"An Error has occurred", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			}
		});
	}
}
