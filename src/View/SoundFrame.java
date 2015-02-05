package View;

import java.awt.BorderLayout;
import java.awt.Color;
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
	private final ImageIcon background= new ImageIcon("data"+ System.getProperty("file.separator") + "BG.jpg");
	private final JLabel bg= new JLabel(background);
	
	/**
	 * This method populates the whole class
	 */
	public SoundFrame() {

		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Groove&Pumpkin");
		this.setLayout(new BorderLayout());
		
		
		final SoundTab tabbedPane = new SoundTab(JTabbedPane.TOP);
		final SoundMenu menuBar = new SoundMenu();
		final JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setOpaque(false);
		mainPanel.setBackground(new Color(255, 255, 255));
		mainPanel.add(menuBar, BorderLayout.NORTH);
		mainPanel.add(tabbedPane, BorderLayout.CENTER);
		
		this.setContentPane(mainPanel);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new SoundFrame();
	}
}