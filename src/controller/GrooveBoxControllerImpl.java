package controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;

import Model.GrooveBoxModel;
import Model.TableValueManager;
import View.Updatable;

/**
 * The implementation of GrooveBoxController
 * @author Matteo Gabellini
 *
 */
public class GrooveBoxControllerImpl implements GrooveBoxController{
	private Optional<MidiSongPlayer> sequencer;
	private GrooveBoxModel model;
	private List<Updatable> component;
	
	@Override
	public void play() {
		Optional<Sequence> sequence = model.getSequence();
		
		if(sequence.isPresent()){
			try {
				sequencer = Optional.of(new MidiSongPlayer(sequence.get()));
				sequencer.get().play();
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			} catch (InvalidMidiDataException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void pause() {
		if (sequencer.isPresent()) {
			sequencer.get().pause();
		}
	}

	@Override
	public void stop() {
		if (sequencer.isPresent()) {
			sequencer.get().stop();
		}
	}

	@Override
	public void setLoop(final boolean value) {
		this.model.setLoop(value);
	}

	@Override
	public void addUpdatableObserver(final Updatable component) {
		// TODO Auto-generated method stub
	}

	@Override
	public void saveTrack(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadTrack(File file) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTempoInBPM(final int BPM) {
		if(this.sequencer.isPresent()){
			this.sequencer.get().setBPM(BPM);
		}
	}

	@Override
	public void setInstrument() {
		// TODO Auto-generated method stub
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.model.getValueAt(rowIndex, columnIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		this.model.setValueAt(aValue, rowIndex, columnIndex);
	}

}
