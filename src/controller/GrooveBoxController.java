package controller;

import java.io.IOException;

/**
 * This rappresent the logic for create song and reproduce them with groove box
 * @author Matteo Gabellini
 *
 */
public interface GrooveBoxController extends Player{
	/**
	 * Save the midi file of the created Groove
	 * @param path 
	 * 			specify where the midi file will be saved
	 * @param fileName
	 * 			specify the name of the midi file
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
	 * Set the function of loop
	 * @param loopActive
	 * 			true - if the loop must be activeted
	 * 			false - if the loop must be deactiveted
	 */
	void setLoop(final boolean loopActive);
	
	
	void setInstrument();
}
