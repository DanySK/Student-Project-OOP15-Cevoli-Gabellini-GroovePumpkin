package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;

import view.interfaces.Updatable;
import model.GrooveBoxContentManager;
import model.GrooveBoxModel;

/**
 * The implementation of GrooveBoxController
 * @author Matteo Gabellini
 *
 */
public class GrooveBoxController implements GrooveBoxPlayer,Observable{
	private Optional<MidiSongPlayer> sequencer;
	private GrooveBoxContentManager model;
	private List<Updatable> component;
	
	public GrooveBoxController(){
		this.model = new GrooveBoxModel();
	}
	
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
	public void setLoop(final boolean loopActive) {
		this.model.setLoop(loopActive);
	}

	@Override
	public void addUpdatableObserver(final Updatable component) {
		if (this.component == null) {
			this.component = new ArrayList<>();
		}
		this.component.add(component);
	}
	
	@Override
	public boolean saveTrack(final String pathName) throws IOException {
		final File outPutFile = new File(pathName);
		final Optional<Sequence> createdSequence = this.model.getSequence();
		if(createdSequence.isPresent()){
			int[] fileTypes = MidiSystem.getMidiFileTypes(createdSequence.get());
			//I check how many midi file type my system is able to write
			if(fileTypes.length != 0){
				//I try to write the file and check how may bytes were written
				if(MidiSystem.write(createdSequence.get(), fileTypes[0], outPutFile) != -1){
					return true;
				}
			}
		}
		return false;
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
	public void changeCellState(final int rowIndex, final int columnIndex) {
		this.model.changeCellState(rowIndex, columnIndex);
	}

	@Override
	public boolean getCellState(int rowIndex, int columnIndex) {
		return this.model.getCellState(rowIndex, columnIndex);
	}

	@Override
	public boolean isLoopModeActive() {
		return this.model.isLoopActive();
	}

}
