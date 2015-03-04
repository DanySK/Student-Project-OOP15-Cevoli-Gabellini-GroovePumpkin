package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * 
 * @author Alessandro
 *
 */
public class SoundFrame extends JFrame {

	private static final long serialVersionUID = 8764967532381350730L;

	/**
	 * This method populates the class
	 */
	public SoundFrame() {

		this.setMinimumSize(new Dimension(Toolkit.getDefaultToolkit()
				.getScreenSize().width * 10 / 15, Toolkit.getDefaultToolkit()
				.getScreenSize().height * 10 / 15));
		// I was looking for a method of JFrame to get the frame always
		// proportional to the screen, but I haven't found it

		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 6,
				Toolkit.getDefaultToolkit().getScreenSize().height / 6);

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
		this.pack();
	}
}