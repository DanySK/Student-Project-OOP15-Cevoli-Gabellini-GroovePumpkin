package view.unused;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import model.PlayerState;
import view.panels.PersonalJPanel;
import controller.Updatable;

/**
 * 
 * @author Alessandro
 *
 */
public class Recorder extends JFrame implements Updatable{

	private static final long serialVersionUID = -4303337519550483522L;

	private final JLabel timerLabel = new JLabel("00:00:00");
	private final List<Updatable> buttons= new ArrayList<>();

	public Recorder(final JMenuBar jbm) {
		this.setTitle("Audio Recorder");
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 5);
		this.setResizable(false);
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 4,
				Toolkit.getDefaultToolkit().getScreenSize().height / 4);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		final PersonalJPanel mainPanel = new PersonalJPanel(new FlowLayout());
		//final JButton rps = createButton(PAUSE_BUTTON, true, null);
		//rps.setEnabled(false);
		
		//mainPanel.add(ButtonFactory.createButton(ButtonFactory.REC_BUTTON, true, rps));
		//mainPanel.add(rps);
		mainPanel.add(timerLabel);

		final PersonalJPanel southPanel = new PersonalJPanel(new FlowLayout());
	
		//southPanel.add(createButton(SAVE_BUTTON, true, null));

		this.add(southPanel, BorderLayout.SOUTH);
		this.add(mainPanel, BorderLayout.CENTER);

		this.setVisible(false);
		this.pack();
	}

	public JLabel getTimerLabel() {
		return this.timerLabel;
	}

	@Override
	public void updateStatus(PlayerState status) {
		for(Updatable u : buttons){
			u.updateStatus(status);
		}
	}
}
