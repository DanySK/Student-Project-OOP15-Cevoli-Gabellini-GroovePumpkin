package controller;

import javax.sound.midi.*;

import bbb.SingleSongPlayerState;

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
	private Synthesizer synthesizer;
	private MidiChannel channels[];
	private SingleSongPlayerState singleSongPlayerState;

	public MidiSongPlayer(final Sequence midiSequence)
			throws MidiUnavailableException, InvalidMidiDataException {
		this.sequencer = MidiSystem.getSequencer();
		this.sequencer.open();
		this.sequencer.setSequence(midiSequence);
	}

	@Override
	public synchronized void play() {
		this.sequencer.start();
		this.singleSongPlayerState = SingleSongPlayerState.RUNNING;
	}

	@Override
	public synchronized void stop() {
		this.sequencer.stop();
		this.singleSongPlayerState = SingleSongPlayerState.STOPPED;
		this.sequencer.close();		
	}

	@Override
	public synchronized void pause() {
		this.sequencer.stop();
		this.singleSongPlayerState = SingleSongPlayerState.PAUSED;
	}

	@Override
	public void setPosition(final int time) throws IllegalArgumentException {
		if (time < 0 || time > this.sequencer.getMicrosecondLength()) {
			throw new IllegalArgumentException();
		}
		this.sequencer.setMicrosecondPosition(time);
	}

	@Override
	public double getDuration() {
		return (this.sequencer.getMicrosecondLength() * 1000000);
	}

	@Override
	public double getElapsedTime() {
		return this.sequencer.getMicrosecondPosition();
	}

	@Override
	public SingleSongPlayerState getState() {
		return this.singleSongPlayerState;
	}

	@Override
	public boolean isActive() {
		return this.sequencer.isRunning();
	}

	public void setBPM(int bpm) {
		this.sequencer.setTempoInBPM(bpm);
	}
	
	/*
	@Override
	public void setGain(final double gainValue) throws IllegalArgumentException{
		if(gainValue < 0 || gainValue > 100){
			throw new IllegalArgumentException("The argument value is not between 0 and 100");
		}
	}*/
}
