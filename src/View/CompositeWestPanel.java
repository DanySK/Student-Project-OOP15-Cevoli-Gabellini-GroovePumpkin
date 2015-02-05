package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/**
 * Personalized Panel for the PlayBackPanel class
 * @author Alessandro
 *
 */
public class CompositeWestPanel extends JPanel {

	private static final long serialVersionUID = 5045956389400601388L;
	private final JTable playlist = new JTable(50, 1);

	public CompositeWestPanel() {
		this.setLayout(new BorderLayout());
		
		playlist.setRowSelectionAllowed(true);
		playlist.setBackground(new Color(50, 50, 50));
		playlist.setForeground(new Color(255, 255, 255));
		
		final JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(playlist);
		jsp.setBackground(new Color(50, 50, 50));
		jsp.setForeground(new Color(255, 255, 255));
		
		final JPanel buttonRow = new JPanel(new FlowLayout());
		populateButtonRow(buttonRow);

		this.add(jsp, BorderLayout.CENTER);
		this.add(buttonRow, BorderLayout.SOUTH);
	}
	
	private void populateButtonRow(final JPanel buttonRow) {
		final JButton add= new JButton("Add");
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//aggiungi una canzone
			}
		});
		final JButton remove= new JButton("Remove");
		remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//rimuovi una canzone
			}
		});
		
		buttonRow.add(add);
		buttonRow.add(remove);
		buttonRow.setBackground(new Color(50, 50, 50));
		buttonRow.setForeground(new Color(255, 255, 255));
	}
}
