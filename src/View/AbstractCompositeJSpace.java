package View;

import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

/**
 * This Class is thiked to built a small PersonalizedJPanel with a JButton and
 * if wanted a JLabel
 * 
 * @author Alessandro
 *
 */

public abstract class AbstractCompositeJSpace extends PersonalJButton {

	private static final long serialVersionUID = 4199364808400772243L;

	private CompoundBorder border;
	
	/**
	 * @param layout
	 *            to be applied
	 * @param img
	 *            to be showed
	 */
	public AbstractCompositeJSpace(final ImageIcon img) {
		this.setIcon(img);
	}

	protected void showTitle(final String name) {
		this.border= (CompoundBorder) PersonalJButton.getCompoundTitledBorder(name);
		this.setBorder(border);
	}
	
	/**
	 * 
	 * @return the label if it's enabled, otherways null
	 */
	public TitledBorder getTitledBorder() {
		return border!= null ? (TitledBorder) border.getOutsideBorder() : null;
	}
	
	/**
	 * 
	 * @return null if border is inactive, otherways the title of the border
	 */
	public String getTitle(){
		if(border!=null){
			return ((TitledBorder) border.getOutsideBorder()).getTitle();
		}
		
		return null;
	}
}
