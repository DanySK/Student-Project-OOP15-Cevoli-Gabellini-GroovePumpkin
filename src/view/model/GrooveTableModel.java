package view.model;

import java.awt.Color;

import javax.swing.table.AbstractTableModel;

import model.groovebox.DefaultValues;
import controller.groovebox.GrooveBoxPlayer;
import static view.config.Configuration.*;

/**
 * This class is a specialized Table Model for the groove box
 * 
 * @author Alessandro
 *
 */
public class GrooveTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -8015327194349106508L;
	
	private static final String[] GROOVE_TIME_VALUES = new String[] {
			"-Instruments-", "#0", "A", "B", "C", "#1", "A", "B", "C", "#2",
			"A", "B", "C", "#3", "A", "B", "C", "#4", "A", "B", "C", "#5", "A",
			"B", "C", "#6", "A", "B", "C", "#7", "A", "B", "C", "#8", "A", "B",
			"C", "#9", "A", "B", "C" };

	private final GrooveBoxPlayer controller;
	
	/**
	 * A constructor fo a GrooveTable model that take in input 
	 * a GrooveboxPlayer controller
	 * 
	 * @param controller
	 */
	public GrooveTableModel(final GrooveBoxPlayer controller) {
		this.controller= controller;
	}
	
	/**
	 * 
	 * @return Return the Array With the names fo the columns
	 */
	public static String[] getGrooveTimeValues(){
		return GROOVE_TIME_VALUES;
	}
	
	
	@Override
	public int getRowCount() {

		return DefaultValues.values().length;
	}

	@Override
	public int getColumnCount() {

		return GROOVE_TIME_VALUES.length;
	}

	@Override
	public String getColumnName(final int column) {

		return GROOVE_TIME_VALUES[column];
	}
	
	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {

		if (columnIndex == 0) {
			return DefaultValues.values()[rowIndex].getInstrument();
		} else{
			return getRowColor(rowIndex, controller.getCellState(rowIndex, columnIndex-1));
		}
	}
	
	@Override
	public boolean isCellEditable(final int rowIndex, final int columnIndex) {
		return false;
	}
	
	@Override
	public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
		if(columnIndex> 0){
			controller.changeCellState(rowIndex, columnIndex-1);
		}
	}
	
	/**
	 * This utility method create a color based on the given row
	 * 
	 * @param row
	 *            the row selected
	 * @return a new random Color
	 */
	private static Color getRowColor(final int row, final Boolean b) {

		return b ? row % 2 == 0 ? ORANGE : DARK_GRAY : WHITE ;
	}
}