package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This Class is thiked to built a small PersonalizedJPanel
 * with a JButton and if wanted a JLabel
 * 
 * @author Alessandro
 *
 */

@SuppressWarnings("unused")
public abstract class AbstractCompositeJSpace extends PersonalJPanel {

	private static final long serialVersionUID = 4199364808400772243L;

	private JLabel label;
	private final PersonalJButton button = new PersonalJButton();

	/**
	 * @param layout to be applied
	 * @param img to be showed
	 */
	public AbstractCompositeJSpace(final LayoutManager layout,
			final ImageIcon img) {
		super(layout);
		this.button.setIcon(img);
		this.add(button, BorderLayout.CENTER);
	}

	/**
	 * @param layout to be applied at the JPanel
	 * @param name to be applied on the label
	 * @param img to be showed on the Icon
	 */
	public AbstractCompositeJSpace(final LayoutManager layout,
			final String name, final ImageIcon img) {
		
		this(layout, img);
		this.label= new JLabel(name);
		showLabel();
	}
	
	/**
	 * Directly set the Action Listener for the built-in button
	 * 
	 * @param buttonListener
	 */
	public void setButtonActionListener(final ActionListener buttonListener) {
		this.button.addActionListener(buttonListener);
	}
	
	/**
	 * 
	 * @return the built-in JButton
	 */
	public JButton getButton() {
		return this.button;
	}
	
	/**
	 * 
	 * @return the label if it's enabled
	 */
	public JLabel getLabel() {
		return label== null ? label : null;
		
	}

	public void showLabel() {
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setBackground(WHITE);
		label.setForeground(GRAY);
		this.add(label, BorderLayout.NORTH);
	}
}
