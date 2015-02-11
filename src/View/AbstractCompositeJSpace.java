package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;

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

	public AbstractCompositeJSpace() {
		super(new BorderLayout());
		this.add(label, BorderLayout.NORTH);
		this.add(button, BorderLayout.CENTER);
	}
	
	public AbstractCompositeJSpace(final String name, final ImageIcon img){
		this();
		this.button.setIcon(img);
		this.label.setText(name);
	}

	protected JButton getButton() {
		return this.button;
	}

	protected JLabel getLabel() {
		return label;
	}
}
