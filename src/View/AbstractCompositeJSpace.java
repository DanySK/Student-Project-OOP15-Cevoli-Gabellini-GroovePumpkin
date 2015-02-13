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
 * 
 * @author Alessandro
 *
 */

@SuppressWarnings("unused")
public abstract class AbstractCompositeJSpace extends PersonalJPanel {

	private static final long serialVersionUID = 4199364808400772243L;

	private final JLabel label = new JLabel();
	private final PersonalJButton button = new PersonalJButton();

	/**
	 * @param layout
	 * @param img
	 */
	public AbstractCompositeJSpace(final LayoutManager layout,
			final ImageIcon img) {
		super(layout);
		this.button.setIcon(img);
		this.add(button, BorderLayout.CENTER);
	}

	/**
	 * @param layout
	 * @param name
	 * @param img
	 */
	public AbstractCompositeJSpace(final LayoutManager layout,
			final String name, final ImageIcon img) {
		
		this(layout, img);
		this.label.setText(name);
		showLabel();
	}

	public void setButtonActionListener(final ActionListener buttonListener) {
		this.button.addActionListener(buttonListener);
	}

	public JButton getButton() {
		return this.button;
	}

	public JLabel getLabel() {
		return label;
	}

	public void showLabel() {
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setBackground(WHITE);
		label.setForeground(GRAY);
		this.add(label, BorderLayout.NORTH);
	}
}
