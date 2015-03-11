package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.net.URL;
import java.util.ArrayList;

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
	private final TableModel dataModel;

	private final JTable playlist;

	public PlaylistPanel(final MusicPlayer mp) {
		super(new BorderLayout());
		this.setBuiltInBorder();
		
		this.controller= mp;
		this.controller.addUpdatableObserver(this);
		
		this.dataModel= new PlaylistTableModel(new ArrayList<URL>());
		this.playlist= new JTable(dataModel);
		playlist.setBackground(WHITE);
		playlist.setForeground(GRAY);
		playlist.setRowSelectionAllowed(true);
		
		final JScrollPane jsp = new JScrollPane();
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
				//controller.
			} catch (Exception ex) {
				// do nothing
			}
		});
		
		buttonRow.add(remove);

		this.add(jsp, BorderLayout.CENTER);
		this.add(buttonRow, BorderLayout.SOUTH);
	}

	//Called by the Controller when a song is added or removed from the playlist
	@Override
	public void updateStatus(PlayerState status) {
		//ricrea la nuova tabella
		//playlist= controller.getPlaylist();
		playlist.tableChanged(new TableModelEvent(dataModel));		
	}
}
