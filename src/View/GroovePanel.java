package View;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("unused")
public class GroovePanel extends JPanel {

	private static final long serialVersionUID = 1116768170189928089L;
	private final ImageIcon background= new ImageIcon("data"+ System.getProperty("file.separator") + "BG.jpg");
	private final JLabel bg= new JLabel(background);
	
	public GroovePanel() {
		this.setLayout(new BorderLayout());
		this.add(bg);
		bg.setLayout(new BorderLayout());
	}
}
