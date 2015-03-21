package controller;

import javax.sound.midi.*;

import Model.SingleSongPlayeState;

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
	private SingleSongPlayeState SingleSongPlayeState;
	private boolean pause;
	
	/* Questa variabile contiene un oggeto di una classe anonima che implementa
	 * il comportamento per controllare quando la canzone termina perchè è stata riprodotta tutta*/
	private Thread threadSongWatcher = new Thread() {
		
		@Override
		public void run() {
			while(MidiSongPlayer.this.isActive() || MidiSongPlayer.this.pause){
				//Finche la traccia è attiva stoppo momentaneamente il thread
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//una volta che la traccia è terminata 
			MidiSongPlayer.this.stop();
			sequencer.close();
		}
	};
	
	public MidiSongPlayer(final Sequence midiSequence) throws MidiUnavailableException, InvalidMidiDataException{
			this.sequencer = MidiSystem.getSequencer();	
			this.sequencer.open();
			this.sequencer.setSequence(midiSequence);
	}
	
	@Override
	public void play() {
		this.pause = false;
		this.sequencer.start();
		this.SingleSongPlayeState = SingleSongPlayeState.RUNNING;
	}

	@Override
	public void stop() {
		this.sequencer.stop();
		this.sequencer.close();
		this.SingleSongPlayeState = SingleSongPlayeState.STOPPED;
	}

	@Override
	public void pause() {
		this.pause = true;
		this.sequencer.stop();
		this.SingleSongPlayeState = SingleSongPlayeState.PAUSED;
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
	public SingleSongPlayeState getState() {
		return this.SingleSongPlayeState;
	}

	@Override
	public boolean isActive() {
		return this.sequencer.isRunning();
	}
	
	public void setBPM(int bpm){
		this.sequencer.setTempoInBPM(bpm);
	}
}
