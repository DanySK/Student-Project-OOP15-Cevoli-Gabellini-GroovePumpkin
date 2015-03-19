package controller;

import javax.sound.midi.*;

import Model.PlayerState;

/**
 * A midi song player take a midi track and play them
 * 
 * this class gives all method for manage the reproduction (play, stop, pause...)
 * @author Matteo Gabellini
 *
 */

public class MidiSongPlayer implements SongPlayer {
	
	private Sequencer sequencer;
	private Synthesizer synthesizer;
	private MidiChannel channels[];
	private PlayerState playerState;
	
	public MidiSongPlayer(final Sequence midiSequence) throws MidiUnavailableException, InvalidMidiDataException{
			this.sequencer = MidiSystem.getSequencer();		
			this.sequencer.setSequence(midiSequence);
	}
	
	@Override
	public void play() {
		this.sequencer.start();
		this.playerState = PlayerState.RUNNING;
	}

	@Override
	public void stop() {
		this.sequencer.stop();
		this.setPosition(0);
		this.playerState = PlayerState.STOPPED;
	}

	@Override
	public void pause() {
		this.sequencer.stop();
		this.playerState = PlayerState.PAUSED;
	}
	
	@Override
	public void setPosition(final int time) throws IllegalArgumentException {
		if(time < 0 || time > this.sequencer.getMicrosecondLength()){
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
	public PlayerState getState() {
		return this.playerState;
	}

	@Override
	public boolean isActive() {
		return this.sequencer.isRunning();
	}

}
