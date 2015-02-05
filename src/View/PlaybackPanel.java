package View;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("unused")
public class PlaybackPanel extends JPanel {

	private static final long serialVersionUID = -2515046442887709479L;
	private final ImageIcon background= new ImageIcon("data"+ System.getProperty("file.separator") + "BG.jpg");
	private final JLabel bg= new JLabel(background);
	
	public PlaybackPanel() {
		this.setLayout(new BorderLayout());
		this.add(bg);
		bg.setLayout(new BorderLayout());
		
	}
}
