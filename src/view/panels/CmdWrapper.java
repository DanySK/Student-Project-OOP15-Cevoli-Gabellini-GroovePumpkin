package view.panels;

import java.util.Optional;

import view.buttons.AbstractStratBtn;
import controller.musicplayer.MusicPlayer;
import controller.musicplayer.Shuffable;
import controller.Loopable;
import controller.Player;
import controller.groovebox.GrooveBoxPlayer;

public class CmdWrapper {
	
	private Optional<AbstractStratBtn<Player>> play;
	private Optional<AbstractStratBtn<Player>> stop;
	private Optional<AbstractStratBtn<MusicPlayer>> fw;
	private Optional<AbstractStratBtn<MusicPlayer>> bw;
	private Optional<AbstractStratBtn<Shuffable>> shuffle;
	private Optional<AbstractStratBtn<Loopable>> loop;
	private Optional<AbstractStratBtn<GrooveBoxPlayer>> save;
	private Optional<AbstractStratBtn<MusicPlayer>> add;
	private Optional<AbstractStratBtn<MusicPlayer>> remove;
	private Optional<AbstractStratBtn<GrooveBoxPlayer>> reset;

	/**
	 * @return the play button
	 */
	public Optional<AbstractStratBtn<Player>> getPlay() {
		return play;
	}

	/**
	 * @return the stop button
	 */
	public Optional<AbstractStratBtn<Player>> getStop() {
		return stop;
	}

	/**
	 * @return the Loop button
	 */
	public Optional<AbstractStratBtn<Loopable>> getLoop() {
		return loop;
	}

	/**
	 * @return the backward button
	 */
	public Optional<AbstractStratBtn<MusicPlayer>> getBW() {
		return bw;
	}

	/**
	 * @return the forward button
	 */
	public Optional<AbstractStratBtn<MusicPlayer>> getFW() {
		return fw;
	}

	/**
	 * @return the shuffle button
	 */
	public Optional<AbstractStratBtn<Shuffable>> getShuffle() {
		return shuffle;
	}

	/**
	 * @param return the save button
	 */
	public Optional<AbstractStratBtn<GrooveBoxPlayer>> getSave() {
		return this.save;
	}

	/**
	 * @return the add button
	 */
	public Optional<AbstractStratBtn<MusicPlayer>> getAdd() {
		return this.add;
	}

	/**
	 * @return the remove button
	 */
	public Optional<AbstractStratBtn<MusicPlayer>> getRemove() {
		return this.remove;
	}
	
	/**
	 * @return the reset button
	 */
	public Optional<AbstractStratBtn<GrooveBoxPlayer>> getReset() {
		return this.reset;
	}
	
	/**
	 * Set a play button
	 * 
	 * @param play
	 */
	public void setPlay(final AbstractStratBtn<Player> b) {
	
		this.play = Optional.ofNullable(b);
	}

	/**
	 * Set a stop button
	 * 
	 * @param stop
	 */
	public void setStop(final AbstractStratBtn<Player> b) {
		
		this.stop = Optional.ofNullable(b);
	}

	/**
	 * Set a loop button
	 * 
	 * @param loop
	 */
	public void setLoop(final AbstractStratBtn<Loopable> b) {
		this.loop = Optional.ofNullable(b);
	}

	/**
	 * set a backward button
	 * 
	 * @param bw
	 */
	public void setBW(final AbstractStratBtn<MusicPlayer> b) {
		this.bw = Optional.ofNullable(b);
	}

	/**
	 * set a Forward button
	 * 
	 * @param fw
	 */
	public void setFW(final AbstractStratBtn<MusicPlayer> b) {
		this.fw = Optional.ofNullable(b);
	}

	/**
	 * set a shuffle button
	 * 
	 * @param shuffle
	 */
	public void setShuffle(final AbstractStratBtn<Shuffable> b) {
		this.shuffle = Optional.ofNullable(b);
	}
	
	/**
	 * Set a Save button
	 * 
	 * @param save
	 */
	public void setSave(final AbstractStratBtn<GrooveBoxPlayer> b){
		this.save= Optional.ofNullable(b);
	}
	
	/**
	 * Set Add button into the button row
	 * 
	 * @param newAdd
	 */
	public void setAdd(final AbstractStratBtn<MusicPlayer> b){
		this.add= Optional.ofNullable(b);
	}
	
	
	/**
	 * Set remove button into the button row
	 * 
	 * @param newRemove
	 */
	public void setRemove(final AbstractStratBtn<MusicPlayer> b){
		this.remove= Optional.ofNullable(b);
	}

	/**
	 * Set reset button into the button row
	 * 
	 * @param newRemove
	 */
	public void setReset(final AbstractStratBtn<GrooveBoxPlayer> b) {
		this.reset=Optional.ofNullable(b);
	}
}
