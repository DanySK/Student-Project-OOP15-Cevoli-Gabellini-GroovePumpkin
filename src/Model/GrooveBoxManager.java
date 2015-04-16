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
}
