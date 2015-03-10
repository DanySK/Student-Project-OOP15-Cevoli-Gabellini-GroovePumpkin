package Model;

import java.net.URL;
import java.util.*;

/**
 * This Class rappresent an implementation of the MusicPlayerModel interface
 * 
 * @author Matteo Gabellini
 *
 */
public class MusicPlayerModelImpl implements MusicPlayerModel {

	private boolean shuffleMode;
	private boolean loopMode;
	private List<URL> playList;
	private Optional<URL> currentSong;
	private int currentPlaylistIndex; // rappresent the current index of the

	// song in the playlist

	// private Stack<URL>

	public MusicPlayerModelImpl() {
		this.playList = new ArrayList<>();
	}

	@Override
	public Optional<URL> getCurretSong() {
		if (this.currentSong.isPresent()) {
			return Optional.of(this.currentSong.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<URL> getNextSong() {
		// Controllo se sono all'ultima canzone
		if (this.playList.size() == this.currentPlaylistIndex + 1) {
			// In tal caso ritorno un oggetto Optional vuoto
			return Optional.empty();
		}

		if (this.shuffleMode = true) {
			// call the method that implements the random choice of the song
		}
		return Optional.of(this.playList.get(this.currentPlaylistIndex + 1));
	}

	@Override
	public Optional<URL> getPreviousSong() {
		// Controllo se sono alla prima canzone
		if (this.currentPlaylistIndex - 1 == 0) {
			// In tal caso ritorno un oggetto Optional vuoto
			return Optional.empty();
		}

		if (this.shuffleMode = true) {
			// take the previous song from the stack
		}
		return Optional.of(this.playList.get(this.currentPlaylistIndex - 1));
	}

	@Override
	public Optional<URL> changeToTheNextSong() {
		this.currentSong = this.getNextSong();

		if (this.shuffleMode) {

		} else {
			this.currentPlaylistIndex++;
		}
		return this.currentSong;
	}

	@Override
	public Optional<URL> changeToThePreviousSong() {
		this.currentSong = this.getPreviousSong();
		if (this.shuffleMode) {

		} else {
			this.currentPlaylistIndex--;
		}
		return this.currentSong;
	}

	@Override
	public Optional<URL> changeSong(final int index) throws IllegalArgumentException {
		// Controllo che index sia corretto
		if (index < 0 || index >= this.playList.size()) {
			throw new IllegalArgumentException();
		}
		this.currentSong = Optional.of(this.playList.get(index));
		this.currentPlaylistIndex = index;
		return this.currentSong;
	}

	@Override
	public void setShuffleMode(final boolean active) {
		this.shuffleMode = active;
	}

	@Override
	public boolean isShuffleModeActive(){
		return this.shuffleMode;
	}
	
	@Override
	public void setLoopMode(final boolean active) {
		this.loopMode = active;
	}

	@Override
	public boolean isLoopModeActive(){
		return this.loopMode;
	}
	
	@Override
	public void addSongToPlayList(final URL songPath) throws IllegalArgumentException {
		if (songPath == null) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void removeSongFromPlayList(final int index)
			throws IllegalArgumentException {
		if (index < 0 || index > this.playList.size() - 1) {
			throw new IllegalArgumentException();
		}
		this.playList.remove(index);
	}

	@Override
	public void loadPlayList(final List<URL> playList)
			throws IllegalArgumentException {
		if (playList == null) {
			throw new IllegalArgumentException();
		}
		this.playList = playList;
	}

	@Override
	public List<URL> getPlayList() {
		// return a defense copy of the playlist
		return new ArrayList<>(this.playList);
	}

}
