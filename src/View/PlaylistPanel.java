package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;

import controller.MusicPlayer;
import Model.PlayerState;
import Model.PlaylistTableModel;
import View.buttons.ButtonFactory;
import View.buttons.PersonalJButton;
import static Model.Utility.*;

/**
 * Personalized Panel for the PlayBackPanel class, 
 * this class "handles" the playlist,
 * adding or removing song from it.
 * 
 * @author Alessandro
 *
 */
public class PlaylistPanel extends PersonalJPanel implements Updatable{

	private static final long serialVersionUID = 5045956389400601388L;

	/**
	 * FILE deve essere esteso o wrappato da una classe SONG, che permetta una
	 * migliore interazione con la JTable
	 */

	private MusicPlayer controller;

	/**
	 * Creation of an anonymous class as a model for the JTable class
	 * 
	 */
	private TableModel dataModel;
	private JTable playlist;
	private final JScrollPane jsp= new JScrollPane();;
	
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
		buttonRow.add(ButtonFactory.createButton(ButtonFactory.ADD_BUTTON, true, mp));
		
		final PersonalJButton remove= new PersonalJButton(REMOVE_IMG);
		remove.setTitle("Remove");
		remove.addActionListener(e->{
			// rimuovi una canzone
			try {
				controller.removeSong(playlist.getSelectedRow());
				//Devo aggironare io?
			} catch (Exception ex) {
				// do nothing
			}
		});
		
		buttonRow.add(remove);

		this.add(jsp, BorderLayout.CENTER);
		this.add(buttonRow, BorderLayout.SOUTH);
	}
	
	public Component getPlaylistTable(){
		return this.playlist;
	}
	
	public TableModel getPlaylistModel(){
		return this.dataModel;
	}
	
	public void setPlaylistTable(final JTable table){
		this.jsp.remove(playlist);
		this.playlist= table;
		this.jsp.add(playlist);
	}
	
	public void setPlaylistModel(final TableModel model){
		this.dataModel= model;
		this.setPlaylistTable(new JTable(model));
	}
	//Called by the Controller when a song is added or removed from the playlist
	@Override
	public void updateStatus(PlayerState status) {
		//ricrea la nuova tabella
		((PlaylistTableModel) dataModel).updatePlaylist(this.controller.getPlayList());
		playlist.tableChanged(new TableModelEvent(dataModel));		
	}
}
