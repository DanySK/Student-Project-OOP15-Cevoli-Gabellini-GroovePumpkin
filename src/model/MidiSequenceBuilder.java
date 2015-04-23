package model;

import java.util.List;
import java.util.Optional;

import javax.sound.midi.*;

/**
 * A MidiTrackBuilder implements the logic for create a midi track from a list of GrooveValues
 * @author Matteo Gabellini
 *
 */
public class MidiSequenceBuilder {
	
	private static final int VELOCITY = 100; // la velocità di tocco può essere da 0 a 127 
	private static final int SELECTED_CHANNEL = 1; //Canale midi da 0 a 15
	
	/**
	 * 
	 * @param partitura
	 * @return An optional that contains the midi track, if during the creation there is some problem the return value is Optional.empty
	 */
	public Optional<Sequence> createMidiSequence(List<GrooveValues> partitura){
		Sequence midiSequence = null;
		
		try {
			midiSequence = new Sequence(Sequence.PPQ, 4);
			
			final Track midiTrack = midiSequence.createTrack();
			midiTrack.add(createEvent(0, ShortMessage.PROGRAM_CHANGE,SELECTED_CHANNEL, 1, VELOCITY));
			// Qui non ho usato il metodo flat map perchè perderei la
			// possibilità di accedere a getID quando creo l'evento
			partitura.stream().forEach(
					X -> {
						X.getRow().stream().forEach(
								Y -> {
									midiTrack.add(createEvent(Y.getSecond(),
											ShortMessage.NOTE_ON,
											SELECTED_CHANNEL, X.getID(), VELOCITY));
									midiTrack.add(createEvent(Y.getSecond(),
											ShortMessage.NOTE_OFF,
											SELECTED_CHANNEL, X.getID(), VELOCITY));
								});
					});
			
		} catch (InvalidMidiDataException e) { 
			e.printStackTrace();
		}
		
		
		return Optional.ofNullable(midiSequence);
	}
	
	/*
	 * this method is used for create a MidiEvent
	 * see the class MidiEvent
	 * 
	 * param:
	 * 
	 * tick - the time-stamp for the event, in MIDI ticks
	 * midiCommand -  the MIDI command represented by this message
	 * channel - the channel associated with the message
	 * fData - the first data byte
     * velocity - the force with the note is played(from 0 to 127)
	 */ 
	private MidiEvent createEvent(final long tick, final int midiCommand, final int channel, final int fData , final int velocity){
		MidiEvent event;
		final ShortMessage message = new ShortMessage();
		try {
			message.setMessage(midiCommand, channel, fData, velocity);
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		event = new MidiEvent(message, tick);
		return event;
	}
}
