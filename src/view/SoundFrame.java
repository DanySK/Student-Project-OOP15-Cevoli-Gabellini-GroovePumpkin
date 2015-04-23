package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import controller.GrooveBoxPlayer;
import controller.MusicPlayer;


/**
 * 
 * @author Alessandro
 *
 */
public class SoundFrame extends JFrame {

	private static final long serialVersionUID = 8764967532381350730L;
	private SoundTab tabbedPane;
	private SoundMenu menuBar;
	
	/**
	 * Default constructor for the frame
	 * -Centered,
	 * -Autoresize,
	 * -BorderLayout
	 * -Close on exit
	 * 
	 */
	public SoundFrame(final MusicPlayer mp, final GrooveBoxPlayer groove) {

		this.setMinimumSize(new Dimension(Toolkit.getDefaultToolkit()
				.getScreenSize().width * 10 / 15, Toolkit.getDefaultToolkit()
				.getScreenSize().height * 10 / 15));
		// I was looking for a method of JFrame to get the frame always
		// proportional to the screen, but I haven't found it

		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 6,
				Toolkit.getDefaultToolkit().getScreenSize().height / 6);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Groove&Pumpkins");
		this.setLayout(new BorderLayout());

		final PersonalJPanel mainPanel = new PersonalJPanel(new BorderLayout());
		tabbedPane = new SoundTab(mp, groove);
		menuBar = new SoundMenu();
		mainPanel.add(menuBar, BorderLayout.NORTH);
		mainPanel.add(tabbedPane, BorderLayout.CENTER);

		this.setContentPane(mainPanel);
		this.setVisible(true);
		this.pack();
	}
	
	/**
	 * 
	 * @return The menu attached to this frame
	 */
	public SoundMenu getMenu(){
		return this.menuBar;
	}
	
	/**
	 * 
	 * @return The TabbedPane attached to this frame
	 */
	public SoundTab getSoundTab(){
		return this.tabbedPane;
	}
	
	/**
	 * Set a new Menu for this frame
	 * 
	 * @param menu
	 */
	public void setMenuBar(final SoundMenu menu){
		this.getContentPane().remove(menuBar);
		this.menuBar=menu;
		this.getContentPane().add(menuBar, BorderLayout.NORTH);
	}
	
	/**
	 * Set a new Tabbed Pane for this frame
	 * 
	 * @param tab
	 */
	public void setSoundTab(final SoundTab tab){
		this.getContentPane().remove(tabbedPane);
		this.tabbedPane=tab;
		this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}
}