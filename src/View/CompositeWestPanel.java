package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableModel;

import Model.PlaylistTableModel;

/**
 * Personalized Panel for the PlayBackPanel class, 
 * this class "haldles" the playlist,
 * adding or removing song from it.
 * 
 * @author Alessandro
 *
 */
public class CompositeWestPanel extends PersonalJPanel {

	private static final long serialVersionUID = 5045956389400601388L;

	/**
	 * FILE deve essere esteso o wrappato da una classe SONG, che permetta una
	 * migliore interazione con la JTable
	 */

	private final List<File> songs = new ArrayList<>(50);

	/**
	 * Creation of an anonymous class as a model for the JTable class
	 * 
	 */
	private final TableModel dataModel = new PlaylistTableModel(songs);

	private final JTable playlist = new JTable(dataModel);

	public CompositeWestPanel() {
		super(new BorderLayout());
		this.setBuiltInBorder();
		final JScrollPane jsp = new JScrollPane();
		this.populateJSP(jsp);

		final PersonalJPanel buttonRow = new PersonalJPanel(new FlowLayout());
		this.populateButtonRow(buttonRow);

		this.add(jsp, BorderLayout.CENTER);
		this.add(buttonRow, BorderLayout.SOUTH);
	}

	private void populateJSP(JScrollPane jsp) {
		playlist.setBackground(WHITE);
		playlist.setForeground(GRAY);
		playlist.setRowSelectionAllowed(true);
		// playlist.getColumn("Song").setResizable(false);
		// jsp.setBorder(new CompoundBorder(new
		// EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(3, 3, 3, 3)));
		jsp.setViewportView(playlist);
		jsp.setBackground(WHITE);
		jsp.setForeground(GRAY);
	}

	private void populateButtonRow(final PersonalJPanel buttonRow) {
		final PersonalJButton add = new PersonalJButton("Add");

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// aggiungi una canzone

				// NOTA IL CODICE SEGUENTE DEVE ANDARE NEL MODEL
				JFileChooser chooser = new JFileChooser(System
						.getProperty("user.home"));

				/*
				 * I've built an anonymous class for the file filter, it'll
				 * problably go as a properly stand-alone class
				 */
				chooser.addChoosableFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						return "*mp3, *.midi, *.wav, *.aac";
					}

					@Override
					public boolean accept(File f) {

						if (f.isDirectory()) {
							return true;
						}

						if (f.getName().endsWith(".midi")
								|| f.getName().endsWith(".wav")
								|| f.getName().endsWith(".aac")
								|| f.getName().endsWith(".mp3")) {
							return true;
						}

						return false;
					}
				});

				chooser.setVisible(true);
				int val = chooser.showOpenDialog(CompositeWestPanel.this);
				if (val == JFileChooser.APPROVE_OPTION) {
					final File f = chooser.getSelectedFile();
					songs.add(f);
					System.out.println(f.getName());
				} else if (val != JFileChooser.CANCEL_OPTION) {
					JOptionPane.showMessageDialog(CompositeWestPanel.this,
							"An Error has occurred", "Error Message",
							JOptionPane.ERROR_MESSAGE);
				}

				// ricrea la nuova tabella
				playlist.tableChanged(new TableModelEvent(dataModel));
			}

		});

		final PersonalJButton remove = new PersonalJButton("Remove");

		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// rimuovi una canzone

				try {
					songs.remove(playlist.getSelectedRow());
					playlist.tableChanged(new TableModelEvent(dataModel));
				} catch (Exception ex) {
					// do nothing
				}
			}
		});

		buttonRow.add(add);
		buttonRow.add(remove);
	}
}
