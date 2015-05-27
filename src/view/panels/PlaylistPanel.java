package view.panels;

import static javax.swing.ListSelectionModel.*;
import static view.buttons.ButtonFactory.*;
import static view.config.Configuration.*;
import static view.config.Utility.getADefaultPanelBorder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;

import model.PlayerState;
import view.buttons.strategies.PlaylistStrategy;
import view.config.Utility;
import view.model.AbstractMouseListener;
import view.model.PlaylistTableModel;
import view.tables.PersonalJTable;
import controller.musicplayer.MusicPlayer;

/**
 * Personalized Panel for the PlayBackPanel class, this class manages the
 * playlist, addition and removeal of songs from it.
 * 
 * It also has by default a mouse listener that hadles the selection, the
 * playing and the remotion of songs from the JTable usign the mouse
 * 
 * @author Alessandro
 *
 */
public class PlaylistPanel extends AbstractControllablePane<MusicPlayer>{

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
	public PlaylistPanel(final MusicPlayer mp) {
		super(new BorderLayout());
		this.setBorder(getADefaultPanelBorder());
		this.setController(mp);
		this.getController().addUpdatableObservers(this);

		this.tableModel = new PlaylistTableModel(getController());
		this.playlist = new PersonalJTable(tableModel, MULTIPLE_INTERVAL_SELECTION);
		this.playlist.setColumnHeaderBounds(0, 30, 30);
		jsp.setViewportView(playlist);
		jsp.setBackground(WHITE);
		jsp.setForeground(DARK_GRAY);

		this.getCommandPane().add(new CmdPane.Builder()
		.setAdd(createButton(ADD_B, getController(), true))
		.setRemove(createButton(REMOVE_B, getController(), true))
		.build(new FlowLayout()));

		this.add(this.getCommandPane().get(0), BorderLayout.SOUTH);
		this.add(jsp, BorderLayout.CENTER);

		this.playlist.addMouseListener(new AbstractMouseListener() {
			@Override
			public void mousePressed(final MouseEvent e) {
				
				if(getCommandPane().get(0).getWrapper().getRemove().isPresent() && 
						getCommandPane().get(0).getWrapper().getRemove().get()
						.getStrategy() instanceof PlaylistStrategy){
					((PlaylistStrategy) getCommandPane().get(0).getWrapper().getRemove().get().getStrategy())
							.setSelectedIndexes(((JTable) e.getSource())
								.getSelectedRows());
				}
			}

			@Override
			public void mouseClicked(final MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)
						&& e.getClickCount() == DOUBLE_CLICK) {
					try{
						getController().stop();
						getController().goToSong(((JTable) e.getSource())
								.rowAtPoint(e.getPoint()));
						getController().play();
					}catch(IllegalArgumentException ex){
						Utility.showErrorDialog(playlist, 
								"Illegal Argument, an error has probably occurred");
					}
				} else if (SwingUtilities.isRightMouseButton(e)) {
					final JPopupMenu jpm = new JPopupMenu();
					final JMenuItem rem = new JMenuItem("Remove");
					rem.setForeground(RED);
					rem.addActionListener(actEv -> {
						
						if (((JTable) e.getSource())
								.getSelectedRows().length > 1) {
							getController().removeSong(((JTable) e.getSource())
										.getSelectedRows());
						} else {
							getController().removeSong(((JTable) e.getSource())
									.rowAtPoint(e.getPoint()));
						}
					});
					jpm.add(rem);
					final JMenuItem add = new JMenuItem("Add");
					add.setForeground(RED);
					add.addActionListener(actEv -> {
						getCommandPane().get(0).getWrapper().getAdd().get().doStrategy();
					});
					jpm.add(add);
					jpm.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	@Override
	public void updateStatus(final PlayerState status) {
		// ricrea la nuova tabella
		if (status == PlayerState.RELOAD) {
			playlist.tableChanged(new TableModelEvent(this.tableModel));
		}
	}

	/**
	 * 
	 * @return the Jtable implementation for the playlist
	 */
	public JTable getPlaylist() {
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
	public void setPlaylist(final PersonalJTable table) {
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
		this.setPlaylist(new PersonalJTable(model, SINGLE_SELECTION));
	}
}