package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("unused")
public class RecorderPauseSpace extends JPanel {

	private static final long serialVersionUID = -8958765355776362631L;
	private static final ImageIcon img= new ImageIcon("data"+ System.getProperty("file.separator") + "1423071159_47954.ico");
	
	private final JButton pauseButton = new JButton();
	private final JLabel label= new JLabel("Pause");
	public RecorderPauseSpace() {
		
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.NORTH);
		this.setBackground(new Color(255, 255, 255));
		
		//pauseButton.setIcon(img);
		pauseButton.setBackground(new Color(255, 255, 255));
		pauseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (label.getText().equals("Pause")) {
					label.setText("Start");
					// pause the recording
				} else {
					label.setText("Pause");
					// restart the recording
				}

			}
		});
		pauseButton.setEnabled(false);
		this.add(pauseButton, BorderLayout.CENTER);
		
	}
	
	protected JButton getPause(){
		return this.pauseButton;
	}
	
	public boolean isPaused(){
		return label.getText().equals("Start");
	}
}
