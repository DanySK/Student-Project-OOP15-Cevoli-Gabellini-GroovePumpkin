package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
	private Recorder rec;
	
	public SoundMenu() {
		
		this.setBackground(new Color(255, 255, 255));
		final JMenu option = new JMenu("Options");
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
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
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

		final JMenu view = new JMenu("View");
		this.add(view);

		final JMenuItem soundRec = new JMenuItem("Show/Hide Sound Recorder");
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

	}
}
