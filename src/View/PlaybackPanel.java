package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

@SuppressWarnings("unused")
public class PlaybackPanel extends JPanel {

	private static final long serialVersionUID = -2515046442887709479L;

	public PlaybackPanel() {
		this.setLayout(new BorderLayout());

		final JPanel jukePanel = new JPanel(new BorderLayout());
		populateJukePanel(jukePanel);

		final JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				new CompositeWestPanel(), jukePanel);

		this.add(splitter);
	}

	private void populateJukePanel(final JPanel jukePanel) {

	}

}
