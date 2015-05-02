package view.unused;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.buttons.PersonalJButton;

/**
 * This abstract class rapresent a button that needs to communicate with
 * a timer thread that update the groovebox
 * 
 * @author Alessandro
 *
 */
public abstract class TJB<C> extends PersonalJButton<C> {

	private static final long serialVersionUID = 4880819512343834501L;
	
	private TimeCounter timer;
	private JLabel label;
	
	protected TJB() {
		super();
	}
	
	protected TJB(final ImageIcon img){
		super(img);
	}
	
	protected TJB(final ImageIcon img, final String title){
		super(img, title);
	}
	
	private void resetTimer() {
		TimeCounter.resetSINGLETON();
		timer= TimeCounter.getSINGLETON();
		timer.attachLabel(label);
	}
	
	public void initTimer(final JLabel label){
		this.label=label;
		timer= TimeCounter.getSINGLETON();
		if(!timer.isInit()){
			timer.attachLabel(label);
		}
	}
	
	public void stopTimer(){
		timer.stopTime();
		resetTimer();
	}

	public void runTimer(){
		timer.start();
		//timer.run();
	}
	
	public void pauseTimer(final boolean b){
		timer.pause(b);
	}
	
	public boolean isTimerPaused(){
		return timer.isPaused();
	}
	
	public boolean isTimerPresent(){
		return timer==null? false : true;
	}
}
