package Model;

import java.util.List;
import java.util.Optional;

import javax.sound.midi.*;

/**
 * A MidiTrackBuilder implements the logic for create a midi track from a list of GrooveValues
 * @author Matteo Gabellini
 *
 */
public class MidiTrackBuilder {
	
	private static final int VELOCITY = 100; // la velocità di tocco può essere da 0 a 127 
	private static final int SELECTED_CHANNEL = 1; //Canale midi da 0 a 15
	
	/**
	 * 
	 * @param partitura
	 * @return An optional that contains the midi track, if during the creation there is some problem the return value is Optional.empty
	 */
	public Optional<Track> createMidiTrack(List<GrooveValues> partitura){
		Track midiTrack = null; 
		
		try {
			midiTrack = (new Sequence(Sequence.PPQ, 4)).createTrack();
			midiTrack.add(createEvent(0, ShortMessage.PROGRAM_CHANGE,SELECTED_CHANNEL, 1, VELOCITY).get());
			partitura.stream().forEach(
					X -> {
						X.getColorsList().stream().forEach(
								Y -> {
									createEvent(Y.getSecond(),
											ShortMessage.NOTE_ON,
											SELECTED_CHANNEL, X.getID(), VELOCITY);
									createEvent(Y.getSecond(),
											ShortMessage.NOTE_OFF,
											SELECTED_CHANNEL, X.getID(), VELOCITY);
								});
					});
		} catch (InvalidMidiDataException e) { 
			e.printStackTrace();
		}
		
		
		return Optional.ofNullable(midiTrack);
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
     * sData- the second data byte
	 */ 
	private Optional<MidiEvent> createEvent(final long tick, final int midiCommand, final int channel, final int fData , final int velocity){
		Optional<MidiEvent> event = Optional.empty();
		ShortMessage message = new ShortMessage();
		try {
			message.setMessage(midiCommand, channel, fData, velocity);
			event = Optional.of(new MidiEvent(message, tick));
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return event;
	}
}
