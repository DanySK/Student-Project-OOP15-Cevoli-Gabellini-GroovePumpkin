package view.unused;

import static model.PlayerState.PAUSED;
import static model.PlayerState.RUNNING;
import static model.PlayerState.SONGCHANGED;
import static model.PlayerState.STOPPED;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import model.PlayerState;
import view.config.Configuration;
import controller.musicplayer.MusicPlayer;
import controller.Updatable;

/**
 * This class rapresent a controller communicates with a timer
 * and a slider which values increment while a song is running,
 * they stop if the song is paused, and they reset if the song is stopped,
 * or changed.
 * 
 * It implements the SINGLETON pattern.
 * 
 * @author Alessandro
 *
 */
public class TimerController implements Updatable {

	private JLabel timeLabel = new JLabel("00:00:00");
	private JProgressBar timeBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
	private TimeCounter timer = new TimeCounter(timeLabel, 0);
	//private Thread t;
	private MusicPlayer controller;

	public TimerController(final MusicPlayer controller) {
		this.controller = controller;
		this.controller.addUpdatableObservers(this);
		timeBar.addChangeListener(e-> {
				final Double d= new Double (controller.getCurrentSongInfosManager().get().getElapsedTime());
				((JProgressBar)e.getSource())
					.setValue(d.intValue());
		});
		
		timeBar.setBackground(Configuration.GRAY);
		timeBar.setForeground(Configuration.ORANGE);
		timeBar.setStringPainted(true);
	}

	public void runTimer() {
		// System.out.println("lalalalalalala");
		if (timer.isAlive()) {
			this.pauseTimer(false);
		} else {
			try {
				timer.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void pauseTimer(final boolean b) {
		timer.pause(b);
	}

	public void stopTimer() {
		timer.stopTime();
		//resetTimer();
	}

	public boolean isTimerPaused() {
		return timer.isPaused();
	}

	public JLabel getLabel() {
		return this.timeLabel;
	}

	public JProgressBar getProgressbar() {
		return this.timeBar;
	}

	@Override
	public void updateStatus(PlayerState status) {
		if (status.equals(RUNNING)) {
			timeBar.setEnabled(true);
			//this.runTimer();
		} else if (status.equals(PAUSED)) {
			
			//this.pauseTimer(true);
		} else if (status.equals(STOPPED)) {
			
			timeBar.setValue(0);
			//this.stopTimer();
		} else if (status.equals(SONGCHANGED)) {
			timeBar.setValue(0);
			//this.stopTimer();
			//this.runTimer();
		}

	}
}
