package view.unused;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

/**
 * A simple Counter class modified to count the time of the played object
 * 
 * @author Alessandro
 *
 */
@SuppressWarnings("unused")
public class TimeCounter extends Thread {

	private boolean stop;
	private boolean paused;
	private int sec;
	private int dur;

	private JLabel timeLabel;
	//private JProgressBar timeBar;

	public TimeCounter(final JLabel label, final int dur) {
		this.timeLabel = label;
		this.dur = dur;
	}

	private String digitalize() {

		String s = "";

		if ((sec / 3600) < 10) {
			s = String.join("", s, "0", String.valueOf(sec / 3600));
		} else {
			s = String.join("", s, String.valueOf(sec / 3600));
		}

		if ((sec / 60) < 10) {
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

	public boolean isPaused() {
		return paused;
	}

	public void pause(final boolean b) {
		paused = b;
		if (paused) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		} else {
			this.notifyAll();
		}
	}

	public void stopTime() {
		stop = true;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				timeLabel.setText("00:00:00");
				//timeBar.setValue(0);
			}
		});
		this.interrupt();
	}

	@Override
	public void run() {
		while (!this.stop) {
//			try {
//				paused=true;
//				this.wait();
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				//e1.printStackTrace();
//			}
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					@Override
					public void run() {
						timeLabel.setText(digitalize());
						//timeBar.setValue(sec*dur/timeBar.getMaximum());
						timeLabel.repaint();
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
