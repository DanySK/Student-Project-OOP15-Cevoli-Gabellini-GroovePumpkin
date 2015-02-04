package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RecorderRecSpace extends JPanel {

	private static final long serialVersionUID = -2653526539862883988L;
	private final JButton recButton = new JButton();
	private JButton pauseButton;
	private JLabel label= new JLabel("Rec");
	
	public RecorderRecSpace(final JButton pause) {
		this.pauseButton= pause;
		
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.NORTH);
		this.setBackground(new Color(255, 255, 255));
		
		recButton.setBackground(new Color(255, 255, 255));
		recButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pauseButton.setEnabled(true);

				if (label.getText().equals("Rec")) {
					label.setText("Stop");
					// start recording the sound
				} else {
					label.setText("Rec");
					pauseButton.setEnabled(false);
					// stop recording
				}

			}
		});
		
		this.add(recButton, BorderLayout.CENTER);
	}
	
	public boolean isRecording(){
		return label.getText().equals("Stop");
	}
}
