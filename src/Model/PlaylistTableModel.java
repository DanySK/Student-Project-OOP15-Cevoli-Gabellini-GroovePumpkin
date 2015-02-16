package Model;

import java.io.File;
import java.util.List;

import javax.swing.table.AbstractTableModel;
/**
 * 
 * @author Alessandro
 *
 * @param <X>
 */

public class PlaylistTableModel<X> extends AbstractTableModel{

	private static final long serialVersionUID = 3639938590302106582L;
	private final String[] names = new String[] { "Song" };
	private List<X> songs; 
	
	public PlaylistTableModel(final List<X> playlist) {
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
			Object obj = ((File)songs.get(rowIndex)).getName();
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


/*
 * new AbstractTableModel() {
		
		private static final long serialVersionUID = 3639938590302106582L;
		private final String[] names = new String[] { "Song" };
		
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
				Object obj = ((File)songs.get(rowIndex)).getName();
				return obj;
			} catch (IndexOutOfBoundsException e) {
				return null;
			}
		}

		@Override
		public String getColumnName(int column) {
			if (column >= names.length) {
				return null;
			}
			return names[column];
		}
	};
	*/
