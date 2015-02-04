package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RecorderPauseSpace extends JPanel {

	private static final long serialVersionUID = -8958765355776362631L;

	public RecorderPauseSpace(final JButton pauseButton) {
		this.setBackground(new Color(255, 255, 255));
		pauseButton.setBackground(new Color(255, 255, 255));
		// this.add(new JLabel(), BorderLayout.NORTH);
		pauseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (pauseButton.getText().equals("Pause")) {
					pauseButton.setText("Start");
					// pause the recording
				} else {
					pauseButton.setText("Pause");
					// restart the recording
				}

			}
		});
		pauseButton.setEnabled(false);
		this.add(pauseButton, BorderLayout.CENTER);
		
	}
}
