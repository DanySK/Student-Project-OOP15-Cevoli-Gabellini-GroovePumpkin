package View.buttons;

import static Model.Utility.*;
import controller.MusicPlayer;

/**
 * 
 * @author Alessandro
 *
 */
public class RemoveSpace extends PersonalJButton {

	private static final long serialVersionUID = -768335930316060117L;
	private static final String TITLE= "Remove";
	private int selectedIndexes[]={-1};
	
	
	protected RemoveSpace(final MusicPlayer controller, final boolean showTitle) {
		super(REMOVE_IMG);
		super.setController(controller);
		
		doShow(showTitle, TITLE);
		
		this.addActionListener(e->{
			// rimuovi una canzone
			try {
				for(int i : selectedIndexes){
					controller.removeSong(i);
				}
				selectedIndexes= new int[]{-1};
			} catch (IllegalArgumentException ex) {
				showErrorDialog(RemoveSpace.this, "Invalid object selected: " + selectedIndexes);
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
}
