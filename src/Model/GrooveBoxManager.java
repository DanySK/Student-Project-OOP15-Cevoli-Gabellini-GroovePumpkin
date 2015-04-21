package Model;

import java.util.List;
import java.util.Optional;

import javax.sound.midi.Sequence;

/**
 * 
 * @author Matteo Gabellini
 *
 */
public interface GrooveBoxManager{
	void setLoop(final boolean value);
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
