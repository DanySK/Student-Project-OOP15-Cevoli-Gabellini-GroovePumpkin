package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * A simple Counter class modified to count the time of the played object
 * 
 * @author Alessandro
 *
 */
public final class TimeCounter extends Thread {

	private static final TimeCounter TIMERCOUNTER = new TimeCounter();

	private volatile boolean stop;
	private int sec;
	private Optional<JLabel> timeLabel = Optional.empty();

	private boolean pause=false;

	private TimeCounter() {
	}

	private String digitalize() {

		String s = "";

		if ((sec % 3600) < 10) {
			s = String.join("", s, "0", String.valueOf(sec / 3600));
		} else {
			s = String.join("", s, String.valueOf(sec / 3600));
		}

		if ((sec % 60) < 10) {
			s = String.join("", s, ":", "0", String.valueOf(sec / 60));
		} else {
			s = String.join("", s, ":", String.valueOf(sec / 60));
		}

		if (sec < 10) {
			s = String.join("", s, ":", "0", String.valueOf(sec % 60));
		} else {
			s = String.join("", s, ":", String.valueOf(sec % 60));
		}

		return s;

	}

	public static TimeCounter getSingleton() {
		return TimeCounter.TIMERCOUNTER;
	}

	public void attachTimeLabel(final JLabel label) {
		this.timeLabel = Optional.ofNullable(label);
	}

	public void stopAndReset() {
		pause();
		stop=true;
		sec = 0;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				TimeCounter.this.timeLabel.get().setText("00:00:00");
			}
		});
	}

	public void pause() {
		pause = true;
	}

	@Override
	public void run() {
		stop = false;
		while (!this.stop) {
			while (!this.pause) {

				try {
					SwingUtilities.invokeAndWait(new Runnable() {
						@Override
						public void run() {
							TimeCounter.this.timeLabel.get().setText(
									TimeCounter.this.digitalize());
						}
					});
				} catch (InvocationTargetException | InterruptedException e) {
					e.printStackTrace();
				}

				this.sec++;

				try {
					Thread.sleep(1000l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
