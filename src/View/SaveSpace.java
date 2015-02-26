package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
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
public class SaveSpace extends PersonalJButton {

	private static final long serialVersionUID = 2740908838399780756L;
	private static final String LABEL= "Save";
	
	public SaveSpace(final boolean showLabel) {
		
		super(PersonalJButton.SAVE_IMG);
		
		if(showLabel){
			super.showTitle(LABEL);
		}
		
		this.addActionListener(new SaveListener(this));
	}
	
	public static class SaveListener implements ActionListener{

		private PersonalJButton save;
		
		public SaveListener(final PersonalJButton save) {
			this.save= save;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser(System
					.getProperty("user.home"));
			
			chooser.setVisible(true);
			int val = chooser.showSaveDialog(save);
			if (val == JFileChooser.APPROVE_OPTION) {
				
				// save the file
				
			} else if (val != JFileChooser.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(save,
						"An Error has occurred", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
}
