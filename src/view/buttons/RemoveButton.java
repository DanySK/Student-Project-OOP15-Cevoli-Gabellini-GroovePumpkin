package view.buttons;

import static view.config.Utility.*;
import static model.PlayerState.*;
import model.PlayerState;
import controller.MusicPlayer;

/**
 * 
 * @author Alessandro
 *
 */
public class RemoveButton extends TJB {

	private static final long serialVersionUID = -768335930316060117L;
	private static final String TITLE= "Remove";
	private int selectedIndexes[]={-1};
	
	
	protected RemoveButton(final MusicPlayer controller, final boolean showTitle) {
		super(REMOVE_IMG);
		super.setController(controller);
		
		doShow(showTitle, TITLE);
		
		this.addActionListener(e->{
			// rimuovi una canzone
			try {
				for(final int i : selectedIndexes){
					controller.removeSong(i);
				}
				selectedIndexes= new int[]{-1};
			} catch (IllegalArgumentException ex) {
				showErrorDialog(RemoveButton.this, "Invalid object selected!");
			}
		});
	}
	
	/**
	 * 
	 * @param idx selected from the PlaylistTable
	 */
	public void setSelectedIndex(final int ...idx){
		this.selectedIndexes=idx;
	}
	
	@Override
	public void updateStatus(final PlayerState status) {
		if(status.equals(REMOVED))
		if(this.isTimerPresent()){
			this.stopTimer();
		}
	}
}
