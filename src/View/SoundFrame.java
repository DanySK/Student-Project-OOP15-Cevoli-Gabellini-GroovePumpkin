package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
	 * This method populates the whole class
	 */
	public SoundFrame() {

		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Groove&Pumpkin");

		final SoundTab tabbedPane = new SoundTab(JTabbedPane.TOP);
		final SoundMenu menuBar = new SoundMenu();
		final JPanel mainPanel = new JPanel(new BorderLayout());

		mainPanel.setBackground(new Color(255, 255, 255));
		//menuBar.setOpaque(true);

		mainPanel.add(menuBar, BorderLayout.NORTH);
		mainPanel.add(tabbedPane, BorderLayout.CENTER);
		this.setContentPane(mainPanel);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new SoundFrame();
	}
}