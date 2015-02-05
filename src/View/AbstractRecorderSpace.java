package View;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AbstractRecorderSpace extends JPanel {

	private static final long serialVersionUID = 4199364808400772243L;
	
	private final JLabel label= new JLabel();
	private final JButton button = new JButton();
	
	protected AbstractRecorderSpace() {
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.NORTH);
		this.setBackground(new Color(255, 255, 255));
		button.setBackground(new Color(255, 255, 255));
		button.setForeground(new Color(255, 0, 0));
		this.add(button, BorderLayout.CENTER);
	}
	
	protected JButton getButton(){
		return this.button;
	}
	
	protected JLabel getLabel(){
		return label;
	}
}
