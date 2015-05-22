package view.controller.time;

import static model.PlayerState.*;

import javax.swing.JLabel;

import view.config.Utility;
import view.controller.time.TimeCounter.Strategy;
import model.PlayerState;
import controller.musicplayer.MusicPlayer;
import controller.Updatable;

/**
 * This class rapresent a controller that communicates with a timer
 * which value increments while a song is running. 
 * This controller intercept the notify sent by the music player 
 * controller to its listener and manages the timer consequently 
 * 
 * 
 * @author Alessandro
 *
 */
public class TimerController implements Updatable {

	private JLabel timeLabel = new JLabel("00:00:00");
	private TimeCounter timer = new TimeCounter(timeLabel, 0);
	private MusicPlayer controller;

	public TimerController(final MusicPlayer controller) {
		this.controller = controller;
		this.controller.addUpdatableObservers(this);
	}

	public synchronized void runTimer() {
		if (!timer.isAlive()) {
			try {
				timer.start();
			} catch (Exception e) {
				Utility.showErrorDialog(timeLabel, "Errore durante l'avvio del timer");
			}
		}
	}

	public synchronized void pauseTimer() {
		timer.manipulateTime(Strategy.PAUSE);
		timer = new TimeCounter(timeLabel, timer.getElapsedTime());
	}

	public synchronized void stopTimer() {
		timer.manipulateTime(Strategy.STOP);
		timer = new TimeCounter(timeLabel, 0);
	}

	public JLabel getLabel() {
		return this.timeLabel;
	}

	@Override
	public void updateStatus(PlayerState status) {
		if (status.equals(RUNNING)) {
			this.runTimer();
		} else if (status.equals(PAUSED)) {
			this.pauseTimer();
		} else if (status.equals(STOPPED) || status.equals(REMOVED) 
				|| status.equals(SONGCHANGED)) {
			this.stopTimer();
		}
	}
}
