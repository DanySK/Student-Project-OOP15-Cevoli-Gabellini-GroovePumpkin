package View;

import java.awt.Color;

import static javax.swing.ListSelectionModel.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import Model.GrooveTableModel;
import Model.PlayerState;

/**
 * The Class that implements the groovebox
 * 
 * @author Alessandro
 *
 */
public class GrooveBox extends PersonalJTable implements Updatable{

	private static final long serialVersionUID = -7907789613027061207L;
	private final TableModel tableModel;

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
		
		// Handle the changes of colours in the columns of the GrooveBox
		this.getColumnModel().getSelectionModel()
				.addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(final ListSelectionEvent e) {
						
						if(!e.getValueIsAdjusting() && getColumn() != 0){
								
								((GrooveTableModel) tableModel).setValueAt(null, getRow(), getColumn());

								GrooveBox.this.tableChanged(new TableModelEvent(tableModel));
							}
					}
					
					private int getRow(){
						return GrooveBox.this.getSelectedRow()< 0 ? 0 : GrooveBox.this.getSelectedRow();
					}
					
					private int getColumn(){
						return GrooveBox.this.getSelectedColumn()< 0 ? 0 : GrooveBox.this.getSelectedColumn();
					}
					
				});
	}

	@Override
	public void updateStatus(final PlayerState status) {
		if(status.equals(PlayerState.RELOAD)){
			this.tableChanged(new TableModelEvent(tableModel));
		}
	}
}
