package model.groovebox;

import java.util.Optional;

import javax.sound.midi.Sequence;

/**
 * The classes that implements this interface are able to manage the
 * datastructure of a groove box
 * 
 * @author Matteo Gabellini
 *
 */
public interface GrooveBoxContentManager{
	/**
	 * Take the midi sequence that corresponding to the groove box table
	 * @return an Optional that may contains the groove sequence
	 */
	Optional<Sequence> getSequence();
	
	/**
	 * Change the state of the cell specified by the parameters
	 * @param rowIndex 
	 * 			the index of the Groove table row
	 * @param columnIndex
	 * 			the index of the Groove table column  
	 */
	void changeCellState(final int rowIndex, final int columnIndex); 
	
	/**
	 * Take the state of a cell
	 * @param rowIndex 
	 * 			the index of the Groove table row
	 * @param columnIndex
	 * 			the index of the Groove table column  
	 * @return true if the cell is active or false
	 */
	boolean getCellState(final int rowIndex, final int columnIndex);
	
	/**
	 * Reset the content of the groove box
	 */
	void resetContent();
}
