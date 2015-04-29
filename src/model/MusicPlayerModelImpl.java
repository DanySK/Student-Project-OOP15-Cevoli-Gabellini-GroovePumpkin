package model;

import java.net.URL;
import java.util.*;

/**
 * This Class rappresent an implementation of the MusicPlayerModel interface
 * 
 * @author Matteo Gabellini
 * @author Alessandro Cevoli
 */
public class MusicPlayerModelImpl implements MusicPlayerModel {

	private boolean shuffleMode;
	private boolean loopMode;
	private List<URL> playList;
	private Optional<URL> currentSong;

	// rappresent the current index of the song in the playlist
	private Optional<Integer> currentPlaylistIndex;
	private int currShuffledIdx = -1;
	private List<Integer> shuffled;

	public MusicPlayerModelImpl() {
		this.playList = new ArrayList<>();
		this.currentSong = Optional.empty();
		this.currentPlaylistIndex = Optional.empty();
	}

	private Optional<URL> shuffle() {
		final int idx = new Random().nextInt(playList.size());
		shuffled.add(idx);
		currShuffledIdx = shuffled.size() - 1;
		return Optional.ofNullable(playList.get(idx));
	}

	public Optional<Integer> getCurrentSongIndex() {
		return Optional.ofNullable(this.currentPlaylistIndex.get());
	}

	@Override
	public Optional<URL> getCurretSong() {
		return Optional.ofNullable(this.currentSong.get());
	}

	@Override
	public String getCurrentTitle() {
		if (this.currentSong.isPresent()) {
			String[] urlSplitted = this.currentSong.get().toString().split("/");
			return urlSplitted[urlSplitted.length - 1];
		}
		return "";
	}

	@Override
	public Optional<URL> getNextSong() {
		// Check if was selected a song

		if (!this.currentPlaylistIndex.isPresent()) {
			// if the song wasn't selected I return a empty Optional
			return Optional.empty();
		}

		// Controllo se sono all'ultima canzone
		if (this.playList.size() == this.currentPlaylistIndex.get() + 1) {
			// In tal caso ritorno un oggetto Optional vuoto
			return Optional.empty();
		}
		
		if (this.shuffleMode) {
			// call the method that implements the random choice of the song
			// the shuffled list is empty or the actual index is the last
			if (shuffled.size() == 0
					|| this.currShuffledIdx == shuffled.size() - 1) {
				// I get a new shuffled song
				return this.shuffle();
			} else {
				// Altrimenti prendo la canzone successiva dall'array di indici
				return Optional.ofNullable(this.playList.get(this.shuffled
						.get(++this.currShuffledIdx)));
			}

		}
		
		return Optional
				.of(this.playList.get(this.currentPlaylistIndex.get() + 1));
	}

	@Override
	public Optional<URL> getPreviousSong() {

		if (!this.currentPlaylistIndex.isPresent()) {
			// se la canzone corrente non è stata selezionata restituisco un
			// optional vuoto
			return Optional.empty();
		}

		// Controllo se sono alla prima canzone
		if (this.currentPlaylistIndex.get() - 1 == -1) {
			// In tal caso ritorno un oggetto Optional vuoto
			return Optional.empty();
		}

		// Controllo se non è stata selezionata una canzone
		if (this.shuffleMode && currShuffledIdx != -1) {
			// take the previous song from the stack
			// If it is the first song of the shuffled playlist then it'll start
			// again
			if (currShuffledIdx == 0) {
				return Optional.ofNullable(this.playList.get(this.shuffled
						.get(currShuffledIdx)));
			} else {
				return Optional.ofNullable(this.playList.get(this.shuffled
						.get(--currShuffledIdx)));
			}

		}

		return Optional
				.of(this.playList.get(this.currentPlaylistIndex.get() - 1));
	}

	@Override
	public Optional<URL> changeToTheNextSong() {
		final Optional<URL> nextSong = this.getNextSong();
		// If the previous song there isn't I don't change the current song
		if (nextSong.isPresent()) {
			this.currentSong = nextSong;
			if (this.shuffleMode) {
				this.currentPlaylistIndex = Optional.of(shuffled
						.get(currShuffledIdx));
			} else {
				this.currentPlaylistIndex = Optional
						.of(this.currentPlaylistIndex.get() + 1);
			}
			return this.currentSong;
		}
		return Optional.empty();
	}

	@Override
	public Optional<URL> changeToThePreviousSong() {
		Optional<URL> previousSong = this.getPreviousSong();
		// If the previous song there isn't I don't change the current song
		if (previousSong.isPresent()) {
			this.currentSong = previousSong;
			if (this.shuffleMode) {
				this.currentPlaylistIndex = Optional.of(shuffled
						.get(currShuffledIdx));
			} else {
				this.currentPlaylistIndex = Optional
						.of(this.currentPlaylistIndex.get() - 1);
			}
			return this.currentSong;
		}
		return Optional.empty();
	}

	@Override
	public Optional<URL> changeSong(final int index)
			throws IllegalArgumentException {
		// Controllo che index sia corretto
		if (index < 0 || index >= this.playList.size()) {
			throw new IllegalArgumentException();
		}
		this.currentSong = Optional.of(this.playList.get(index));
		this.currentPlaylistIndex = Optional.of(index);
		return this.currentSong;
	}

	@Override
	public void setShuffleMode(final boolean active) {
		this.shuffleMode = active;

		if (active) {
			// In questo modo creo una nuova lista, lasciando l'altra al Garbage
			// Collector
			shuffled = new ArrayList<Integer>();
		} else {
			currShuffledIdx = -1;
		}
	}

	@Override
	public boolean isShuffleModeActive() {
		return this.shuffleMode;
	}

	@Override
	public void setLoopMode(final boolean active) {
		this.loopMode = active;
	}

	@Override
	public boolean isLoopModeActive() {
		return this.loopMode;
	}

	@Override
	public void addSongToPlayList(final URL songPath)
			throws IllegalArgumentException {
		if (songPath == null) {
			throw new IllegalArgumentException();
		}
		// Se è la prima canzone nella playlist la imposto anche come canzone
		// corrente
		if (this.playList.size() == 0) {
			this.currentSong = Optional.of(songPath);
			this.currentPlaylistIndex = Optional.of(0);
		}
		this.playList.add(songPath);
	}

	@Override
	public void removeSongFromPlayList(final int index)
			throws IllegalArgumentException {
		this.playList.remove(index);
		// Se la canzone che ho rimosso è quella corrente modifico anche
		// currentSong e currentPlaylistIndex
		if (this.currentPlaylistIndex.get() == index) {
			this.currentSong = Optional.empty();
			this.currentPlaylistIndex = Optional.of(-1);
		}
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
