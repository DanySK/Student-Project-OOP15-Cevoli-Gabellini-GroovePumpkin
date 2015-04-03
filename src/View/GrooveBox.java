package View;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
public class GrooveBox extends PersonalJTable implements Updatable{

	private static final long serialVersionUID = -7907789613027061207L;
	private final TableModel tableModel;

	/**
	 * 
	 * @param tm
	 */
	public GrooveBox(final TableModel tm) {
		super(tm);
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
								
								final Color clr = ((GrooveTableModel) tableModel).getList()
										.get(getRow()).getColorAtIndex(getColumn() - 1);
								
								if (clr.equals(WHITE)) {
									((GrooveTableModel) tableModel).getList().get(getRow())
											.setColorAtIndex(GrooveValues.getRowColor(getRow()), getColumn() - 1);
								} else {
									((GrooveTableModel) tableModel).getList().get(getRow())
											.setColorAtIndex(WHITE, getColumn() - 1);
								}

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
	public Map<Pair<String, Integer> ,List<Integer>> getIndexesTab(final List<GrooveValues> grooveBox){
		//This Method should be moved into the controller/model @Alessandro
		
		final Map<Pair<String, Integer>, List<Integer>> idx= new HashMap<>();
		
		grooveBox.stream().forEach(gVal-> idx.put(new Pair<>(gVal.getName(), gVal.getID()), 
						gVal.getColorsList().stream()
							.filter(pair-> !pair.getFirst().equals(WHITE))
							.mapToInt(pair->pair.getSecond())
							.mapToObj(i -> Integer.valueOf(i))
							.collect(Collectors.toList())));
		return idx;
	}
}
