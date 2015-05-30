package controller.songplayer;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import model.SongPlayerState;

/**
 * A midi song player take a midi track and play them
 * 
 * this class exhibits method for managing the reproduction (play, stop,
 * pause...) and the infos of a midi track
 * 
 * see also {@link controller.songplayer}
 * 
 * @author Matteo Gabellini
 *
 */

public class MidiSongPlayer implements SongPlayer {

	final private Sequencer sequencer;
	private SongPlayerState singleSongPlayerState;

	public MidiSongPlayer(final Sequence midiSequence)
			throws MidiUnavailableException, InvalidMidiDataException {
		this.sequencer = MidiSystem.getSequencer();
		this.sequencer.open();
		this.sequencer.setSequence(midiSequence);
	}

	@Override
	public synchronized void play() {
		this.sequencer.start();
		this.singleSongPlayerState = SongPlayerState.RUNNING;
	}

	@Override
	public synchronized void stop() {
		this.sequencer.stop();
		this.singleSongPlayerState = SongPlayerState.STOPPED;
		this.sequencer.close();		
	}

	@Override
	public synchronized void pause() {
		this.sequencer.stop();
		this.singleSongPlayerState = SongPlayerState.PAUSED;
	}

	@Override
	public synchronized void setPosition(final int time) throws IllegalArgumentException {
		if (time < 0 || time > (this.getDuration() * 1000000)) {
			throw new IllegalArgumentException();
		}
		this.sequencer.setMicrosecondPosition(time);
	}

	@Override
	public synchronized double getDuration() {
		return this.sequencer.getMicrosecondLength() * 1000000;
	}

	@Override
	public synchronized double getElapsedTime() {
		return this.sequencer.getMicrosecondPosition();
	}

	@Override
	public synchronized SongPlayerState getState() {
		return this.singleSongPlayerState;
	}

	@Override
	public synchronized boolean isActive() {
		return this.sequencer.isRunning();
	}

	/**
	 * Set the time with which the midi sequence is played
	 * @param bpm
	 * 	    beat per minute
	 */
	public void setBPM(final int bpm) {
		this.sequencer.setTempoInBPM(bpm);
	}
	
}
