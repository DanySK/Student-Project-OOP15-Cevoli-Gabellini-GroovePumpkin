package controller;

import java.io.IOException;
import java.net.URL;

import javax.naming.OperationNotSupportedException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;

import Model.SingleSongPlayerState;

/**
 * A Player of sampled song (wav song)
 * 
 * @author Matteo Gabellini
 *
 */
public class SampledSongPlayer implements SongPlayer {

	/*
	 * The Clip interface represents a special kind of data line whose audio
	 * data can be loaded prior to playback, instead of being streamed in real
	 * time.
	 */
	private Clip clip;
	private SingleSongPlayerState statoLettore;

	

	/**
	 * This constructor create a new instance of a songPlayer
	 * 
	 * @param clip
	 *            where the audio data will be loaded
	 * @throws LineUnavailableException
	 * @throws IOException
	 */
	public SampledSongPlayer(AudioInputStream song)
			throws LineUnavailableException, IOException {
		// Va creata una classe per le impostazioni
		// Prendo il primo dispositivo audio;
		// mixer = AudioSystem.getMixer(mixInfos[0]);

		clip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, song
				.getFormat()));

		this.clip.open(song);

		this.statoLettore = SingleSongPlayerState.STOPPED;
	}

	public Clip getClip(URL songURL) {
		return this.clip;
	}

	@Override
	public synchronized void play() {
		// Avvia la riproduzione su un altro thread
		this.statoLettore = SingleSongPlayerState.RUNNING;
		this.clip.start();
		try {
			// Fermo un attimo il thread in modo che
			// possa partire la riproduzione della traccia sul thread della clip
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void stop() {
		this.clip.stop();
		this.clip.close();
		this.statoLettore = SingleSongPlayerState.STOPPED;
	}

	@Override
	public synchronized void pause() {
		// Il metodo stop ferma la riproduzione senza riportare la traccia
		// all'inizio
		this.clip.stop();
		this.statoLettore = SingleSongPlayerState.PAUSED;
	}

	@Override
	public  void setPosition(final int time) throws IllegalArgumentException {
		// Check the parameter
		if (time < 0 || time > (this.getDuration() * 1000000)) {
			throw new IllegalArgumentException();
		}
		this.clip.setMicrosecondPosition(time);
	}

	@Override
	public  double getDuration() {
		return this.clip.getBufferSize()
				/ (this.clip.getFormat().getFrameSize() * this.clip.getFormat()
						.getFrameRate());
	}

	@Override
	public  double getElapsedTime() {
		/*
		 * The microsecond position measures the time corresponding to the
		 * number of sample frames rendered from the line since it was opened.
		 */
		return this.clip.getFramePosition()
				/ this.clip.getFormat().getFrameRate();
	}

	@Override
	public SingleSongPlayerState getState() {
		return this.statoLettore;
	}

	@Override
	public boolean isActive() {
		return this.clip.isActive();
	}

}
