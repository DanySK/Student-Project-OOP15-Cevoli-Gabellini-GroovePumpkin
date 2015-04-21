package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

/**
 * A model for the Groove Box
 * @author Matteo Gabellini
 *
 */

public class GrooveBoxModel extends GrooveTableModel implements GrooveBoxManager {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean loop;
	private Optional<Sequence> grooveSequence;
	
	public GrooveBoxModel(List<GrooveValues> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setLoop(boolean value) {
		this.loop = value;
	}

	private void createSequence() {
		grooveSequence = new MidiSequenceBuilder().createMidiSequence(this.getList());
	}

	@Override
	public Optional<Sequence> getSequence() {
		this.createSequence();
		return grooveSequence;
	}

	@Override
	public void changeCellState(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}

}
