package controller;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;

import Model.PlayerState;

/**
 * 
 * @author Matteo Gabellini
 *
 */
public class SampledSongPlayer implements SongPlayer {
		public static Mixer mixer;
		private AudioInputStream audioStream;
		// Prendo le informazioni audio del sistema
		final Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
		
		/*
		 * The Clip interface represents a special kind of data line whose audio
		 * data can be loaded prior to playback, instead of being streamed in real
		 * time.
		 */
		private  Clip clip;
		private  PlayerState statoLettore;
		
		/**
		 * This constructor create a new instance of a songPlayer
		 * @param clip where the audio data will be loaded
		 */
		public SampledSongPlayer(AudioInputStream song) {
			//Va creata una classe per le impostazioni
			// Prendo il primo dispositivo audio;
			mixer = AudioSystem.getMixer(mixInfos[0]);
			
			try {
				clip = (Clip) mixer.getLine(new DataLine.Info(Clip.class, null));
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				this.clip.open(song);
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.statoLettore = PlayerState.STOPPED;
		}
		
		public Clip getClip(URL songURL){
			return this.clip;
		}
		
		@Override
		public void play() {
			//Avvia la riproduzione su un altro thread
			this.clip.start();
			this.statoLettore = PlayerState.RUNNING;
		}

		@Override
		public void stop() {
			this.clip.stop();
			this.statoLettore = PlayerState.STOPPED;
			
		}

		@Override
		public void pause() {
			//this.clip.
			this.statoLettore = PlayerState.PAUSED;
		}

		@Override
		public double getDuration() {
            return clip.getBufferSize() / (clip.getFormat().getFrameSize() * clip.getFormat().getFrameRate());
		}

		@Override
		public double getElapsedTime() {
			return clip.getFramePosition() / clip.getFormat().getFrameRate();
		}

		@Override
		public PlayerState getState() {
			return this.statoLettore;
		}
		
		
		@Override
		protected void finalize() throws Throwable {
			// Quando l'oggetto viene distrutto mi assicuro di chiudere la clip
			super.finalize();
			clip.close();
		}
		
	}
