package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import Model.GrooveValues;

/**
 * This class is a specialized Table Model for the groove box
 * 
 * @author Alessandro
 *
 * @param <X>
 */
@SuppressWarnings("unused")
public class GrooveTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -8015327194349106508L;
	
	/**
	 * The names of all the columns, 
	 * i've used a static array instead of an ArrayList 
	 * because i was already sure it won't have changed
	 */
	public static final String[] GrooveTimeValues = new String[]{
		"Instruments", "#1", "A", "B", "C", "#2", "A", "B", "C", "#3", "A",
		"B", "C", "#4", "A", "B", "C" , "#5", "A", "B", "C", "#6", "A", "B",
		"C", "#7", "A", "B", "C", "#8", "A", "B", "C", "#9", "A", "B", "C",
		"#10", "A", "B", "C"};

	private GrooveValues grooveValues;

	@Override
	public int getRowCount() {

		return GrooveValues.values().length;
	}

	@Override
	public int getColumnCount() {

		return GrooveTimeValues.length;
	}

	@Override
	public String getColumnName(int column) {

		return GrooveTimeValues[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		if (columnIndex == 0) {
			return GrooveValues.values()[rowIndex].getInstrument();
		}

		return GrooveValues.values()[rowIndex].getColor(columnIndex-1);
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		return columnIndex == 0 ? false : true;

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex != 0) {
			if (columnIndex == 0 && aValue instanceof String) {
				GrooveValues.values()[rowIndex].setName((String) aValue);
			} else if(aValue instanceof Color){
				GrooveValues.values()[rowIndex].setColor((Color) aValue, columnIndex-1);
			}
		}
	}

}