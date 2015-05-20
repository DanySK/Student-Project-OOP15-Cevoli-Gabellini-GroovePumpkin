package model.playlistmanager;

import java.util.*;

import model.playlistmanager.choicestrategy.PlaylistChoiceStrategy;

/**
 * This class represents a basic playlist manager.
 * the classic strategy is the default strategy used for select song from the playlist 
 * 
 * @see PlaylistManager
 * @author Matteo Gabellini
 */
public  class BasicPlaylistManager<X> implements PlaylistManager<X>{
	private PlaylistChoiceStrategy<X> extractionStrategy;
	private final List<X> playlist;
	private Optional<X> currentSong;


	public BasicPlaylistManager(final PlaylistChoiceStrategy<X> extractionStrategy) {
		this.playlist = new ArrayList<>();
		this.currentSong = Optional.empty();
		this.extractionStrategy = extractionStrategy;
	}
	
	@Override
	public Optional<Integer> getCurrentSongIndex(){
		return this.extractionStrategy.getCurrentSongIndex();
	}

	@Override
	public Optional<X> getCurretSong() {
		return this.currentSong.isPresent()? Optional.of(this.currentSong.get()) : Optional.empty();
	}
	

	@Override
	public Optional<X> changeToTheNextSong() {
		if (!this.extractionStrategy.getCurrentSongIndex().isPresent()) {
			// if the song wasn't selected I return a empty Optional
			return Optional.empty();
		}
		
		final Optional<Integer> nextSong = this.extractionStrategy.getNextSong(this.playlist);
		// If the previous song there isn't I don't change the current song
		if (nextSong.isPresent()) {
			this.currentSong = Optional.of(this.playlist.get(nextSong.get()));
			return this.currentSong;
		} else {
			return Optional.empty(); 
		}
		
	}

	@Override
	public Optional<X> changeToThePreviousSong() {
		if (!this.extractionStrategy.getCurrentSongIndex().isPresent()) {
			// if the song wasn't selected I return a empty Optional
			return Optional.empty();
		}
		
		final Optional<Integer> previousSong = this.extractionStrategy.getPreviousSong(this.playlist);
		// If the previous song there isn't I don't change the current song
		if (previousSong.isPresent()) {
			this.currentSong = Optional.of(this.playlist.get(previousSong.get()));
			return this.currentSong;
		} else {
			return Optional.empty(); 
		}
	}

	@Override
	public Optional<X> changeSong(final int index)
			throws IllegalArgumentException {
		// Controllo che index sia corretto
		if (index < 0 || index >= this.playlist.size()) {
			throw new IllegalArgumentException();
		}
		this.extractionStrategy.goToSong(index, playlist);
		this.currentSong = Optional.of(this.playlist
				.get(index));
		return this.currentSong;
	}

	@Override
	public void addSongToPlayList(final X songPath)
			throws IllegalArgumentException {
		if (songPath == null) {
			throw new IllegalArgumentException();
		}
		
		this.playlist.add(songPath);
		// Se è la prima canzone nella playlist la imposto anche come canzone
		// corrente
		if (this.playlist.size() == 1) {
			this.changeSong(0);
		}
		
	}

	@Override
	public void removeSongFromPlayList(final int index)
			throws IllegalArgumentException {
		if (index >= 0 && index < this.playlist.size()) {
			this.playlist.remove(index);
			// If the song that i removed is the current song
			if (this.getCurrentSongIndex().isPresent()
					&& this.getCurrentSongIndex().get() == index) {
				// Update the currentSong variable
				this.currentSong = Optional.empty();
			}
			// Now update the datastructure of the strategy
			this.extractionStrategy.removedIndex(index);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void loadPlayList(final List<X> playList)
			throws IllegalArgumentException {
		
		if (playList == null) {
			throw new IllegalArgumentException();
		}
		this.playlist.addAll(playList);
	}

	@Override
	public List<X> getPlayList() {
		// return a defense copy of the playlist
		return new ArrayList<>(this.playlist);
	}

	/**
	 * Set another strategy to chose songs from the playlist
	 * @param strategy
	 */
	public void setChoiceStrategy(PlaylistChoiceStrategy<X> strategy){
		if(strategy != null){
			if (this.extractionStrategy.getCurrentSongIndex().isPresent()) {
				strategy.goToSong(this.extractionStrategy.getCurrentSongIndex().get(),
						this.playlist);
			}
			this.extractionStrategy = strategy;
		}
	}
	
}
