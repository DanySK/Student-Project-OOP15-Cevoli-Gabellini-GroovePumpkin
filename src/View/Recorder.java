package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class Recorder extends JFrame {

	private static final long serialVersionUID = -4303337519550483522L;
	
	public Recorder(final JMenuBar jbm) {
		this.setBackground(new Color(255, 255, 255));
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 4,
				Toolkit.getDefaultToolkit().getScreenSize().height / 8);

		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setName("Audio Recorder");
		
		this.setResizable(false);
		this.setVisible(false);
	}
}
