package View;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import Model.GrooveValues;
import Model.GrooveTableModel;
import Model.PlayerState;
import Model.lesson.Pair;
import static Model.Utility.*;

/**
 * The Class that implements the groovebox
 * 
 * @author Alessandro
 *
 */
public class GrooveBox extends JTable implements Updatable{

	private static final long serialVersionUID = -7907789613027061207L;
	private GrooveTableModel tableModel;

	/**
	 * 
	 * @param tm
	 */
	public GrooveBox(final TableModel tm) {
		super(tm);
		tableModel = (GrooveTableModel) tm;

		//this.setAutoResizeMode(0);
		this.getColumn(GrooveValues.GROOVE_TIME_VALUES[0]).setMinWidth(120);
		this.setRowHeight(25);
		this.setColumnSelectionAllowed(false);
		this.setRowSelectionAllowed(false);
	
		// Thank you STACKOVERFLOW <3
		this.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setResizingAllowed(false);
		
		this.getTableHeader().setBackground(DARK_GRAY);
		this.getTableHeader().setForeground(WHITE);
		this.getTableHeader().setBorder(
				new CompoundBorder(new SoftBevelBorder(SoftBevelBorder.RAISED),
						new EmptyBorder(5, 5, 5, 5)));

		this.setGridColor(DARK_GRAY);
		this.setForeground(RED);
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {

			private static final long serialVersionUID = -5252083106955434257L;

			public void setValue(Object value) {
				if(value instanceof Color){
					this.setBackground((Color) value);
				}
			}
		};

		for (int i = 1; i < GrooveValues.GROOVE_TIME_VALUES.length; i++) {
			this.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		
		// Handle the changes of colours in the columns of the GrooveBox
		this.getColumnModel().getSelectionModel()
				.addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						
						if(!e.getValueIsAdjusting()){
							if (this.getColumn() != 0) {
								
								Color clr = tableModel.getList().get(this.getRow())
										.getColorAtIndex(this.getColumn() - 1);
								
								if (clr.equals(WHITE)) {
									tableModel.getList().get(this.getRow())
											.setColorAtIndex(GrooveValues.getRowColor(this.getRow()), this.getColumn() - 1);
								} else {
									tableModel.getList().get(this.getRow())
											.setColorAtIndex(WHITE, this.getColumn() - 1);
								}

								GrooveBox.this.tableChanged(new TableModelEvent(
										tableModel));
							}
						}
					}
					
					private int getRow(){
						return GrooveBox.this.getSelectedRow()< 0 ? 0 : GrooveBox.this.getSelectedRow();
					}
					
					private int getColumn(){
						return GrooveBox.this.getSelectedColumn()< 0 ? 0 : GrooveBox.this.getSelectedColumn();
					}
					
				});
		
		this.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	@Override
	public void updateStatus(PlayerState status) {
		if(status.equals(PlayerState.RELOAD)){
			this.tableChanged(new TableModelEvent(tableModel));
		}
	}
	
	/**
	 * Keys-> A Pair<String, Integer>,
	 * 		the String value is the name of the tone;
	 *		the Integer value is the ID associated to the Tone;
	 * 
	 * Values-> A List<Integer>, rapresenting the indexes (or time's quantum) 
	 *  	where the specified tone is active
	 * 
	 * @return A Map that will rapresents the groovebox's pattern
	 */
	public Map<Pair<String, Integer> ,List<Integer>> getIndexesTab(){
		
		final Map<Pair<String, Integer>, List<Integer>> idx= new HashMap<>();
		
		this.tableModel.getList().stream()
			.forEach(gVal-> idx.put(new Pair<>(gVal.getName(), gVal.getID()), 
						gVal.getColorsList().stream()
							.filter(pair-> !pair.getFirst().equals(WHITE))
							.mapToInt(pair->pair.getSecond())
							.mapToObj(i -> Integer.valueOf(i))
							.collect(Collectors.toList())));
		return idx;
	}
}
