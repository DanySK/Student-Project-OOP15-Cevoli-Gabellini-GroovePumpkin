package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import static javax.swing.ListSelectionModel.*;

import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;

import bbb.PlayerState;
import bbb.PlaylistTableModel;
import controller.MusicPlayer;
import View.buttons.RemoveSpace;
import static View.buttons.ButtonFactory.*;
import static View.config.Utility.*;

/**
 * Personalized Panel for the PlayBackPanel class, this class manages the
 * playlist, addition and removeal of songs from it.
 * 
 * @author Alessandro
 *
 */
public class PlaylistPanel extends PersonalJPanel implements Updatable {

	private static final long serialVersionUID = 5045956389400601388L;
	private MusicPlayer controller;
	private TableModel tableModel;
	private PersonalJTable playlist;
	private final JScrollPane jsp = new JScrollPane();

	/**
	 * The default contructor for this object creates a ready to use Playlist
	 * Panel with the given controller
	 * 
	 * @param mp
	 */
	public PlaylistPanel(final MusicPlayer mp) {
		super(new BorderLayout());
		this.setBuiltInBorder();

		this.controller = mp;
		this.controller.addUpdatableObserver(this);

		this.tableModel = new PlaylistTableModel(controller.getPlayList());
		this.playlist = new PersonalJTable(tableModel, SINGLE_SELECTION);
		this.playlist.setColumnHeaderBounds(0, 25, 25);
		jsp.setViewportView(playlist);
		jsp.setBackground(WHITE);
		jsp.setForeground(DARK_GRAY);
		
		final PersonalJPanel buttonRow = new PersonalJPanel(new FlowLayout());
		buttonRow.add(createButton(ADD_BUTTON, true, mp));

		final RemoveSpace remove = (RemoveSpace) createButton(REMOVE_BUTTON,
				true, mp);
		buttonRow.add(remove);
		
		this.add(buttonRow, BorderLayout.SOUTH);
		this.add(jsp, BorderLayout.CENTER);

		this.playlist.getColumnModel().getSelectionModel()
				.addListSelectionListener(e -> {
							if (!e.getValueIsAdjusting()) {
								remove.setSelectedIndex(this.playlist.getSelectedRows());
							}
						});
		
		this.playlist.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(final MouseEvent e) {}
			
			@Override
			public void mousePressed(final MouseEvent e) {
				remove.setSelectedIndex(((JTable)e.getSource()).getSelectedRow());
			}
			
			@Override
			public void mouseExited(final MouseEvent e) {}
			
			@Override
			public void mouseEntered(final MouseEvent e) {}
			
			@Override
			public void mouseClicked(final MouseEvent e) {
				if(e.getClickCount()==DOUBLE_CLICK){
					//if(controller.getCurrentSong().get()
					//		.getFile().equals(controller.getPlayList()
					//				.get(playlist.getSelectedRow()).getFile())){
					//	if(controller.){ //servirebbe un metodo isPaused()
					//		controller.pause();
					//	} else{
					//		controller.play();
					//	}
					//} else{
						controller.stop();
						controller.goToSong(((JTable)e.getSource()).getSelectedRow());
						controller.play();
					//}
				}
			}
		});
	}

	/**
	 * 
	 * @return the Jtable implementation for the playlist
	 */
	public JTable getPlaylistTable() {
		return this.playlist;
	}

	/**
	 * 
	 * @return the model attached to the JTable
	 */
	public TableModel getPlaylistModel() {
		return this.tableModel;
	}

	/**
	 * 
	 * @return the controller attached top this object
	 */
	public MusicPlayer getController() {
		return this.controller;
	}

	/**
	 * Set a JTable for the implementation of the Playlist
	 * 
	 * @param table
	 */
	public void setPlaylistTable(final PersonalJTable table) {
		this.jsp.remove(playlist);
		this.playlist = table;
		this.jsp.add(playlist);
	}

	/**
	 * Set a new Model for the JTable
	 * 
	 * @param model
	 */
	public void setPlaylistModel(final TableModel model) {
		this.tableModel = model;
		this.setPlaylistTable(new PersonalJTable(model, MULTIPLE_INTERVAL_SELECTION));
	}

	/**
	 * Attach a new controller to this object
	 * 
	 * @param mp
	 */
	public void setController(final MusicPlayer mp) {
		this.controller = mp;
		this.controller.addUpdatableObserver(this);
	}

	// Called by the Controller when a song is added or removed from the
	// playlist
	@Override
	public void updateStatus(final PlayerState status) {
		// ricrea la nuova tabella
		if (status == PlayerState.RELOAD) {
			((PlaylistTableModel) tableModel).updatePlaylist(this.controller
					.getPlayList());
			playlist.tableChanged(new TableModelEvent(tableModel));
		}
	}
}
