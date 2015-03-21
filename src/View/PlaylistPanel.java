package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import controller.MusicPlayer;
import Model.PlayerState;
import Model.PlaylistTableModel;
import View.buttons.RemoveSpace;
import static Model.Utility.*;
import static View.buttons.ButtonFactory.*;

/**
 * Personalized Panel for the PlayBackPanel class, 
 * this class manages the playlist,
 * addition and removeal of songs from it.
 * 
 * @author Alessandro
 *
 */
public class PlaylistPanel extends PersonalJPanel implements Updatable{

	private static final long serialVersionUID = 5045956389400601388L;
	private MusicPlayer controller;
	private TableModel dataModel;
	private JTable playlist;
	private final JScrollPane jsp= new JScrollPane();
	
	/**
	 * The default contructor for this object creates a ready to use 
	 * Playlist Panel with the given controller
	 * 
	 * @param mp
	 */
	public PlaylistPanel(final MusicPlayer mp) {
		super(new BorderLayout());
		this.setBuiltInBorder();
		
		this.controller= mp;
		this.controller.addUpdatableObserver(this);
		
		this.dataModel= new PlaylistTableModel(controller.getPlayList());
		this.playlist= new JTable(dataModel);
		playlist.setBackground(WHITE);
		playlist.setForeground(GRAY);
		playlist.setRowSelectionAllowed(true);
		
		jsp.setViewportView(playlist);
		jsp.setBackground(WHITE);
		jsp.setForeground(GRAY);

		final PersonalJPanel buttonRow = new PersonalJPanel(new FlowLayout());
		buttonRow.add(createButton(ADD_BUTTON, true, mp));
		
		final RemoveSpace remove= (RemoveSpace) createButton(REMOVE_BUTTON, true, controller);
		remove.attachPlaylist(playlist);
		buttonRow.add(remove);

		this.add(jsp, BorderLayout.CENTER);
		this.add(buttonRow, BorderLayout.SOUTH);
	}
	
	/**
	 * 
	 * @return the Jtable implementation for the playlist
	 */
	public JTable getPlaylistTable(){
		return this.playlist;
	}
	
	/**
	 * 
	 * @return the model attached to the JTable 
	 */
	public TableModel getPlaylistModel(){
		return this.dataModel;
	}
	
	/**
	 * 
	 * @return the controller attached top this object
	 */
	public MusicPlayer getController(){
		return this.controller;
	}
	
	/**
	 * Set a JTable for the implementation of the Playlist
	 * 
	 * @param table
	 */
	public void setPlaylistTable(final JTable table){
		this.jsp.remove(playlist);
		this.playlist= table;
		this.jsp.add(playlist);
	}
	
	/**
	 * Set a new Model for the JTable
	 * 
	 * @param model
	 */
	public void setPlaylistModel(final TableModel model){
		this.dataModel= model;
		this.setPlaylistTable(new JTable(model));
	}
	
	/**
	 * Attach a new controller to this object
	 * 
	 * @param mp
	 */
	public void setController(final MusicPlayer mp){
		this.controller= mp;
		this.controller.addUpdatableObserver(this);
	}
	
	//Called by the Controller when a song is added or removed from the playlist
	@Override
	public void updateStatus(final PlayerState status) {
		//ricrea la nuova tabella
		if(status==PlayerState.RELOAD){
			((PlaylistTableModel) dataModel).updatePlaylist(this.controller.getPlayList());
			playlist.tableChanged(new TableModelEvent(dataModel));	
		}
	}
}
