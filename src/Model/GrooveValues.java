package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * This class rapresent the basic instruments playable into the groovebox 
 * 
 * @author Alessandro
 *
 */
public enum GrooveValues {

	ACOUSTICBASSDRUM("Acoustic bass drum", 0), BASSDRUM1("Bass drum 1", 1), SIDESTICK(
			"Side stick", 2), ACOUSTICSNARE("Acoustic snare", 3), ELECTRICSNARE(
			"Hand clap", 4), LOWFLOORTOM("Electric snare", 5), CLOSEDHITHAT(
			"Low floor tom", 6);
	
	private final List <Color> colors = new ArrayList<Color>(40);
	private String instrument;
	private String name = "";

	private Integer id;

	private GrooveValues(final String instrument, final int id) {
		this.instrument = instrument;
		this.id = id;

		for (int i = 0; i < GrooveTableModel.GrooveTimeValues.length-1; i++) {
			colors.add(Color.WHITE);
			//System.out.println(colors.size());
		}
	}
	
	private void checkBounds(final int index){
		//System.out.println(index);
		if (index >= colors.size()) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * 
	 * @return the main identifier for this object
	 */
	public String getInstrument() {
		return new String(instrument);
	}
	
	/**
	 * 
	 * @return the alternative identifier for this object
	 */
	public String getName() {
		return new String(name);
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
	 * @return the ID of this object
	 */

	public Integer getID() {
		return new Integer(id);
	}
	
	/**
	 * set a new color into the given index
	 * 
	 * @param c, the new color
	 * @param index, index of the color
	 */

	public void setColor(final Color c, final int index) {
		checkBounds(index);
		colors.set(index,c);
	}
	
	/**
	 * set a alternative indentifier for this object
	 * 
	 * @param name 
	 */

	public void setName(final String name) {
		this.name = name;
	}
	
	/**
	 * This utility method create a new random color in RGB
	 * 
	 * @return a new random Color
	 */
	public static Color getRandomColor(){
		final Random rnd= new Random();
		
		return new Color( rnd.nextInt()%255, rnd.nextInt()%255, rnd.nextInt()%255);
		
	}
}
