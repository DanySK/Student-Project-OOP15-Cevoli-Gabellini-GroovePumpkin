package view.unused;

import java.lang.reflect.InvocationTargetException;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * A simple Counter class modified to count the time of the played object
 * 
 * @author Alessandro
 *
 */
public final class TimeCounter extends Thread {

	private static TimeCounter SINGLETON= new TimeCounter();
	
	private boolean stop;
	private boolean pause;
	private int sec;
	private JLabel timeLabel;

	private boolean init;

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
	
	public static TimeCounter getSINGLETON(){
		return SINGLETON;
	}
	
	public static void resetSINGLETON(){
		SINGLETON= new TimeCounter();
	}
	
	public void attachLabel(final JLabel label){
		this.timeLabel = label;
		this.init=true;
	}
	
	public boolean isInit(){
		return this.init;
	}
	
	public boolean isPaused(){
		return pause;
	}
	
	public void pause(final boolean b) {
		pause = b;
		if(pause){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			this.notify();
		}
	}
	
	public void stopTime() {
		stop=true;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				TimeCounter.this.timeLabel.setText("00:00:00");
			}
		});
		this.interrupt();
	}

	@Override
	public void run() {
		while (!this.stop) {
				try {
					SwingUtilities.invokeAndWait(new Runnable() {
						@Override
						public void run() {
							timeLabel.setText(digitalize());
						}
					});
				} catch (InvocationTargetException | InterruptedException e) {
					//e.printStackTrace();
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
