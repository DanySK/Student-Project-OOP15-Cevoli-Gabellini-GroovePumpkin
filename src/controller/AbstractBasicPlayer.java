package controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import model.PlayerState;
import model.SongPlayerState;
import controller.songplayer.SongPlayer;
import controller.songplayer.SongWatchDog;
/**
 * An abstract class that implements the basic function of a music player indipendent from 
 * song format
 * @author Matteo Gabellini
 *
 */
public abstract class AbstractBasicPlayer extends UpdatableObserversManager implements Player {
	protected Optional<SongPlayer> soundPlayer;
	private Optional<SongWatchDog> threadSongWatcher;	
	
	protected AbstractBasicPlayer(){
		this.soundPlayer = Optional.empty();
		this.threadSongWatcher = Optional.empty();
	}
	
	protected abstract void loadSong();
	
	@Override
	public synchronized void play() {
		if(!this.soundPlayer.isPresent()){
			this.loadSong();
		}
		try {

			this.soundPlayer.get().play();
			if (this.soundPlayer.get().isActive()
					&& !threadSongWatcher.isPresent()) {
				threadSongWatcher = Optional.of(new SongWatchDog(this,
						this.soundPlayer.get()));
				threadSongWatcher.get().start();
			}

			this.notifyToUpdatable(soundPlayer.get().getState() == SongPlayerState.RUNNING ? PlayerState.RUNNING
					: PlayerState.ERROR);
		} catch (NoSuchElementException e) {
			this.notifyToUpdatable(PlayerState.ERROR);
		}		
	}

	protected abstract void afterSongEnding();

	@Override
	public  synchronized void stop() {
		if(this.soundPlayer.isPresent()){
			final boolean songEnded = this.soundPlayer.get().getState().equals(SongPlayerState.RUNNING) && !this.soundPlayer.get().isActive();
			this.soundPlayer.get().stop();
			// If the song was invocated by the
			// song watchdog the soundPlayer is not active
			if (!songEnded && this.threadSongWatcher.isPresent()) {
				// wait the termination of song watchdog
				try {
					this.threadSongWatcher.get().join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
			this.notifyToUpdatable(this.soundPlayer.get().getState() == SongPlayerState.STOPPED ? PlayerState.STOPPED
					: PlayerState.ERROR);
			this.threadSongWatcher = Optional.empty();
			this.soundPlayer = Optional.empty();
			//If the state of the sound player is RUNNING and the sound player isn't active means that the stop was called by the song watchdog because the song was terminated
			if(songEnded){
				this.afterSongEnding();
			} 
		}
	}
	
	@Override
	public synchronized void pause() {
		if (this.soundPlayer.isPresent()) {
			this.soundPlayer.get().pause();
			this.notifyToUpdatable(soundPlayer.get().getState() == SongPlayerState.PAUSED ? PlayerState.PAUSED
					: PlayerState.ERROR);
		}
	}
	
}
