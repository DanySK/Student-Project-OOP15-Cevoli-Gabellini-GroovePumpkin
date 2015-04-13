package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import Model.lesson.Pair;
import static Model.Utility.*;

/**
 * This class rapresents the basic instruments playable by the groovebox
 * Each each elements (GrooveValues) is a row of colors (the time quantum where
 * the tone is active) with an associated tone
 * 
 * How I've managed this class should be similar to a SINGLETON pattern
 * 
 * @author Alessandro
 *
 */
public final class GrooveValues {
	
	/*
	 * At least 5s of raw reproduction
	 * 
	 */
	private static final int TIME_QUANTI= GrooveTableModel.GROOVE_TIME_VALUES.length-1;
	
	/**
	 * This is the GrooveBox, each element is a GrooveValues which implements a
	 * sound and a list of colors
	 * 
	 */
	public static final List<GrooveValues> GROOVEBOX = new ArrayList<>();

	/*
	 * This list rapresent the row of the groovebox, 
	 * <False>= inactive;
	 * <True>= active;
	 * 
	 * The Integer field is the position inside the list
	 * 
	 * The position inside the list rapresent the time quantum when the effect
	 * have to be played (40 should be approsimatively 5 sec)
	 */
	private final List<Pair<Boolean, Integer>> row = new ArrayList<>(TIME_QUANTI);

	private final DefaultValues value;

	// I've made the constructor final, if you want to build a new GrooveBox
	// tone use the static method
	private GrooveValues(final DefaultValues value) {

		this.value = value;
		for (int i = 0; i < TIME_QUANTI; i++) {
			row.add(new Pair<>(false, i));
		}
	}

	private void checkBounds(final int index) {
		if (index >= row.size()) {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * @param index
	 * @return return the color found at the given index
	 */
	public Boolean getValueAtIndex(final int index) {
		checkBounds(index);
		return row.get(index).getFirst();
	}

	/**
	 * @return the color's row associated to this object
	 */
	public List<Pair<Boolean, Integer>> getRow() {

		return this.row;
	}

	/**
	 * @return the ID associated with this instrument
	 */
	public Integer getID() {

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
	 * @param c
	 *            , the new color
	 * @param index
	 *            of the color
	 */
	public void setActiveAtIndex(final int index) {
		checkBounds(index);
		row.set(index, new Pair<>(!row.get(index).getFirst(), index));
	}

	/**
	 * Creates a new GrooveBox data Structure
	 * 
	 * @param list
	 * @return an initialized list
	 */
	public static List<GrooveValues> initAGrooveBoxList() {

		if (GROOVEBOX.isEmpty()) {
			for (int i = 0; i < DefaultValues.values().length; i++) {
				GROOVEBOX.add(new GrooveValues(DefaultValues.values()[i]));
			}
		}

		return GROOVEBOX;
	}

	/**
	 * @param list
	 *            the groovebox where will be added the tone
	 * @param def
	 *            the tone to be added
	 */
	// Not very useful now, but i'm thinking of making the groovebox expandibles
	public static void addAToneToTheList(final List<GrooveValues> list,
			final DefaultValues def) {

		list.add(new GrooveValues(def));
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
	public Map<Pair<String, Integer> ,List<Integer>> getIndexedTab(final List<GrooveValues> grooveBox){
		
		final Map<Pair<String, Integer>, List<Integer>> idx= new HashMap<>();
		
		grooveBox.stream().forEach(gVal-> idx.put(new Pair<>(gVal.getName(), gVal.getID()), 
						gVal.getRow().stream()
							.filter(pair-> !pair.getFirst().equals(WHITE))
							.mapToInt(pair->pair.getSecond())
							.mapToObj(i -> Integer.valueOf(i))
							.collect(Collectors.toList())));
		return idx;
	}
}