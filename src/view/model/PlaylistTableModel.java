package view.model;

import javax.swing.table.AbstractTableModel;

import view.config.Utility;
import controller.musicplayer.MusicPlayer;

/**
 * This class is a personalized Table Model for a playlist
 * 
 * @author Alessandro
 *
 */

public class PlaylistTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 3639938590302106582L;
	private final String[] names = new String[] { "#", "Song" };
	private final MusicPlayer controller;
	
	/**
	 * @param playlist, the playlist to be associated with this table
	 */
	public PlaylistTableModel(final MusicPlayer controller) {
		super();
		this.controller= controller;
	}
	
	@Override
	public boolean isCellEditable(final int rowIndex, final int columnIndex) {
		return false;
	}
	
	@Override
	public int getRowCount() {
		return controller.getPlayList().size();
	}

	@Override
	public int getColumnCount() {
		return names.length;
	}

	@Override
	public Object getValueAt(final int rowIndex,final int columnIndex) {
		if(columnIndex == 1){
			return Utility.convertURLPath(controller.getPlayList().get(rowIndex).getPath());
		} else {
			return String.join("", String.valueOf(rowIndex+1), ".");
		}
	}

	@Override
	public String getColumnName(final int column) {
		return names[column];
	}
}