package controller;

import java.io.File;

import View.Updatable;

/**
 * The implementation of GrooveBoxController
 * @author Matteo Gabellini
 *
 */
public class GrooveBoxControllerImpl implements GrooveBoxController {
	private MidiSongPlayer sequencer;
	
	@Override
	public void play() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoop(final boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addUpdatableObserver(final Updatable component) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTrack() {
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
		this.sequencer.setBPM(BPM);
	}

	@Override
	public void setInstrument() {
		// TODO Auto-generated method stub

	}

}
