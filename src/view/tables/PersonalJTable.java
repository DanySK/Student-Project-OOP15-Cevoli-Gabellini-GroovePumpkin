package view.tables;

import static view.config.Utility.*;

import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.TableModel;

import controller.Updatable;
import model.PlayerState;

/**
 * 
 * 
 * @author Alessandro
 *
 */
public class PersonalJTable extends JTable implements Updatable {

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
	public void updateStatus(PlayerState status) {
		// Override this method
	}
}
