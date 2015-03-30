package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import Model.lesson.Pair;
import static Model.Utility.*;

/**
 * This class rapresent the basic instruments playable into the groovebox
 * 
 * @author Alessandro
 *
 */
public final class GrooveValues {
	
	/**
	 * 
	 * The names of all the columns, 
	 * i've used a static array instead of an ArrayList 
	 * because i was already sure it won't have changed
	 * 
	 * @GrooveTimeValues
	 */
	public static final String[] GROOVE_TIME_VALUES = new String[]{
		"-Instruments-",
		"#0", "A", "B", "C", "#1", "A", "B", "C", 
		"#2", "A", "B", "C", "#3", "A", "B", "C", 
		"#4", "A", "B", "C", "#5", "A", "B", "C", 
		"#6", "A", "B", "C", "#7", "A", "B", "C", 
		"#8", "A", "B", "C", "#9", "A", "B", "C"};
	
	/* This list rapresent the row of the groovebox, 
	 * <White>= inactive; 
	 * <Colored>= active;
	 * 
	 * The position inside the list rapresent the time quantum 
	 * when the effect have to be played (40 should be approsimatively 5 sec)
	 */
	private final List<Pair<Color, Integer>> colors = new ArrayList<>(GROOVE_TIME_VALUES.length);
	
	private DefaultValues value;
	
	private GrooveValues(final DefaultValues value) {
		
		this.value= value;
		for (int i = 0; i < GROOVE_TIME_VALUES.length; i++) {
			colors.add(new Pair<>(Color.WHITE, i));
		}
	}

	private void checkBounds(final int index) {
		if (index >= colors.size()) {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * @param index
	 * @return return the color found at the given index
	 */
	public Color getColorAtIndex(final int index) {
		checkBounds(index);
		return colors.get(index).getFirst();
	}
	
	/**
	 * @return the color's row associated to this object 
	 */
	public List<Pair<Color, Integer>> getColorsList(){
		
		return this.colors;
	}
	
	/**
	 * @return the ID associated with this instrument
	 */
	public Integer getID(){

		return value.getID();
	}
	
	/**
	 * @return the identifier for this object
	 */
	public String getName() {
		
		return value.getInstrument();
	}
	
	/**
	 * Sets a new color into the given index
	 * 
	 * @param c, the new color
	 * @param index of the color
	 */
	public void setColorAtIndex(final Color c, final int index) {
		checkBounds(index);
		colors.set(index, new Pair<>(c, index));
	}
	
	/**
	 * Creates a new GrooveBox data Structure
	 * 
	 * @param list
	 * @return an initialized list
	 */
	public static List<GrooveValues> initAGrooveBoxList(final List<GrooveValues> list){
		
		for(int i=0; i<DefaultValues.values().length; i++){
			
			list.add(new GrooveValues(DefaultValues.values()[i]));
		}
		return list;
	}
	
	/**
	 * @param list the groovebox where will be added the tone
	 * @param def the tone to be added
	 */
	//Not very useful now, but i'm thinking of making the groovebox expandibles
	public static void addAToneToTheList(final List<GrooveValues> list, final DefaultValues def){
		
		list.add(new GrooveValues(def));
	}

	/**
	 * This utility method create a color based on the given row
	 * 
	 * @param row the row selected
	 * @return a new random Color
	 */
	public static Color getRowColor(final int row) {
		
		return row%2==0 ? ORANGE : DARK_GRAY;
	}
}