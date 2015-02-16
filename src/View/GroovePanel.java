package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import Model.GrooveTableModel;
import Model.GrooveValues;

/**
 * 
 * @author Alessandro
 *
 */

public class GroovePanel extends PersonalJPanel {

	private static final long serialVersionUID = 1116768170189928089L;

	private final Double[] items = new Double[] { 40d, 60d, 80d, 100d, 120d,
			140d, 160d, 180d };
	int row;
	int col;

	private final JComboBox<Double> timeDialerOptions = new JComboBox<>(items);
	private final TableModel tableModel = new GrooveTableModel();
	private final JTable grooveBox = new JTable(tableModel);

	public GroovePanel() {
		super(new BorderLayout());
		timeDialerOptions.setBackground(WHITE);
		timeDialerOptions.setForeground(GRAY);
		final PersonalJPanel westPanel = new PersonalJPanel(new BorderLayout(5,
				5));
		populateWestPanel(westPanel);
		this.add(westPanel, BorderLayout.WEST);

		final PersonalJPanel eastPanel = new PersonalJPanel(new BorderLayout(5,
				5));
		populateEastPanel(eastPanel);

		this.add(new JScrollPane(eastPanel), BorderLayout.CENTER);
	}

	private void populateEastPanel(final PersonalJPanel eastPanel) {

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {

			private static final long serialVersionUID = -5252083106955434257L;

			public void setValue(Object value) {
				setBackground(Color.WHITE);
			}
		};

		for (int i = 1; i < GrooveTableModel.GrooveTimeValues.length; i++) {
			grooveBox.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}

		// Listener for row changes
		grooveBox.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						ListSelectionModel sm = (ListSelectionModel) e
								.getSource();

						if (!sm.isSelectionEmpty()) {
							row = sm.getMinSelectionIndex();
						}
						
						grooveBox.tableChanged(new TableModelEvent(
								tableModel));
					}
				});

		// Listener for column changes
		grooveBox.getColumnModel().getSelectionModel()
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						ListSelectionModel sm = (ListSelectionModel) e
								.getSource();

						if (!sm.isSelectionEmpty()) {
							col = sm.getMinSelectionIndex();
						}
						
						if (col != 0) {
							Color c = GrooveValues.values()[row].getColor();
							if (c.equals(Color.WHITE)) {
								GrooveValues.values()[row]
										.setColor(Color.DARK_GRAY);
							} else {
								GrooveValues.values()[row]
										.setColor(Color.WHITE);
							}
						}
						
						grooveBox.tableChanged(new TableModelEvent(
								tableModel));
					}
				});

		eastPanel.add(grooveBox, BorderLayout.CENTER);
	}

	private void populateWestPanel(final PersonalJPanel westPanel) {

		final JLabel timeDialerLabel = new JLabel("Time Dial: ");
		timeDialerLabel.setBackground(WHITE);
		timeDialerLabel.setForeground(GRAY);

		final PersonalJPanel timePanel = new PersonalJPanel(new FlowLayout(
				FlowLayout.CENTER, 5, 10));
		timePanel.add(timeDialerLabel);
		timePanel.add(timeDialerOptions);
		westPanel.add(timePanel, BorderLayout.NORTH);

		final PersonalJPanel buttonPanel = new PersonalJPanel();
		buttonPanel.setLayout(new GridLayout(4, 1, 3, 3));

		final PlayAndPauseSpace play = new PlayAndPauseSpace(new FlowLayout(),
				true);

		final PersonalJPanel loopPanel = new PersonalJPanel(new FlowLayout(
				FlowLayout.CENTER, 3, 3));
		final PersonalJButton loop = new PersonalJButton(
				PersonalJButton.LOOP_OFF_IMG, "off");
		loop.setAlignmentX(CENTER_ALIGNMENT);
		loop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (loop.getName().equals("off")) {
					loop.setName("on");
					loop.setIcon(PersonalJButton.LOOP_ON_IMG);
					// Loop the groove
				} else {
					loop.setName("off");
					loop.setIcon(PersonalJButton.LOOP_OFF_IMG);
					// UnLoop the groove
				}
			}
		});

		loopPanel.add(loop);
		buttonPanel.add(play);
		buttonPanel.add(loopPanel);

		westPanel.add(buttonPanel, BorderLayout.CENTER);
	}

	public Double getDial() {
		return (Double) timeDialerOptions.getSelectedItem();
	}
}
