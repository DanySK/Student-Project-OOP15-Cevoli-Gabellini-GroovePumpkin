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
	private List<URL> songs; 
	
	/**
	 * @param playlist, the playlist to be associated with this table
	 */
	public PlaylistTableModel(final List<URL> playlist) {
		songs= playlist;
	}
	
	@Override
	public int getRowCount() {
		return songs.size();
	}

	@Override
	public int getColumnCount() {
		return names.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			Object obj = ((URL)songs.get(rowIndex)).getFile();
			return obj;
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
}