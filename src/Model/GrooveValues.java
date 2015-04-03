package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
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
	 * This list rapresent the row of the groovebox, <White>= inactive;
	 * <Colored>= active;
	 * 
	 * The Integer field is the position inside the list
	 * 
	 * The position inside the list rapresent the time quantum when the effect
	 * have to be played (40 should be approsimatively 5 sec)
	 */
	private final List<Pair<Color, Integer>> colors = new ArrayList<>(TIME_QUANTI);

	private DefaultValues value;

	// I've made the constructor final, if you want to build a new GrooveBox
	// tone use the static method
	private GrooveValues(final DefaultValues value) {

		this.value = value;
		for (int i = 0; i < TIME_QUANTI; i++) {
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
	public List<Pair<Color, Integer>> getColorsList() {

		return this.colors;
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
	 * This utility method create a color based on the given row
	 * 
	 * @param row
	 *            the row selected
	 * @return a new random Color
	 */
	public static Color getRowColor(final int row) {

		return row % 2 == 0 ? ORANGE : DARK_GRAY;
	}
}