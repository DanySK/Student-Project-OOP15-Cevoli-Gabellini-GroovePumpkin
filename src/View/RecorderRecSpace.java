package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RecorderRecSpace extends JPanel {

	private static final long serialVersionUID = -2653526539862883988L;

	public RecorderRecSpace(final JButton recButton, final JButton pauseButton) {
		this.setBackground(new Color(255, 255, 255));
		recButton.setBackground(new Color(255, 255, 255));
		// this.add(new JLabel(), BorderLayout.NORTH);
		recButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pauseButton.setEnabled(true);

				if (recButton.getText().equals("Rec")) {
					recButton.setText("Stop");
					// start recording the sound
				} else {
					recButton.setText("Rec");
					pauseButton.setEnabled(false);
					// stop recording
				}

			}
		});
		this.add(recButton, BorderLayout.CENTER);
	}
}
