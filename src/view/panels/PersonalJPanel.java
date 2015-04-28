package view.panels;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.PlayerState;
import view.interfaces.Updatable;
import controller.Player;
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
	 * Add new components for this object
	 * 
	 * @param cmp
	 */
	public void addComponents(final Component ...cmp){
		for(Component c : cmp){
			this.add(c);
		}
	}
	
	/**
	 * Add new Observers for this object
	 * 
	 * @param up
	 */
	public void addObservers(final Updatable ...up){
		for(Updatable u : up){
			observers.add(u);
		}
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
	 * This method allow the attachement of a Player type controller
	 * to this panel
	 * 
	 * @param controller
	 */
	public void setController(final Player controller){
		this.controller=controller;
	}

	@Override
	public void updateStatus(final PlayerState status) {
		observers.stream().forEach(u->u.updateStatus(status));
	}
	
}
