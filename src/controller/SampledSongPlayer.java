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

import Model.PlayerState;

/**
 * 
 * @author Matteo Gabellini
 *
 */
public class SampledSongPlayer implements SongPlayer {
		//public static Mixer mixer;
		private AudioInputStream audioStream;
		// Prendo le informazioni audio del sistema
		//final Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
		
		/*
		 * The Clip interface represents a special kind of data line whose audio
		 * data can be loaded prior to playback, instead of being streamed in real
		 * time.
		 */
		private  Clip clip;
		private  PlayerState statoLettore;
		private boolean pause;
		
		/* Questa variabile contiene un oggeto di una classe anonima che implementa
		 * il comportamento */
		private Thread threadSongWatcher = new Thread() {
			
			@Override
			public void run() {
				while(clip.isActive() || SampledSongPlayer.this.pause){
					//Finche la traccia è attiva stoppo momentaneamente il thread
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//una volta che la traccia è terminata 
				SampledSongPlayer.this.stop();
			}
		};
		
		/**
		 * This constructor create a new instance of a songPlayer
		 * @param clip where the audio data will be loaded
		 * @throws LineUnavailableException 
		 * @throws IOException 
		 */
		public SampledSongPlayer(AudioInputStream song) throws LineUnavailableException, IOException {
			//Va creata una classe per le impostazioni
			// Prendo il primo dispositivo audio;
			//mixer = AudioSystem.getMixer(mixInfos[0]);
			
			clip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, song.getFormat()));			
			
			this.clip.open(song);
			
			this.statoLettore = PlayerState.STOPPED;
		}
		
		public Clip getClip(URL songURL){
			return this.clip;
		}
		
		@Override
		public void play() {
			this.pause = false;
			//Avvia la riproduzione su un altro thread
			this.statoLettore = PlayerState.RUNNING;
			this.clip.start();			
			try {
				//Fermo un attimo il thread in modo che 
				//possa partire la riproduzione della traccia sul thread della clip
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//Avvio un thread che controlla quando la canzone termina se non è già attivo
			if(!threadSongWatcher.isAlive()){
				threadSongWatcher.start();
			}
		}

		@Override
		public void stop() {
			this.clip.stop();
			this.clip.close();
			this.statoLettore = PlayerState.STOPPED;			
		}

		@Override
		public void pause() {
			//Il metodo stop ferma la riproduzione senza riportare la traccia all'inizio
			this.pause = true;
			this.clip.stop();
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
		public boolean isActive() {
			return this.clip.isActive();
		}
		
		@Override
		protected void finalize() throws Throwable {
			// Quando l'oggetto viene distrutto mi assicuro di chiudere la clip
			super.finalize();
			this.clip.close();
		}
		
		
	}
