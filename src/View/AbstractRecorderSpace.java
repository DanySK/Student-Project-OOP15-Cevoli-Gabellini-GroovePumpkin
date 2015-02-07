package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("unused")
public abstract class AbstractRecorderSpace extends PersonalJPanel {

	private static final long serialVersionUID = 4199364808400772243L;

	protected static final ImageIcon saveImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Save.png");
	protected static final ImageIcon recImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Rec.png");
	protected static final ImageIcon stopImg = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Stop.png");

	private final JLabel label = new JLabel();
	private final JButton button = new JButton();

	protected AbstractRecorderSpace(final LayoutManager layout) {
		super(layout);
		this.add(label, BorderLayout.NORTH);
		button.setBackground(WHITE);
		this.add(button, BorderLayout.CENTER);
	}

	protected JButton getButton() {
		return this.button;
	}

	protected JLabel getLabel() {
		return label;
	}
}
