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
	
	public MidiSongPlayer(/*Qui gli passo la traccia midi*/) throws MidiUnavailableException{
			this.sequencer = MidiSystem.getSequencer();
			
	}
	
	@Override
	public void play() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public double getDuration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getElapsedTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PlayerState getState() {
		// TODO Auto-generated method stub
		return null;
	}

}
