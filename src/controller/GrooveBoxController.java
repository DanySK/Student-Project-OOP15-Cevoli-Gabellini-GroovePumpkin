package controller;

import java.io.File;

/**
 * This rappresent the logic for create song and reproduce them with groove box
 * @author Matteo Gabellini
 *
 */
public interface GrooveBoxController extends Player{
	void saveTrack(final String path);
	void loadTrack(final File file);	
	void setTempoInBPM(final int BPM);
	void setInstrument();
}
