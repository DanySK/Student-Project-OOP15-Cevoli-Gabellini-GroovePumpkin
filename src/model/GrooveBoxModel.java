package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

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
		GrooveValues2.initAGrooveBoxList();
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
		grooveSequence = new MidiSequenceBuilder().createMidiSequence(GrooveValues2.GROOVEBOX);
	}

	@Override
	public Optional<Sequence> getSequence() {
		this.createSequence();
		return grooveSequence;
	}

	@Override
	public void changeCellState(int rowIndex, int columnIndex) {
		GrooveValues2.GROOVEBOX.get(rowIndex).setActiveAtIndex(columnIndex);		
	}

	@Override
	public boolean getCellState(int rowIndex, int columnIndex) {
		return GrooveValues2.GROOVEBOX.get(rowIndex).getValueAtIndex(columnIndex);
	}

	@Override
	public void resetContent() {
		GrooveValues2.resetGrooveBox();		
	}

}
