package view.tables;

import static view.config.Configuration.DARK_GRAY;
import static view.config.Configuration.RED;
import static view.config.Configuration.WHITE;

import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;

import model.PlayerState;
import controller.UpdatableObserver;

/**
 * A personalized JTable with unresizable Header 
 * and unswitchable columns
 * 
 * @author Alessandro
 *
 */
public class PersonalJTable extends JTable implements UpdatableObserver {

	private static final long serialVersionUID = -2646448367133853783L;

	public PersonalJTable(final TableModel model, final int selectionMode) {
		super(model);
		this.setBackground(WHITE);
		this.setForeground(RED);
		this.setGridColor(DARK_GRAY);
		this.setRowHeight(25);
		
		// Thank you STACKOVERFLOW <3
		this.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setResizingAllowed(false);
		this.getTableHeader().setFocusable(false);
		this.getTableHeader().setBackground(DARK_GRAY);
		this.getTableHeader().setForeground(WHITE);
		
		this.getTableHeader().setBorder(
				new CompoundBorder(new SoftBevelBorder(SoftBevelBorder.RAISED),
						new EmptyBorder(5, 5, 5, 5)));
		this.getSelectionModel().setSelectionMode(selectionMode);
	}
	
	public void setColumnHeaderBounds(final int identifier, final int minSize, final int maxSize){
		this.getColumnModel().getColumn(identifier).setMinWidth(minSize);
		this.getColumnModel().getColumn(identifier).setMaxWidth(maxSize);
	}

	@Override
	public void updateStatus(final PlayerState status) {
		if(status.equals(PlayerState.RELOAD)){
			this.tableChanged(new TableModelEvent(getModel()));
		}
	}
}
