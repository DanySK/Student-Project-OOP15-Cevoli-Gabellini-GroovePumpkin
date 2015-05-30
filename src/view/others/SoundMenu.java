package view.others;

import static view.config.Configuration.DARK_GRAY;
import static view.config.Configuration.WHITE;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import view.config.Configuration;
import view.config.Utility;

/**
 * This class creates an already populated JMenuBar for SoundFrame
 * 1)Option: show various options avaiable, 
 * 				like the infos about this application
 * 2)View: Allows the user to view external windows 
 * 				like the audio recorder device
 * 
 * @author Alessandro
 *
 */

public class SoundMenu extends JMenuBar {

	private static final long serialVersionUID = 840456297459950226L;
	private JMenu option;
	
	/**
	 * A Default Sound MenuBar
	 * 
	 */
	public SoundMenu() {
		
		this.setBackground(WHITE);
		this.setForeground(DARK_GRAY);
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
		about.setForeground(DARK_GRAY);
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					final BufferedReader buf= new BufferedReader(
							new InputStreamReader(SoundMenu.this.getClass()
									.getResourceAsStream(Configuration.LICENSE), "UTF8"));
					final JTextArea jta= new JTextArea();
					jta.read(buf, null);

					JOptionPane.showMessageDialog(null, new JScrollPane(jta), 
							"About Groove&Pumpkin", JOptionPane.INFORMATION_MESSAGE);
					buf.close();
				} catch (IOException e1) {
					Utility.showErrorDialog(null, "Error while loading the about of the program");
				}
			}
		});
		option.add(about);

		final JMenuItem exit = new JMenuItem("Exit");
		exit.setBackground(WHITE);
		exit.setForeground(DARK_GRAY);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		option.add(exit);
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
