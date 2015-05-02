package model;

import java.util.Optional;
import javax.sound.midi.Sequence;

/**
 * This class rappresent the logic for manage the data of a groove box. 
 * see: {@link #GrooveValues}
 * @author Matteo Gabellini
 *
 */

public class GrooveBoxModel implements GrooveBoxContentManager {
	
	private boolean loop;
	private Optional<Sequence> grooveSequence;
	
	public GrooveBoxModel() {
		GrooveValues.initAGrooveBoxList();
	}
	
	@Override
	public void setLoop(boolean value) {
		this.loop = value;
	}
	
	@Override
	public boolean isLoopActive() {
		return this.loop;
	}
	
	private void createSequence() {
		grooveSequence = new MidiSequenceBuilder().createMidiSequence(GrooveValues.GROOVEBOX);
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
