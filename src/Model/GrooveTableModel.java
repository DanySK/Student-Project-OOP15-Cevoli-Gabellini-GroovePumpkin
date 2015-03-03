package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

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

	private List<GrooveValues> grooveValues;
	
	/**
	 * 
	 * @param list
	 */
	public GrooveTableModel(final List<GrooveValues> list) {
		grooveValues=list;
	}
	
	@Override
	public int getRowCount() {

		return GrooveValues.DefaultValues.values().length;
	}

	@Override
	public int getColumnCount() {

		return GrooveValues.GROOVE_TIME_VALUES.length;
	}

	@Override
	public String getColumnName(final int column) {

		return GrooveValues.GROOVE_TIME_VALUES[column];
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {

		if (columnIndex == 0) {
			return GrooveValues.DefaultValues.values()[rowIndex].getInstrument();
		} else{
			return grooveValues.get(rowIndex).getColor(columnIndex-1);
		}
	}

	@Override
	public boolean isCellEditable(final int rowIndex, final int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
		if (aValue instanceof Color) {
			grooveValues.get(rowIndex).setColor((Color) aValue, columnIndex-1);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public List<GrooveValues> getList(){
		return grooveValues;
	}

}