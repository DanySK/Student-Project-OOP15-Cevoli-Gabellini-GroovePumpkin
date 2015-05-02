package view.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import view.buttons.FunctionalButton;
import view.buttons.strategies.PlaylistStrategy;
import view.others.PLML;
import view.tables.PersonalJTable;
import view.viewModel.PlaylistTableModel;
import model.PlayerState;
import controller.MusicPlayer;
import controller.Observable;
import static javax.swing.ListSelectionModel.*;
import static view.buttons.ButtonFactory.*;
import static view.config.Utility.*;

/**
 * Personalized Panel for the PlayBackPanel class, this class manages the
 * playlist, addition and removeal of songs from it.
 * 
 * @author Alessandro
 *
 */
public class PlaylistPanel extends PersonalJPanel<MusicPlayer>{

	private static final long serialVersionUID = 5045956389400601388L;
	private TableModel tableModel;
	private PersonalJTable playlist;
	private final JScrollPane jsp = new JScrollPane();

	/**
	 * The default contructor for this object creates a ready to use Playlist
	 * Panel with the given controller
	 * 
	 * @param mp
	 */
	@SuppressWarnings("unchecked")
	public PlaylistPanel(final MusicPlayer mp) {
		super(new BorderLayout());
		this.setBorder(getADefaultPanelBorder());

		this.setController(mp);
		((Observable) this.getController()).addUpdatableObserver(this);

		this.tableModel = new PlaylistTableModel((MusicPlayer) getController());
		this.playlist = new PersonalJTable(tableModel, SINGLE_SELECTION);
		this.playlist.setColumnHeaderBounds(0, 25, 25);
		jsp.setViewportView(playlist);
		jsp.setBackground(WHITE);
		jsp.setForeground(DARK_GRAY);

		final PersonalJPanel<Object> buttonRow = new PersonalJPanel<>(new FlowLayout());
		buttonRow.add(createButton(ADD_BUTTON, true, mp));

		final JButton remove = createButton(REMOVE_BUTTON, true, mp);
		buttonRow.add(remove);

		this.add(buttonRow, BorderLayout.SOUTH);
		this.add(jsp, BorderLayout.CENTER);
		
		
		this.playlist.getColumnModel().getSelectionModel()
				.addListSelectionListener(e -> {
							if (!e.getValueIsAdjusting()) {
								((PlaylistStrategy) ((FunctionalButton<MusicPlayer>) remove)
										.getStrategy()).setSelectedIndexes(playlist.getSelectedRows());
							}
						});
		
		//this.addKeyListener(this.getPlayAdapter());
		
		this.playlist.addMouseListener(new PLML((MusicPlayer) getController()));
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
		this.setPlaylistTable(new PersonalJTable(model,
				SINGLE_SELECTION));
	}

	// Called by the Controller when a song is added or removed from the
	// playlist
	@Override
	public void updateStatus(final PlayerState status) {
		// ricrea la nuova tabella
		if (status == PlayerState.RELOAD) {
			playlist.tableChanged(new TableModelEvent(tableModel));
		}
	}
}
