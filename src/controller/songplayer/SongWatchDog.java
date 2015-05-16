package controller.songplayer;

import model.SongPlayerState;
import controller.Player;

/**
 * Song watch dog is a thread that control during the reproduction the state of the song 
 * when song finish them call the method for close the reproduction on the song player and call the stop of the music player
 * @author Matteo Gabellini
 *
 */
public class SongWatchDog extends Thread{
	
	final private Player player;
	final private SongPlayer soundPlayer;
	
	public SongWatchDog(final Player player, final SongPlayer soundPlayer){
		this.player = player; 
		this.soundPlayer = soundPlayer;
	}
	
	@Override
	public void run() {
		
		while (this.soundPlayer.isActive()
				|| this.soundPlayer.getState() == SongPlayerState.PAUSED) {
			// Finche la traccia è attiva stoppo
			// momentaneamente il
			// thread
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Controllo che non sia stato invocato lo stop
		// dall'utente
		if (!this.soundPlayer.getState().equals(SongPlayerState.STOPPED)) {
			this.player.stop();
		}

	}
}
