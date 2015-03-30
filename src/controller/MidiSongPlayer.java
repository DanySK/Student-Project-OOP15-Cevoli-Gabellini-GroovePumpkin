package controller;

import javax.sound.midi.*;

import Model.SingleSongPlayerState;

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
		if (!this.sequencer.isOpen()) {
			// Attendo in modo da essere sicuro che il sequencer si apra
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.sequencer.setSequence(midiSequence);
	}

	@Override
	public void play() {
		this.sequencer.start();
		this.singleSongPlayerState = SingleSongPlayerState.RUNNING;
	}

	@Override
	public void stop() {
		this.sequencer.stop();
		this.sequencer.close();
		this.singleSongPlayerState = SingleSongPlayerState.STOPPED;
	}

	@Override
	public void pause() {
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
}
