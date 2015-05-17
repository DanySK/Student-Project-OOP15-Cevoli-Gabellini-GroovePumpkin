package view.controller.time;

import java.lang.reflect.InvocationTargetException;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * A simple Counter class modified to count the time of the played object
 * 
 * @author Alessandro
 *
 */
public class TimeCounter extends Thread {

	private boolean stop;
	private int sec;

	private JLabel timeLabel;
	//private JProgressBar timeBar;

	public TimeCounter(final JLabel label, final int elapsedTime ) {
		this.timeLabel = label;
		this.sec= elapsedTime;
	}

	private String digitalize() {

		String s = "";
		
		if (sec / 3600 < 10) {
			s = String.join("", s, "0", String.valueOf(sec / 3600));
		} else {
			s = String.join("", s, String.valueOf(sec / 3600));
		}

		if ((sec%3600)/60 < 10) {
			s = String.join("", s, ":", "0", String.valueOf((sec%3600)/60));
		} else {
			s = String.join("", s, ":", String.valueOf((sec%3600)/60));
		}

		if (sec % 60 < 10) {
			s = String.join("", s, ":", "0", String.valueOf(sec % 60));
		} else {
			s = String.join("", s, ":", String.valueOf(sec % 60));
		}
		
		return s;

	}
	
	public int getElapsedTime(){
		return sec;
	}
	
	public void pauseTime(){
		stop=true;
	}
	
	public void stopTime() {
		stop = true;
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				timeLabel.setText("00:00:00");
			}
		});
	}

	@Override
	public void run() {
		while (!this.stop) {
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					@Override
					public void run() {
						timeLabel.setText(digitalize());
						timeLabel.repaint();
					}
				});
			} catch (InvocationTargetException 
					| InterruptedException e) {
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
