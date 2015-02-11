package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 * 
 * @author Alessandro
 *
 */
@SuppressWarnings("unused")
public class SaveSpace extends AbstractCompositeJSpace {

	private static final long serialVersionUID = 2740908838399780756L;
	
	
	public SaveSpace() {
		super("Save Recording", PersonalJButton.SAVE_IMG);

		super.setButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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

			}
		});
	}
}
