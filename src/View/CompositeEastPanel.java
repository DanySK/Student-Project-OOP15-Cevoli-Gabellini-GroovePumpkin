package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Personalized JPanel for the PlayBackPanel
 * 
 * @author Alessandro
 *
 */
@SuppressWarnings("unused")
public class CompositeEastPanel extends JPanel {

	private static final long serialVersionUID = 4164776505153007930L;
	
	private static final Color GRAY= new Color(50, 50, 50);
	private static final Color WHITE= new Color(255, 255, 255);
	
	private final ImageIcon pauseImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Pause.png");
	private final ImageIcon playImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Play.png");
	private final ImageIcon fwImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "FW.png");
	private final ImageIcon rwImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "RW.png");

	public CompositeEastPanel() {
		this.setLayout(new BorderLayout());
		this.setBackground(WHITE);
		this.setForeground(GRAY);

		final JPanel north = new JPanel(new FlowLayout());
		north.setBackground(WHITE);
		north.setForeground(WHITE);
		final JLabel songName = new JLabel("< Nothing Else Matters >");
		songName.setBackground(WHITE);
		songName.setForeground(GRAY);
		songName.setOpaque(true);
		north.add(songName);
		
		this.add(north, BorderLayout.NORTH);
		
		final JPanel centre = new JPanel(new FlowLayout());
		populateCentralPanel(centre);
		
		this.add(centre, BorderLayout.CENTER);
	}

	private void populateCentralPanel(final JPanel centre) {
		centre.setBackground(WHITE);
		centre.setForeground(GRAY);
		
		final JButton play = new JButton(playImg);
		play.setBackground(WHITE);
		
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//play selected song
			}
		});
		final JButton fw = new JButton(fwImg);
		fw.setBackground(WHITE);
		fw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//go to the next song
			}
		});
		final JButton rw = new JButton(rwImg);
		rw.setBackground(WHITE);
		rw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//go back to the previous song
			}
		});
		
		centre.add(rw);
		centre.add(play);
		centre.add(fw);
	}
	
	
}
