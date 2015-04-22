package Model;

import java.util.List;
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
	 * Able or disale the loop mode
	 * @param true if the loop must be activeted otherwise false
	 */
	void setLoop(final boolean value);
	
	/**
	 * Take the midi sequence that corresponding to the groove box table
	 * @return an Optional that may contains the groove sequence
	 */
	Optional<Sequence> getSequence();
	
	/**
	 * Change the state of the Cell specified by the parameters
	 * @param rowIndex 
	 * 			the index of the Groove table row
	 * @param columnIndex
	 * 			the index of the Groove table column  
	 */
	void changeCellState(final int rowIndex, final int columnIndex); 
}
