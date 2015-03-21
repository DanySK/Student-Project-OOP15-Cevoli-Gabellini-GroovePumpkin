package controller;

import java.lang.reflect.InvocationTargetException;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Alessandro
 *
 */
public class TimeCounter extends Thread {
	private volatile boolean stop;
	private int sec;
	private final JLabel timeLabel;

	public TimeCounter(final JLabel timeLabel) {
		this.timeLabel= timeLabel;
	}
	
	private String digitalize(){
		
		String s="";
		
		if((sec%3600)<10){
			s= String.join("", s, "0", String.valueOf(sec/3600));
		} else {
			s= String.join("", s, String.valueOf(sec/3600));
		}
		
		if((sec%60)<10){
			s= String.join("", s, ":", "0", String.valueOf(sec/60));
		} else{
			s= String.join("", s, ":", String.valueOf(sec/60));
		}
		
		if(sec<10){
			s= String.join("", s, ":", "0", String.valueOf(sec%60));
		} else{
			s= String.join("", s, ":", String.valueOf(sec%60));
		}
		
		return s;
		
	}

	public void stopAndReset() {
		pause();
		sec=0;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				TimeCounter.this.timeLabel.setText("00:00:00");
			}
		});
	}
	
	public void pause(){
		stop=true;
	}

	@Override
	public void run() {
		while (!this.stop) {

			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					@Override
					public void run() {
						TimeCounter.this.timeLabel.setText(TimeCounter.this.digitalize());
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
