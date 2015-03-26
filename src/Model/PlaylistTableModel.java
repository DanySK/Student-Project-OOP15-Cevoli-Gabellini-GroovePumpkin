package Model;

import java.net.URL;
import java.util.List;

import javax.swing.table.AbstractTableModel;
/**
 * This class is a personalized Table Model for a playlist
 * 
 * @author Alessandro
 *
 */

public class PlaylistTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 3639938590302106582L;
	private final String[] names = new String[] { "Song" };
	private List<URL> playlist; 
	
	/**
	 * @param playlist, the playlist to be associated with this table
	 */
	public PlaylistTableModel(final List<URL> playlist) {
		this.playlist= playlist;
	}
	
	@Override
	public int getRowCount() {
		return playlist.size();
	}

	@Override
	public int getColumnCount() {
		return names.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			return ((URL)playlist.get(rowIndex)).getPath();
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return names[0];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return super.getColumnClass(columnIndex);
	}
	
	public void updatePlaylist(final List<URL> playlist){
		this.playlist= playlist;
	}
}