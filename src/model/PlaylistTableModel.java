package model;

import java.net.URL;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import View.config.Utility;

/**
 * This class is a personalized Table Model for a playlist
 * 
 * @author Alessandro
 *
 */

public class PlaylistTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 3639938590302106582L;
	private final String[] names = new String[] { "#", "Song" };
	private List<URL> playlist; 
	
	/**
	 * @param playlist, the playlist to be associated with this table
	 */
	public PlaylistTableModel(final List<URL> playlist) {
		this.playlist= playlist;
	}
	
	@Override
	public boolean isCellEditable(final int rowIndex, final int columnIndex) {
		return false;
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
		if(columnIndex==1){
			return Utility.convertURLPath(playlist.get(rowIndex).getPath());
		} else{
			return String.join("", names[0], String.valueOf(rowIndex+1));
		}
	}

	@Override
	public String getColumnName(int column) {
		return names[column];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return super.getColumnClass(columnIndex);
	}
	
	/**
	 * Attach a new playlist to this model
	 * 
	 * Ex: 
	 * useful if the playlist previously given is 
	 * a copy and if modified, you have to attach again
	 * the playlist to this model so you can properly 
	 * refresh the groovebox
	 * 
	 * @param playlist
	 */
	public void updatePlaylist(final List<URL> playlist){
		this.playlist= playlist;
	}
}