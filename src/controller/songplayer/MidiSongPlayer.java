package controller.songplayer;

import javax.sound.midi.*;

import model.SongPlayerState;

/**
 * A midi song player take a midi track and play them
 * 
 * this class gives all method for manage the reproduction (play, stop,
 * pause...)
 * 
 * @author Matteo Gabellini
 *
 */

public class MidiSongPlayer implements SongPlayer {

	private Sequencer sequencer;
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

	public void setBPM(int bpm) {
		this.sequencer.setTempoInBPM(bpm);
	}
	
}
