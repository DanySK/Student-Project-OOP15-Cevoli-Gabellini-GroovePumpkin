package view.controller.time;

import static model.PlayerState.*;

import javax.swing.JLabel;

import view.config.Utility;
import view.interfaces.UpdatableObserver;
import model.PlayerState;
import controller.Observable;

/**
 * This class rapresents a controller that communicates with a timer
 * which value increments while a song is running. 
 * This controller intercepts the notifies sent by an Observable 
 * Object to its listener and manages the timer consequently.
 * 
 * @author Alessandro
 *
 */
public class TimerController implements UpdatableObserver {

	private final JLabel timeLabel = new JLabel("00:00:00");
	private TimeCounter timer = new TimeCounter(timeLabel, 0);
	private final Observable controller;
	
	/**
	 * The constructor for 
	 * 
	 * @param controller
	 */
	public TimerController(final Observable controller) {
		this.controller = controller;
		this.controller.addUpdatableObservers(this);
	}

	private void runTimer() {
		if (!timer.isAlive()) {
			try {
				timer.start();
			} catch (IllegalThreadStateException e) {
				Utility.showErrorDialog(timeLabel, "Errore durante l'avvio del timer");
			}
		}
	}

	private void pauseTimer() {
		timer.pauseTime();
		timer = new TimeCounter(timeLabel, timer.getElapsedTime());
	}

	private void stopTimer() {
		timer.stopTime();
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
