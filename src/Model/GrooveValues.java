package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class rapresent the basic instruments playable into the groovebox
 * 
 * @author Alessandro
 *
 */
public class GrooveValues {
	
	/**
	 * This enum rapresent the default values that can be used with the groovebox
	 * 
	 */
	static enum DefaultValues{
		ACOUSTICBASSDRUM("Acoustic bass drum", 0), BASSDRUM1("Bass drum 1", 1), SIDESTICK(
				"Side stick", 2), ACOUSTICSNARE("Acoustic snare", 3), ELECTRICSNARE(
				"Hand clap", 4), LOWFLOORTOM("Electric snare", 5), CLOSEDHITHAT(
				"Low floor tom", 6);
		
		private final String instrument;
		private final Integer id;
		
		private DefaultValues(final String instrument, final int id) {
			this.instrument = instrument;
			this.id = id;
		}
		
		/**
		 * 
		 * @return the main identifier for this object
		 */
		public String getInstrument() {
			return new String(instrument);
		}
		
		/**
		 * @return the ID of this object
		 */

		public Integer getID() {
			return new Integer(id);
		}
		
	}
	
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
	 * <White>= unactive; 
	 * <Colored>= active;
	 * 
	 * The position inside the list rapresent the time quantum 
	 * when the effect have to be played (40 should be approsimatively 5 sec)
	 */
	private final List<Color> colors = new ArrayList<Color>(40);
	
	private String name = "";
	
	private GrooveValues() {

		for (int i = 0; i < 40; i++) {
			colors.add(Color.WHITE);
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
	public Color getColor(final int index) {
		checkBounds(index);
		return colors.get(index);
	}
	
	/**
	 * 
	 * @return the identifier for this object
	 */
	public String getName() {
		return new String(name);
	}

	/**
	 * set a new color into the given index
	 * 
	 * @param c, the new color
	 * @param index of the color
	 */

	public void setColor(final Color c, final int index) {
		checkBounds(index);
		colors.set(index, c);
	}
	
	/**
	 * set a indentifier for this object
	 * 
	 * @param name
	 */

	public void setName(final String name) {
		this.name = name;
	}
	
	public static List<GrooveValues> initAGrooveBoxList(final List<GrooveValues> list){
		
		for(int i=0; i<DefaultValues.values().length; i++){
			
			list.add(new GrooveValues());
		}
		
		return list;
	}

	/**
	 * This utility method create a new random color in RGB
	 * 
	 * @return a new random Color
	 */
	public static Color getRandomColor(final int row) {
		
		return row%2==0 ? Color.ORANGE : Color.DARK_GRAY;
		
	}
}