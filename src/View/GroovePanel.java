package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
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
 * This class rapresent the GUI space for the groovebox
 * 
 * @author Alessandro
 *
 */

public class GroovePanel extends PersonalJPanel {

	private static final long serialVersionUID = 1116768170189928089L;

	private final Double[] items = new Double[] { 40d, 60d, 80d, 100d, 120d,
			140d, 160d, 180d };

	private int row;
	private int col;

	private final JComboBox<Double> timeDialerOptions = new JComboBox<>(items);
	private final TableModel tableModel = new GrooveTableModel();
	private final JTable grooveBox = new JTable(tableModel);

	public GroovePanel() {
		super(new BorderLayout(5, 5));

		timeDialerOptions.setBackground(WHITE);
		timeDialerOptions.setForeground(GRAY);

		final PersonalJPanel westPanel = new PersonalJPanel(new BorderLayout(5,
				5));
		westPanel.setBuiltInBorder();

		populateWestPanel(westPanel);
		this.add(westPanel, BorderLayout.WEST);

		this.add(populateGrooveBox(), BorderLayout.CENTER);
	}

	// Maybe this 'll got a own class, for cutting code line
	private JScrollPane populateGrooveBox() {

		grooveBox.getColumn(GrooveTableModel.GrooveTimeValues[0]).setMinWidth(
				120);

		// Thank you STACKOVERFLOW <3
		grooveBox.getTableHeader().setReorderingAllowed(false);
		grooveBox.getTableHeader().setResizingAllowed(false);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {

			private static final long serialVersionUID = -5252083106955434257L;

			public void setValue(Object value) {
				setBackground(Color.WHITE);
			}
		};

		for (int i = 1; i < GrooveTableModel.GrooveTimeValues.length; i++) {
			grooveBox.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}

		grooveBox.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						ListSelectionModel sm = (ListSelectionModel) e
								.getSource();

						if (!sm.isSelectionEmpty()) {
							row = sm.getMinSelectionIndex();
						}
					}
				});

		grooveBox.getColumnModel().getSelectionModel()
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						ListSelectionModel sm = (ListSelectionModel) e
								.getSource();

						if (!sm.isSelectionEmpty()) {
							col = sm.getMinSelectionIndex();
						}

						if (col != 0) {
							Color c = GrooveValues.values()[row]
									.getColor(col - 1);
							if (c.equals(Color.WHITE)) {
								GrooveValues.values()[row].setColor(
										GrooveValues.getRandomColor(), col - 1);
							} else {
								GrooveValues.values()[row].setColor(
										Color.WHITE, col - 1);
							}
						}

						grooveBox.tableChanged(new TableModelEvent(tableModel));
					}
				});

		return new JScrollPane(grooveBox);
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
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		final PlayAndPauseSpace play = new PlayAndPauseSpace(PersonalJButton.PLAY_IMG);
		play.setBorder(PersonalJButton.getCompoundTitledBorder("Play"));
		
		play.addActionListener(new PlayAndPauseSpace.PlayListener(play));		
		
		final PersonalJButton loop = new PersonalJButton(
				PersonalJButton.LOOP_OFF_IMG, "off");
		loop.setBorder(PersonalJButton.getCompoundTitledBorder("Loop"));
		
		loop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (loop.getID().equals("off")) {
					loop.setID("on");
					loop.setIcon(PersonalJButton.LOOP_ON_IMG);
					// Loop the groove
				} else {
					loop.setID("off");
					loop.setIcon(PersonalJButton.LOOP_OFF_IMG);
					// UnLoop the groove
				}
			}
		});

		final SaveSpace save = new SaveSpace(true);
		
		buttonPanel.add(play);
		buttonPanel.add(loop);
		buttonPanel.add(save);

		westPanel.add(buttonPanel, BorderLayout.CENTER);
	}

	/**
	 * @return The time dial in beat for minute
	 */
	public Double getDial() {
		return (Double) timeDialerOptions.getSelectedItem();
	}
}
