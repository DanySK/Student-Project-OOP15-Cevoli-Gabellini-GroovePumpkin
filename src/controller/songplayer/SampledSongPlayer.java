package controller.songplayer;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;

import model.SongPlayerState;

/**
 * A Player of sampled song (wav song)
 * 
 * this class exhibits method for managing the reproduction (play, stop,
 * pause...) and the infos of a sampled track
 * 
 * see also {@link controller.SongPlayer}
 * 
 * @author Matteo Gabellini
 *
 */
public class SampledSongPlayer implements SongPlayer {

	final private Clip clip;
	private SongPlayerState statoLettore;
	/**
	 * This constructor create a new instance of a songPlayer
	 * 
	 * @param song
	 *            where the audio data will be loaded
	 * @throws LineUnavailableException
	 * @throws IOException
	 */
	public SampledSongPlayer(final AudioInputStream song)
			throws LineUnavailableException, IOException {

		clip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, song
				.getFormat()));

		this.clip.open(song);

		this.statoLettore = SongPlayerState.STOPPED;
	}

	@Override
	public synchronized void play() {
		if (this.clip.isOpen()) {
			// Avvia la riproduzione su un altro thread
			this.statoLettore = SongPlayerState.RUNNING;
			this.clip.start();
			/*
			 * if the reproduction on the clip isn't started for sheduling
			 * reason wait 1 second
			 */
			if(!this.clip.isActive()){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

	@Override
	public synchronized void stop() {
		if (this.clip.isOpen()) {
			this.clip.stop();
			this.clip.close();
			this.statoLettore = SongPlayerState.STOPPED;
		}
	}

	@Override
	public synchronized void pause() {
		if (this.clip.isOpen()) {
			// Il metodo stop ferma la riproduzione senza riportare la traccia
			// all'inizio
			this.clip.stop();
			this.statoLettore = SongPlayerState.PAUSED;
		}
	}

	@Override
	public synchronized void setPosition(final int time) throws IllegalArgumentException {
		// Check the parameter
		if (time < 0 || time > (this.getDuration() * 1000000)) {
			throw new IllegalArgumentException();
		}
		this.clip.setMicrosecondPosition(time);
	}

	@Override
	public double getDuration() {
		return this.clip.isOpen()?this.clip.getFrameLength()
				/ this.clip.getFormat()
						.getFrameRate(): 0;
	}

	@Override
	public synchronized double getElapsedTime() {
		/*
		 * The microsecond position measures the time corresponding to the
		 * number of sample frames rendered from the line since it was opened.
		 */
		return this.clip.getFramePosition()
				/ this.clip.getFormat().getFrameRate();
	}

	@Override
	public synchronized SongPlayerState getState() {
		return this.statoLettore;
	}

	@Override
	public synchronized boolean isActive() {
		return this.clip.isActive();
	}

}
