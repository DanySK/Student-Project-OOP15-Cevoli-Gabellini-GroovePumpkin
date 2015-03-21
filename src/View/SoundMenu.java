package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import static Model.Utility.*;

/**
 * This class creates an already populated JMenuBar for SoundFrame
 * 1) Option-> show various options avaiable, like the infos about this application
 * 2)View-> Allows the user to view external windows like the audio recorder device
 * 
 * @author Alessandro
 *
 */

@SuppressWarnings("unused")
public class SoundMenu extends JMenuBar {

	private static final long serialVersionUID = 840456297459950226L;
	private JMenu option;
	//private Recorder rec;
	
	/**
	 * A Default Sound MenuBar
	 * 
	 */
	public SoundMenu() {
		
		this.setBackground(WHITE);
		this.setForeground(GRAY);
		option = new JMenu("Options");
		option.setBackground(WHITE);
		this.add(option);
		
		/*
		 * final JMenuItem config=new JMenuItem("Configuration");
		 * 
		 * config.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { //TODO } });
		 * option.add(config);
		 */

		final JMenuItem about = new JMenuItem("About");
		about.setBackground(WHITE);
		about.setForeground(GRAY);
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Show Information about this Application
			}
		});
		option.add(about);

		final JMenuItem exit = new JMenuItem("Exit");
		exit.setBackground(WHITE);
		exit.setForeground(GRAY);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		option.add(exit);
		
		/*
		final JMenu view = new JMenu("View");
		view.setBackground(WHITE);
		this.add(view);
		final JMenuItem soundRec = new JMenuItem("Show/Hide Sound Recorder");
		soundRec.setBackground(WHITE);
		soundRec.setForeground(GRAY);
		soundRec.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rec == null) {
					rec = new Recorder(SoundMenu.this);
					rec.setVisible(true);
				} else if (rec != null && !rec.isVisible()) {
					rec.setVisible(true);
				} else {
					rec.setVisible(false);
				}

			}
		});
		view.add(soundRec);
		*/
	}
	
	/**
	 * If someone doesn't like the normal implementation of this 
	 * JMenu
	 */
	public void resetMenuBar(){
		this.removeAll();
	}
	
	/**
	 * 
	 * @return
	 */
	public Component getOptionMenu(){
		return this.option;
	}
}
