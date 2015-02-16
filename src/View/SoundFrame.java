package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * 
 * @author Alessandro
 *
 */
@SuppressWarnings("unused")
public class SoundFrame extends JFrame {

	private static final long serialVersionUID = 8764967532381350730L;
	
	/**
	 * This method populates the class
	 */
	public SoundFrame() {

		this.setMinimumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2));
		//this.setResizable(false);
		//I was looking for a method of JFrame to get the frame always proportional to the screen, but I haven't found it
		
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 4,
				Toolkit.getDefaultToolkit().getScreenSize().height / 4);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Groove&Pumpkin");
		this.setLayout(new BorderLayout());
		
		final SoundTab tabbedPane = new SoundTab(JTabbedPane.TOP);
		final SoundMenu menuBar = new SoundMenu();
		final PersonalJPanel mainPanel = new PersonalJPanel(new BorderLayout());
		mainPanel.add(menuBar, BorderLayout.NORTH);
		mainPanel.add(tabbedPane, BorderLayout.CENTER);
		
		this.setContentPane(mainPanel);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new SoundFrame();
	}
}