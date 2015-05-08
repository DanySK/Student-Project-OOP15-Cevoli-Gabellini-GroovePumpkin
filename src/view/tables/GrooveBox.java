package view.tables;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import controller.Updatable;
import view.viewModel.AbstractMouseListener;
import view.viewModel.GrooveTableModel;
import static javax.swing.ListSelectionModel.*;
import static view.config.Configuration.*;

/**
 * The Class that implements the groovebox
 * 
 * @author Alessandro
 *
 */
public class GrooveBox extends PersonalJTable implements Updatable{

	private static final long serialVersionUID = -7907789613027061207L;
	private final TableModel tableModel;
	
	private boolean locked;
	private int row;
	private int col;

	/**
	 * 
	 * @param tm
	 */
	public GrooveBox(final TableModel tm) {
		super(tm, SINGLE_SELECTION);
		tableModel = tm;
		
		super.setColumnHeaderBounds(0, 120, 120);
		for(int i=1; i< GrooveTableModel.GROOVE_TIME_VALUES.length; i++){
			super.setColumnHeaderBounds(i, 40, 40);
		}
		this.setAutoResizeMode(0);
		this.setColumnSelectionAllowed(false);
		this.setRowSelectionAllowed(false);
		
		final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {

			private static final long serialVersionUID = -5252083106955434257L;

			public void setValue(final Object value) {
				if(value instanceof Color){
					this.setBackground((Color) value);
				}
			}
		};

		for (int i = 1; i < GrooveTableModel.GROOVE_TIME_VALUES.length; i++) {
			this.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		

		this.addMouseListener(new AbstractMouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				//System.out.println("Released");
				locked=false;
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e)){
					final JPopupMenu jpm = new JPopupMenu();
					final JMenuItem setRow = new JMenuItem("Invert row's color");
					setRow.setForeground(RED);
					setRow.addActionListener(al->{
						
						final int r= ((PersonalJTable)e.getSource())
								.rowAtPoint(e.getPoint());
						for (int c=1; c< tableModel.getColumnCount(); c++){
								tableModel.setValueAt(null, r, c);
						}
						GrooveBox.this.tableChanged(new TableModelEvent(tableModel));
					});
					jpm.add(setRow);
					
					final JMenuItem setCol = new JMenuItem("Invert column's color");
					setCol.setForeground(RED);
					setCol.addActionListener(al->{
						
							final int c= ((PersonalJTable)e.getSource())
									.columnAtPoint(e.getPoint());
							for (int r=0; r< tableModel.getRowCount(); r++){
									tableModel.setValueAt(null,	r, c);
							}
						GrooveBox.this.tableChanged(new TableModelEvent(tableModel));
					});
					jpm.add(setCol);
					
					jpm.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		// Handle the changes of colours in the columns of the GrooveBox
		this.getColumnModel().getSelectionModel()
				.addListSelectionListener(new ListSelectionListener() {
					
					@Override
					public void valueChanged(final ListSelectionEvent e) {
						
						
//						if(col!= getColumn() || row!=getRow()){
//							locked=false;
//						}
						
						if (!e.getValueIsAdjusting() &&  getColumn() != 0 && !locked) {
							
							locked=true;
							row=getRow();
							col=getColumn();
							tableModel.setValueAt(null, row, col);
							GrooveBox.this.tableChanged(new TableModelEvent(tableModel));
						}
					}

					private int getRow() {

						return GrooveBox.this.getMousePosition() == null ? 0
								: GrooveBox.this.rowAtPoint(GrooveBox.this
										.getMousePosition());
					}

					private int getColumn() {
						
						return GrooveBox.this.getMousePosition() == null ? 0
								: GrooveBox.this.columnAtPoint(GrooveBox.this
										.getMousePosition());
					}

				});
	}
}