package model.groovebox;

import java.util.Optional;

import javax.sound.midi.Sequence;
import model.MidiSequenceCreationStrategy;

/**
 * This class rappresent the logic for manage the data of a groove box. 
 * see: {@link #GrooveValues}
 * @author Matteo Gabellini
 *
 */

public class GrooveBoxModel implements GrooveBoxContentManager {
	private Optional<Sequence> grooveSequence;
	
	public GrooveBoxModel() {
		GrooveValues.initAGrooveBoxList();
	}
	
	private void createSequence() {
		grooveSequence = new MidiSequenceCreationStrategy().createMidiSequence(GrooveValues.GROOVEBOX);
	}

	@Override
	public Optional<Sequence> getSequence() {
		this.createSequence();
		return grooveSequence;
	}

	@Override
	public void changeCellState(int rowIndex, int columnIndex) {
		GrooveValues.GROOVEBOX.get(rowIndex).setActiveAtIndex(columnIndex);		
	}

	@Override
	public boolean getCellState(int rowIndex, int columnIndex) {
		return GrooveValues.GROOVEBOX.get(rowIndex).getValueAtIndex(columnIndex);
	}

	@Override
	public void resetContent() {
		GrooveValues.resetGrooveBox();		
	}

}
