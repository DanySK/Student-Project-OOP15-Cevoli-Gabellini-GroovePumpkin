package view.panels;

import java.util.Optional;

import view.buttons.AbsStratBtn;
import controller.musicplayer.LoopableMusicPlayer;
import controller.musicplayer.MusicPlayer;
import controller.musicplayer.Shuffable;
import controller.Player;
import controller.groovebox.GrooveBoxPlayer;

public class CmdWrapper {
	
	private Optional<AbsStratBtn<Player>> play;
	private Optional<AbsStratBtn<Player>> stop;
	private Optional<AbsStratBtn<MusicPlayer>> fw;
	private Optional<AbsStratBtn<MusicPlayer>> bw;
	private Optional<AbsStratBtn<Shuffable>> shuffle;
	private Optional<AbsStratBtn<LoopableMusicPlayer>> loop;
	private Optional<AbsStratBtn<GrooveBoxPlayer>> save;
	private Optional<AbsStratBtn<MusicPlayer>> add;
	private Optional<AbsStratBtn<MusicPlayer>> remove;

	/**
	 * @return the play button associated to this panel
	 */
	public Optional<AbsStratBtn<Player>> getPlay() {
		return play;
	}

	/**
	 * @return the stop button associated with this panel
	 */
	public Optional<AbsStratBtn<Player>> getStop() {
		return stop;
	}

	/**
	 * @return the Loop button associated with this panel
	 */
	public Optional<AbsStratBtn<LoopableMusicPlayer>> getLoop() {
		return loop;
	}

	/**
	 * @return the backward button associated with this panel
	 */
	public Optional<AbsStratBtn<MusicPlayer>> getBW() {
		return bw;
	}

	/**
	 * @return the forward button associated with this panel
	 */
	public Optional<AbsStratBtn<MusicPlayer>> getFW() {
		return fw;
	}

	/**
	 * @return the shuffle button associated with this panel
	 */
	public Optional<AbsStratBtn<Shuffable>> getShuffle() {
		return shuffle;
	}

	/**
	 * @param return the save button
	 */
	public Optional<AbsStratBtn<GrooveBoxPlayer>> getSave() {
		return this.save;
	}

	/**
	 * @return the add button
	 */
	public Optional<AbsStratBtn<MusicPlayer>> getAdd() {
		return this.add;
	}

	/**
	 * @return the remove button
	 */
	public Optional<AbsStratBtn<MusicPlayer>> getRemove() {
		return this.remove;
	}
	
	/**
	 * Set a new play button
	 * 
	 * @param play
	 */
	public void setPlay(final AbsStratBtn<Player> b) {
	
		this.play = Optional.ofNullable(b);
	}

	/**
	 * Set a new stop button
	 * 
	 * @param stop
	 */
	public void setStop(final AbsStratBtn<Player> b) {
		
		this.stop = Optional.ofNullable(b);
	}

	/**
	 * Set a new loop button
	 * 
	 * @param loop
	 */
	public void setLoop(final AbsStratBtn<LoopableMusicPlayer> b) {
		this.loop = Optional.ofNullable(b);
	}

	/**
	 * set a new backward button
	 * 
	 * @param bw
	 */
	public void setBW(final AbsStratBtn<MusicPlayer> b) {
		this.bw = Optional.ofNullable(b);
	}

	/**
	 * set a new Forward button
	 * 
	 * @param fw
	 */
	public void setFW(final AbsStratBtn<MusicPlayer> b) {
		this.fw = Optional.ofNullable(b);
	}

	/**
	 * set a new shuffle button
	 * 
	 * @param shuffle
	 */
	public void setShuffle(final AbsStratBtn<Shuffable> b) {
		this.shuffle = Optional.ofNullable(b);
	}
	
	/**
	 * 
	 * @param save
	 */
	public void setSave(final AbsStratBtn<GrooveBoxPlayer> b){
		this.save= Optional.ofNullable(b);
	}
	
	/**
	 * Set a new Add button into the button row in the same place
	 * 
	 * @param newAdd
	 */
	public void setAdd(final AbsStratBtn<MusicPlayer> b){
		this.add= Optional.ofNullable(b);
	}
	
	
	/**
	 * Set a new remove button into the button row in the same place
	 * 
	 * @param newRemove
	 */
	public void setRemove(final AbsStratBtn<MusicPlayer> b){
		this.remove= Optional.ofNullable(b);
	}
}
