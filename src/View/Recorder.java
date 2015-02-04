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
				Toolkit.getDefaultToolkit().getScreenSize().height / 8);

		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		final JPanel mainPanel = new JPanel(new FlowLayout());
		mainPanel.setBackground(new Color(255, 255, 255));
		
		final RecorderPauseSpace rps= new RecorderPauseSpace();
		mainPanel.add(new RecorderRecSpace(rps.getPause()));
		mainPanel.add(rps);
		mainPanel.add(timerLabel);
		this.setContentPane(mainPanel);
		
		this.setResizable(false);
		this.setVisible(false);
	}

	public JLabel getTimeLabel() {
		return this.timerLabel;
	}
}
