package View;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Recorder extends JFrame {

	private static final long serialVersionUID = -4303337519550483522L;
	
	public Recorder() {
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 4,
				Toolkit.getDefaultToolkit().getScreenSize().height / 8);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		this.setVisible(true);
	}
}
