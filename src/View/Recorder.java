package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuBar;

@SuppressWarnings("unused")
public class Recorder extends JFrame {

	private static final long serialVersionUID = -4303337519550483522L;

	private final JLabel timerLabel = new JLabel("00:00:00");

	public Recorder(final JMenuBar jbm) {
		this.setTitle("Audio Recorder");
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 4,
				Toolkit.getDefaultToolkit().getScreenSize().height / 5);
		this.setResizable(false);
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 4,
				Toolkit.getDefaultToolkit().getScreenSize().height / 4);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		final PersonalJPanel mainPanel = new PersonalJPanel(new FlowLayout());

		final PlayAndPauseSpace rps = new PlayAndPauseSpace("Pause", PersonalJButton.PAUSE_IMG, false);
		mainPanel.add(new RecSpace(rps));
		mainPanel.add(rps);
		mainPanel.add(timerLabel);

		final PersonalJPanel southPanel = new PersonalJPanel(new FlowLayout());
		southPanel.add(new SaveSpace());
		
		this.add(southPanel, BorderLayout.SOUTH);
		this.add(mainPanel, BorderLayout.CENTER);

		this.setVisible(false);
	}

	public JLabel getTimerLabel() {
		return this.timerLabel;
	}
}
