package controller;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;

import controller.songplayer.MidiSongPlayer;
import controller.songplayer.SongWatchDog;
import model.LoopManager;
import model.SongPlayerState;
import model.groovebox.GrooveBoxContentManager;
import model.groovebox.GrooveBoxModel;
import static model.PlayerState.*;


/**
 * The implementation of GrooveBoxController
 * 
 *   
 * This class implements the pattern singleton, so for taking the instance of
 * this class use the method getInstance
 * 
 * @author Matteo Gabellini
 *
 */
public class GrooveBoxController extends UpdatableObserversManager implements GrooveBoxPlayer{
	private static final GrooveBoxController GROOVE_BOX = new GrooveBoxController();
	
	
	private Optional<MidiSongPlayer> sequencer;
	private final GrooveBoxContentManager model;
	private LoopManager lManager;
	private SongWatchDog threadGrooveWatchdog;
	
	private GrooveBoxController(){
		super();
		this.model = new GrooveBoxModel();
	}
	
	public static GrooveBoxController getInstance(){
		return GrooveBoxController.GROOVE_BOX;
	}
	
	@Override
	public void play() {
		final Optional<Sequence> sequence = model.getSequence();
		
		if(sequence.isPresent()){
			try {
				this.sequencer = Optional.of(new MidiSongPlayer(sequence.get()));
				this.sequencer.get().play();
				this.threadGrooveWatchdog = new SongWatchDog(this, this.sequencer.get());
				if(this.sequencer.get().isActive()){
					notifyToUpdatable(RUNNING);
				}
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
			notifyToUpdatable(PAUSED);
		}
	}

	@Override
	public void stop() {
		if (sequencer.isPresent()) {
			if(this.sequencer.get().isActive()){
				//This code is execute when the stop is called by the user
				this.sequencer.get().stop();
				try {
					this.threadGrooveWatchdog.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				notifyToUpdatable(this.sequencer.get().getState() == SongPlayerState.STOPPED ? STOPPED
						: ERROR);
				this.sequencer = Optional.empty();
			} else {
				//This code is executed when the stop is called by the Song Watcher
				this.sequencer.get().stop();
				if(this.lManager.isLoopModeActive()){
					this.play();
				}
			}	
		}		
	}
	
	@Override
	public boolean saveTrack(final String pathName) throws IOException {
		final File outPutFile = new File(pathName);
		final Optional<Sequence> createdSequence = this.model.getSequence();
		if(createdSequence.isPresent()){
			final int[] fileTypes = MidiSystem.getMidiFileTypes(createdSequence.get());
			//I check how many midi file type my system is able to write
			//and I try to write the file and check how may bytes were written			
			if(fileTypes.length != 0 && MidiSystem.write(createdSequence.get(), fileTypes[0], outPutFile) != -1){				
					return true;				
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
	public void changeCellState(final int rowIndex, final int columnIndex) {
		this.model.changeCellState(rowIndex, columnIndex);
	}

	@Override
	public boolean getCellState(final int rowIndex, final int columnIndex) {
		return this.model.getCellState(rowIndex, columnIndex);
	}

	@Override
	public void setLoop(final boolean loopActive) {
		this.lManager.setLoopMode(loopActive);
		this.notifyToUpdatable(this.lManager.isLoopModeActive()? LOOPED : UNLOOPED);
	}
	
	@Override
	public boolean isLoopModeActive() {
		return this.lManager.isLoopModeActive();
	}

	@Override
	public void reset() {
		this.model.resetContent();
		notifyToUpdatable(RELOAD);
	}

}
