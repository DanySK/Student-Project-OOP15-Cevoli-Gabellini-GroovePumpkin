package View.buttons;

import static Model.Utility.*;
import java.util.Optional;
import javax.swing.JTable;
import controller.MusicPlayer;

/**
 * 
 * @author Alessandro
 *
 */
public class RemoveSpace extends PersonalJButton {

	private static final long serialVersionUID = -768335930316060117L;
	private static final String TITLE= "Remove";
	private Optional<JTable> playlist= Optional.empty();
	
	protected RemoveSpace(final MusicPlayer controller, final boolean showTitle) {
		super(REMOVE_IMG);
		super.setController(controller);
		
		if(showTitle){
			this.setTitle(TITLE);
		}
		
		this.addActionListener(e->{
			// rimuovi una canzone
			try {
				controller.removeSong(playlist.get().getSelectedRow());
				//Devo aggironare io?
			} catch (Exception ex) {
				// do nothing
			}
		});
	}
	
	/**
	 * The Playlist to be attached to this button to make it fully working
	 * 
	 * @param playlist
	 */
	public void attachPlaylist(final JTable playlist) {
		if(this.getController() instanceof MusicPlayer){
			this.playlist = Optional.ofNullable(playlist);
		}
	}
	
}
