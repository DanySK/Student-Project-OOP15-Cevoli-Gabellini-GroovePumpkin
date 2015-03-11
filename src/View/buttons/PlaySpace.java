package View.buttons;

import javax.swing.ImageIcon;

import static Model.Utility.*;
import controller.MusicPlayer;
import Model.PlayerState;
import View.Updatable;

/**
 * This class extends the abstract composite (Label+Button) space to build a
 * play and pause system for this software
 * 
 * @author Alessandro
 *
 */
public class PlaySpace extends PersonalJButton implements Updatable{

	private static final long serialVersionUID = -8958765355776362631L;

	public static final String PLAY = "Play";
	public static final String PAUSE = "Pause";

	/**
	 * 
	 * @param layout
	 *            to be attached
	 * @param buttonEnabled
	 *            if the button have to be enabled or not
	 */
	protected PlaySpace(final MusicPlayer controller, final ImageIcon img, final boolean showTitle) {

		super(img);
		super.setController(controller);

		if (img != null && this.getIcon().equals(PLAY_IMG)) {
			this.setID(PLAY);
		} else {
			this.setID(PAUSE);
		}

		if (showTitle) {
			this.setTitle(this.getID());
		}

		this.addActionListener(e -> {
			if(this.getID().equals(PLAY)){
				try{
					controller.play();
				} catch(Exception ex){
					//Open ErrorLoadingPane
				}
			} else{
				try{
					controller.pause();
				} catch(Exception ex){
					
				}
			}
		});
	}

	@Override
	public void updateStatus(final PlayerState status) {
		
		if (status.equals(PlayerState.RUNNING)) {
			this.setID(PLAY);
			if (this.getTitledBorder() != null) {
				this.getTitledBorder().setTitle(PAUSE);
			}
			this.setIcon(PAUSE_IMG);
			// start
		} else {
			this.setID(PAUSE);
			if (this.getTitledBorder() != null) {
				this.getTitledBorder().setTitle(PLAY);
			}
			PlaySpace.this.setIcon(PLAY_IMG);
			// pause
		}
	}
}
