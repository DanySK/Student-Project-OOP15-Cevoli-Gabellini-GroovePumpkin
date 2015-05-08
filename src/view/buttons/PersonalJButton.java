package view.buttons;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import view.interfaces.Controllable;
import controller.Updatable;
import model.PlayerState;
import static view.config.Utility.*;
import static view.config.Configuration.*;

/**
 * A personal JButton class with modifed bg ad fg,
 * few helpful methods and able to communicate with a
 * given generic controller 
 * 
 * @author Alessandro
 * 
 * @param <C> the type of the attachable controller
 *
 */
public class PersonalJButton<C> extends JButton implements Updatable, Controllable<C>{

	private static final long serialVersionUID = -7937866815197131576L;
	private C controller;
	private boolean hasIcon;
	private boolean hasTitle;
	
	/**
	 * Basic constructor for the personal JButton
	 */
	protected PersonalJButton() {
		this.setBorder(getADefaultButtonBorder());
		
		this.setBackground(WHITE);
		this.setForeground(DARK_GRAY);
		this.setFocusPainted(false);
		
		this.setSize();
	}

	/**
	 * @param img to be applied to the button
	 */
	public PersonalJButton(final ImageIcon img) {
		this();
		this.setIcon(img);
		this.hasIcon=true;
	}

	/**
	 * @param title to be shown upon the button
	 * @param img to be applied to the button
	 */
	public PersonalJButton(final ImageIcon img, final String title) {
		this(img);
		this.setTitledBorder(title);
	}
	
	private void setSize() {
		
		if(this.hasIcon && !this.hasTitle){
			this.setPreferredSize(new Dimension(this.getIcon().getIconWidth(), 
					this.getIcon().getIconHeight()));
		} 
	}
	
	private TitledBorder getTitledBorder() {

		if (this.hasTitle){
			return ((TitledBorder) ((CompoundBorder) this.getBorder())
					.getOutsideBorder());
		}
		
		return null;
	}
	
	protected void doShow(final boolean b, final String t){
		if(b){
			this.setTitledBorder(t);
		}
	}
	
	/**
	 * This method change the previous title show by the 
	 * titledborder (if exist), otherways it do nothing
	 * 
	 * @param newTitle
	 */
	public void changeTitle(final String newTitle){
		if (this.hasTitle) {
			final TitledBorder tb= this.getTitledBorder();
			tb.setTitle(newTitle);
		}
	}
	
	@Override
	public C getController(){
		return controller;
	}
	
	@Override
	public void setController(final C controller){
		this.controller=controller;
	}
	
	/**
	 * Set a title border on this button
	 * 
	 * @param name
	 *            to be shown on the TitledBorder
	 */ 
	public void setTitledBorder(final String title) {
		this.setBorder(getACompoundTitledBorder(title));
		this.repaint();
		this.hasTitle=true;
	}
	
	/**
	 * Remove the title border displayed on this button
	 * 
	 */
	public void removeTitledBorder(){
		this.setBorder(getADefaultButtonBorder());
		this.setSize();
		this.repaint();
		this.hasTitle=false;
	}
	
	/**
	 * Override this method if you want to use it
	 */
	@Override
	public void updateStatus(final PlayerState status) {
		//Override this method
	}
}