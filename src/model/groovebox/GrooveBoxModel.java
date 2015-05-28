package model.groovebox;

import java.util.Optional;

import javax.sound.midi.Sequence;
import model.MidiSequenceCreationStrategy;

/**
 * This class rappresent the logic for manage the data of a groove box. 
 * see: {@link #GrooveValues}
 * 
 * @author Matteo Gabellini
 *
 */

public class GrooveBoxModel implements GrooveBoxContentManager {
	private Optional<Sequence> grooveSequence;
	
	public GrooveBoxModel() {
		GrooveTableManager.getGrooveBox();
	}
	
	private void createSequence() {
		grooveSequence = new MidiSequenceCreationStrategy().createMidiSequence(GrooveTableManager.getGrooveBox());
	}

	@Override
	public Optional<Sequence> getSequence() {
		this.createSequence();
		return grooveSequence;
	}

	@Override
	public void changeCellState(final int rowIndex, final int columnIndex) {
		GrooveTableManager.getGrooveBox().get(rowIndex).invertValueAtIndex(columnIndex);		
	}

	@Override
	public boolean getCellState(final int rowIndex, final int columnIndex) {
		return GrooveTableManager.getGrooveBox().get(rowIndex).getValueAtIndex(columnIndex);
	}

	@Override
	public void resetContent() {
		GrooveTableManager.resetGrooveBox();		
	}

}
