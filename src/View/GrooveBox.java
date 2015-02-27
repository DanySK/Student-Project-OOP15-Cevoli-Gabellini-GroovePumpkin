package View;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import Model.BetaGrooveValues;
import Model.GrooveTableModel;

/**
 * The Class that implements the groovebox
 * 
 * @author Alessandro
 *
 */

public class GrooveBox extends JTable {

	private static final long serialVersionUID = -7907789613027061207L;
	private GrooveTableModel tableModel;

	private int row = 0;
	private int col = 0;

	public GrooveBox(final TableModel tm) {
		super(tm);
		tableModel = (GrooveTableModel) tm;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return col;
	}

	public void setRow(final int row) {
		this.row = row;
	}

	public void setColumn(final int col) {
		this.col = col;
	}

	public void initGrooveBox() {
		this.getColumn(BetaGrooveValues.GROOVE_TIME_VALUES[0]).setMinWidth(120);

		// Thank you STACKOVERFLOW <3
		this.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setResizingAllowed(false);
		this.getTableHeader().setBackground(PersonalJPanel.GRAY);
		this.getTableHeader().setForeground(Color.WHITE);
		this.getTableHeader().setBorder(
				new CompoundBorder(new SoftBevelBorder(SoftBevelBorder.RAISED),
						new EmptyBorder(5, 5, 5, 5)));

		this.setGridColor(PersonalJPanel.GRAY);
		this.setForeground(Color.RED);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {

			private static final long serialVersionUID = -5252083106955434257L;

			public void setValue(Object value) {
				setBackground((Color) value);
			}
		};

		for (int i = 1; i < BetaGrooveValues.GROOVE_TIME_VALUES.length; i++) {
			this.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}

		this.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						ListSelectionModel sm = (ListSelectionModel) e
								.getSource();

						if (!sm.isSelectionEmpty()) {
							GrooveBox.this.setRow(sm.getMinSelectionIndex());
						}
					}
				});

		this.getColumnModel().getSelectionModel()
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						ListSelectionModel sm = (ListSelectionModel) e
								.getSource();

						if (!sm.isSelectionEmpty()) {
							GrooveBox.this.setColumn(sm.getMinSelectionIndex());
						}

						if (GrooveBox.this.getColumn() != 0) {
							Color c = tableModel.getList()
									.get(GrooveBox.this.getRow())
									.getColor(GrooveBox.this.getColumn() - 1);
							if (c.equals(Color.WHITE)) {
								tableModel.getList()
										.get(GrooveBox.this.getRow())
										.setColor(BetaGrooveValues.getRandomColor(),
												GrooveBox.this.getColumn() - 1);
							} else {
								tableModel.getList()
										.get(GrooveBox.this.getRow())
										.setColor(Color.WHITE,
												GrooveBox.this.getColumn() - 1);
							}
						}

						GrooveBox.this.tableChanged(new TableModelEvent(
								tableModel));
					}
				});

	}
}
