package view.controller.time;

import java.lang.reflect.InvocationTargetException;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import view.config.Utility;

/**
 * A simple Counter class modified to count the time of the played object
 * 
 * @author Alessandro
 *
 */
public class TimeCounter extends Thread {
	
	private static final int TEN= 10;
	private boolean stop;
	private int sec;
	private JLabel timeLabel;
	private int h;
	private int m;
	private int s;
	
	/**
	 * Constructor for the time counter
	 * It gets in inupt a JLabel on which change the time text written
	 * and the time from which starting counting
	 * 
	 * @param label
	 * @param elapsedTime
	 */
	public TimeCounter(final JLabel label, final int elapsedTime ) {
		super();
		this.timeLabel = label;
		this.sec= elapsedTime;
	}

	private String digitalize() {
		String str = "";
		h= sec / 3600;
		m=(sec % 3600)/60;
		s= sec % 60;
		str= String.join("", str, h < TEN ? "0" : "", String.valueOf(h));
		str= String.join("", str, ":", m < TEN ? "0" : "", String.valueOf(m));
		str= String.join("", str, ":", s < TEN ? "0" : "", String.valueOf(s));
		return str;
	}
	
	/**
	 * 
	 * @return Return the elapsed time by the start of the timer
	 */
	public int getElapsedTime(){
		return sec;
	}
	
	public void pauseTime(){
		this.stop= true;
	}
	/**
	 * Manipulate time with the given strategy
	 */
	public void stopTime() {
		this.pauseTime();
		SwingUtilities.invokeLater(()-> {
			this.timeLabel.setText("00:00:00");
			this.timeLabel.repaint();
		});
	}

	@Override
	public void run() {
		while (!this.stop) {
			try {
				SwingUtilities.invokeAndWait(()-> {
						timeLabel.setText(digitalize());
						timeLabel.repaint();
				});
			} catch (InvocationTargetException 
					| InterruptedException e) {
				Utility.showErrorDialog(timeLabel, e.getMessage());
			}

			this.sec++;

			try {
				Thread.sleep(1000l);
			} catch (InterruptedException e) {
				Utility.showErrorDialog(timeLabel, e.getMessage());
			}
		}
	}
}
