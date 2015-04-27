package view.panels;

import java.awt.LayoutManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import model.PlayerState;
import view.interfaces.Updatable;
import controller.MusicPlayer;
import controller.Player;
import static model.PlayerState.PAUSED;
import static model.PlayerState.RUNNING;
import static view.config.Utility.*;

/**
 * A Personalized White&DarkGrey JPanel, 
 * with a personalizable Layout
 * 
 * @author Alessandro
 *
 */

public class PersonalJPanel extends JPanel implements Updatable{

	private static final long serialVersionUID = -7098020132793828090L;
	
	private KeyAdapter keyAdp;
	private Player controller;
	private final List<Updatable> observers= new ArrayList<>();
	
	/**
	 * A basic constructor for a White&Gray panel
	 * 
	 */
	public PersonalJPanel() {
		super();
		this.setBackground(WHITE);
		this.setForeground(DARK_GRAY);
		
		for(final KeyListener kl : this.getKeyListeners()){
			this.removeKeyListener(kl);
		}
	}
	
	/**
	 * A basic constructor for a White&Gray panel
	 * with the given layout
	 * 
	 * @param layout
	 */
	public PersonalJPanel(final LayoutManager layout) {
		this();
		this.setLayout(layout);
	}
	
	/**
	 * Add a new Observer for this object
	 * 
	 * @param up
	 */
	public void addObserver(final Updatable up){
		observers.add(up);
	}
	
	/**
	 * Remove an Observer for this object
	 * 
	 * @param up
	 */
	public void removeObserver(final Updatable up){
		observers.remove(up);
	}
	
	/**
	 * Get all the Observers of this object
	 * 
	 * @return
	 */
	public List<Updatable> getObservers(){
		return observers;
	}
	
	/**
	 * This method allow to get the controller attached to this 
	 * panel
	 * 
	 * @return
	 */
	public Player getController(){
		return this.controller;
	}
	
	/**
	 * Return a play adapter for this class.
	 * Bind the SPACE bar so that it will play/pause
	 * the running song
	 * 
	 * @return
	 */
	public KeyAdapter getPlayAdapter(){
		if(keyAdp==null && controller!= null){
			keyAdp=new KeyAdapter() {
				@Override
				public void keyPressed(final KeyEvent e) {
					if(e.getKeyCode()== KeyEvent.VK_SPACE
							&& controller instanceof MusicPlayer){
						if(((MusicPlayer)controller).getState().equals(RUNNING)){
							controller.pause();
						} else if(((MusicPlayer)controller).getState().equals(PAUSED)){
							controller.play();
						}
					}
				}
			};
		}
		return keyAdp;
	}
	
	/**
	 * This method allow the attachement of a Player type controller
	 * to this panel
	 * 
	 * @param controller
	 */
	public void setController(final Player controller){
		this.controller=controller;
	}
	
	/**
	 * This method set up an already choosen border 
	 * (CompoundBorder decorated with ad EtchedBorder)
	 */
	public void setBuiltInBorder(){
		this.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), 
				new EmptyBorder(3, 3, 3, 3)));
	}

	@Override
	public void updateStatus(final PlayerState status) {
		observers.stream().forEach(u->u.updateStatus(status));
	}
	
}
