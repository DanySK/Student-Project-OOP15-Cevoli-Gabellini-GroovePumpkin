package view.buttons;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import controller.Updatable;
import model.PlayerState;
import static view.config.Utility.*;

/**
 * A personal JButton class that stores all the imagines for the oject of this
 * class and the color of Background and Foreground; It manages also the border
 * of the button
 * 
 * NOTE: 
 * Avrei voluto implementare un pattern creazionale quale Factory o Strategy o Decorator
 * ma a livello di riuso del codice/uso della memoria (decorator) non mi convincevano:
 * l'unico che si presterebbe nel modo migliore sarebbe lo Strategy che se riesco implementerò 
 * anzichè l'ammucchiata di classi.
 * 
 * @author Alessandro
 *
 */
public class PersonalJButton extends JButton implements Updatable{

	private static final long serialVersionUID = -7937866815197131576L;
	private Object controller;
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

		if (this.getBorder() instanceof CompoundBorder
				&& ((CompoundBorder) this.getBorder())
						.getOutsideBorder() instanceof TitledBorder){
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
		final TitledBorder tb= this.getTitledBorder();
		if (tb != null) {
			tb.setTitle(newTitle);
		}
	}
	
	/**
	 * 
	 * @return the controller associated with this button
	 */
	public Object getController(){
		return controller;
	}
	
	/**
	 * 
	 * @return the controller associated with this button
	 */
	public void setController(final Object controller){
		this.controller=controller;
	}
	
	/**
	 * 
	 * @param name
	 *            to be shown on the TitledBorder
	 */ 
	public void setTitledBorder(final String title) {
		this.setBorder(getACompoundTitledBorder(title));
		this.repaint();
		this.hasTitle=true;
	}
	
	public void removeTitledBorder(){
		this.setBorder(getADefaultButtonBorder());
		this.setSize();
		this.repaint();
		this.hasTitle=false;
	}
	/**
	 * Override this method
	 * 
	 */
	@Override
	public void updateStatus(final PlayerState status) {
		//Override this method
	}
}