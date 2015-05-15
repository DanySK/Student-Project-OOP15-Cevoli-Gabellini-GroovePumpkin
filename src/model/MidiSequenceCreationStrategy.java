package model;

import java.util.List;
import java.util.Optional;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import model.groovebox.GrooveValues;

/**
 * A MidiTrackBuilder implements the logic for create a midi track from a list of GrooveValues
 * @author Matteo Gabellini
 *
 */
public class MidiSequenceCreationStrategy {
	
	private static final int VELOCITY = 100; // la velocità di tocco può essere
												// da 0 a 127
	/*
	 * Midi Channels are 16 and the channel 10 in the GM(General Midi) standard
	 * is reserved for percussion. In the program the channels are indexed from
	 * 0 to 9 so the 10 channel in the program correspond to 9
	 */
	private static final int SELECTED_CHANNEL = 9; 
	private static final int INSTRUMENT_INDEX = 0;
	/**
	 * 
	 * @param partitura
	 * @return An optional that contains the midi track, if during the creation there is some problem the return value is Optional.empty
	 */
	public Optional<Sequence> createMidiSequence(final List<? extends GrooveValues> partitura){
		Sequence midiSequence = null;
		try {
			midiSequence = new Sequence(Sequence.PPQ, 4);
			
			final Track midiTrack = midiSequence.createTrack();
			midiTrack.add(createEvent(0, ShortMessage.PROGRAM_CHANGE, SELECTED_CHANNEL, INSTRUMENT_INDEX, VELOCITY));
			// Qui non ho usato il metodo flat map perchè perderei la
			// possibilità di accedere a getID quando creo l'evento
			
			partitura.stream().forEach(
					X -> {
						X.getRow()
						.stream()
						.filter(K -> K.getFirst())
						.forEach(
								Y -> {
									//System.out.println("Strumento " + X.getID() + " First " + Y.getFirst() + " Second" + Y.getSecond());
									
									midiTrack.add(createEvent(Y.getSecond(),
											ShortMessage.NOTE_ON,
											SELECTED_CHANNEL, X.getID(), VELOCITY));
									midiTrack.add(createEvent(Y.getSecond() + 1,
											ShortMessage.NOTE_OFF,
											SELECTED_CHANNEL, X.getID(), VELOCITY));
								});
					});

			midiTrack.add(createEvent(GrooveValues.getTimeQuanti(), ShortMessage.PROGRAM_CHANGE,SELECTED_CHANNEL, INSTRUMENT_INDEX, VELOCITY));
		} catch (InvalidMidiDataException e) { 
			e.printStackTrace();
		}
		 /*
		Sequencer sequencer;
		try {
			sequencer = MidiSystem.getSequencer();

			sequencer.open();
			try {
				sequencer.setSequence(midiSequence);
				sequencer.start();
			} catch (InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		return Optional.ofNullable(midiSequence);
	}
	
	/*
	 * this method is used for create a MidiEvent see the class MidiEvent
	 * 
	 * param:
	 * 
	 * tick - the time-stamp for the event, in MIDI ticks midiCommand - the MIDI
	 * command represented by this message channel - the channel associated with
	 * the message fData - the first data byte (if the command is
	 * Program-Changed this value rappresents the index of the instrument
	 * otherwise if the command if NOTE_ON or NOTE_OFF this value corresponding
	 * to the note value) velocity - the force with the note is played(from 0 to
	 * 127)
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
