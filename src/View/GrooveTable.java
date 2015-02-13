package View;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import Model.GrooveTimeValues;
import Model.GrooveValues;

@SuppressWarnings("unused")
public class GrooveTable extends JTable {

	private static final long serialVersionUID = -6788098719233400264L;

	private GrooveValues grooveValues;
	private GrooveTimeValues grooveTimeValues;

	private final TableModel datamodel = new AbstractTableModel() {

		private static final long serialVersionUID = -8015327194349106508L;

		@Override
		public int getRowCount() {

			return GrooveValues.values().length;
		}

		@Override
		public int getColumnCount() {

			return GrooveTimeValues.N_COLUMNS;
		}

		@Override
		public String getColumnName(int column) {
			if (column >= getColumnCount()) {
				return null;
			}
			return GrooveTimeValues.values()[getColumnCount()/column].getValues().get(column);
		};

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {

			return null;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		};
	};

	public GrooveTable() {

		this.setModel(dataModel);

	}

}
