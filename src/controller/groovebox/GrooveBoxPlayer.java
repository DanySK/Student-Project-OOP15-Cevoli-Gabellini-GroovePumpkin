package controller.groovebox;

import java.io.IOException;

import controller.Loopable;
import controller.Observable;
import controller.Player;

/**
 * This rappresent the logic to manage a groove and reproduce them with groove box
 * @author Matteo Gabellini
 *
 */
public interface GrooveBoxPlayer extends Player,Loopable,Observable{
	/**
	 * Save the midi file of the created Groove
	 * @param path 
	 * 			specify where the midi file will be saved,
	 * 			the last word of the path is the name of the file
	 * @return true if the file was saved otherwise false
	 */
	boolean saveTrack(final String pathName) throws IOException;
	
	/**
	 * Set the tempo of the reproduction expressed in beats per minute
	 * @param BPM
	 */
	void setTempoInBPM(final int BPM);
	
	/**
	 * Change the state of the Cell specified by the parameters
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
	 * @return true if the cell is active otherwise false
	 */
	boolean getCellState(final int rowIndex, final int columnIndex);
	
	/**
	 * Reset the groove box table 
	 */
	void reset();
}
