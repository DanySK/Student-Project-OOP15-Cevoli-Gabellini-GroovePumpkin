package view.panels;

import java.util.Optional;

import view.buttons.AbstractStratBtn;
import controller.musicplayer.MusicPlayer;
import controller.musicplayer.PlaylistFeatureCommand;
import controller.Loopable;
import controller.Player;
import controller.groovebox.GrooveBoxPlayer;

/**
 * A class that wraps the functional buttons
 * used by a Command Pane
 * 
 * @author Alessandro
 *
 */
public class CmdWrapper {
	
	private Optional<AbstractStratBtn<Player>> play;
	private Optional<AbstractStratBtn<Player>> stop;
	private Optional<AbstractStratBtn<MusicPlayer>> fw;
	private Optional<AbstractStratBtn<MusicPlayer>> bw;
	private Optional<AbstractStratBtn<PlaylistFeatureCommand>> shuffle;
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
	public Optional<AbstractStratBtn<PlaylistFeatureCommand>> getShuffle() {
		return shuffle;
	}

	/**
	 * @return the save button
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
	 * @param b
	 */
	public void setPlay(final AbstractStratBtn<Player> b) {
	
		this.play = Optional.ofNullable(b);
	}

	/**
	 * Set a stop button
	 * 
	 * @param b
	 */
	public void setStop(final AbstractStratBtn<Player> b) {
		
		this.stop = Optional.ofNullable(b);
	}

	/**
	 * Set a loop button
	 * 
	 * @param b
	 */
	public void setLoop(final AbstractStratBtn<Loopable> b) {
		this.loop = Optional.ofNullable(b);
	}

	/**
	 * set a backward button
	 * 
	 * @param b
	 */
	public void setBW(final AbstractStratBtn<MusicPlayer> b) {
		this.bw = Optional.ofNullable(b);
	}

	/**
	 * set a Forward button
	 * 
	 * @param b
	 */
	public void setFW(final AbstractStratBtn<MusicPlayer> b) {
		this.fw = Optional.ofNullable(b);
	}

	/**
	 * set a shuffle button
	 * 
	 * @param b
	 */
	public void setShuffle(final AbstractStratBtn<PlaylistFeatureCommand> b) {
		this.shuffle = Optional.ofNullable(b);
	}
	
	/**
	 * Set a Save button
	 * 
	 * @param b
	 */
	public void setSave(final AbstractStratBtn<GrooveBoxPlayer> b){
		this.save= Optional.ofNullable(b);
	}
	
	/**
	 * Set Add button into the button row
	 * 
	 * @param b
	 */
	public void setAdd(final AbstractStratBtn<MusicPlayer> b){
		this.add= Optional.ofNullable(b);
	}
	
	
	/**
	 * Set remove button into the button row
	 * 
	 * @param b
	 */
	public void setRemove(final AbstractStratBtn<MusicPlayer> b){
		this.remove= Optional.ofNullable(b);
	}

	/**
	 * Set reset button into the button row
	 * 
	 * @param b
	 */
	public void setReset(final AbstractStratBtn<GrooveBoxPlayer> b) {
		this.reset=Optional.ofNullable(b);
	}
}
