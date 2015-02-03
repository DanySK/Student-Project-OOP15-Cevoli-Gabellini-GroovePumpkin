package View;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
		this.setLayout(new BorderLayout());
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		final JMenuBar menuBar = new JMenuBar();
		final JPanel mainPanel = new JPanel(new BorderLayout());

		menuCreator(menuBar);
		tabCreator(tabbedPane);

		mainPanel.add(menuBar, BorderLayout.NORTH);
		mainPanel.add(tabbedPane, BorderLayout.CENTER);
		this.setContentPane(mainPanel);
		this.setVisible(true);
	}

	/**
	 * This methos populates the JTabbedPane created as a field of this class
	 * @param jtp
	 */
	private void tabCreator(final JTabbedPane jtp) {
		jtp.add("Play Music", new PlaybackPanel());
		jtp.add("Groove Box", new GroovePanel());
	}

	/**
	 * This method populates the JMenùBar created as a field of this class
	 * 1) Option-> show various options avaiable, like the infos about this application
	 * 2) Open Sound Recorder-> Allows the user to open, in a different window the audio recorder device
	  
	 * @param jbm, the Java Menu Bar to be populated
	 */
	private void menuCreator(final JMenuBar jbm){
		
		
		final JMenu option= new JMenu("Options");
		jbm.add(option);
		/*
		final JMenuItem config=new JMenuItem("Configuration");
		
		config.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		option.add(config);
		*/
		
		final JMenuItem about= new JMenuItem("About");
		about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		option.add(about);
		
		final JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		option.add(exit);
		
		final JMenu soundRec= new JMenu("Open Sound Recorder");
		soundRec.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 new Recorder();
			}
		});
		jbm.add(soundRec);
		
	}

	public static void main(String[] args) {
		new SoundFrame();
	}
}
