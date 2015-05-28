package model.groovebox;

import java.util.ArrayList;
import java.util.List;

import model.lessons.Pair;

/**
 * This class represents an utility class for the GrooveBox data structure
 * that provides method for initialization of the groove table 
 * 
 * @author Alessandro Cevoli
 * @author Matteo Gabellini
 *
 */
public final class GrooveTableManager {
	
	/*
	 * At least 5s of raw reproduction
	 * 
	 */
	private static final int TIME_QUANTI= 40;
	
	/*
	 * This is the concrete GrooveBox data structure, each element is a GrooveValue 
	 * 
	 */
	private static final List<GrooveValue> GROOVEBOX = new ArrayList<>();
	
	private GrooveTableManager(){		
	}
	
	/**
	 * This class represents the basic instrument playable by the groovebox
	 * Each object (GrooveValues) is a row of values (the time quantum where
	 * the tone is active) with an associated tone
	 * (defined in @link{model.groovebox.DefaultValues}).
	 * 
	 * @author Alessandro Cevoli
	 * @author Matteo Gabellini
	 *
	 */
	public static class GrooveValue {

		/*
		 * This list rapresents the row of the groovebox, <False>= inactive;
		 * <True>= active;
		 * 
		 * The Integer field is the position inside the list
		 * 
		 * The position inside the list rapresent the time quantum when the
		 * effect have to be played (40 should be approsimatively 5 sec)
		 */
		private final List<Pair<Boolean, Integer>> row = new ArrayList<>(
				TIME_QUANTI);

		private final DefaultValues value;

		// I've made the constructor final, if you want to build a new GrooveBox
		// tone use the static method
		public GrooveValue(final DefaultValues value) {
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
		 * @return return the value found at the given index
		 */
		public Boolean getValueAtIndex(final int index) {
			checkBounds(index);
			return row.get(index).getFirst();
		}

		/**
		 * @return the value's row associated to this object
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
		 * invert state of the value at the given index the given index
		 * 
		 * @param index
		 */
		public void invertValueAtIndex(final int index) {
			checkBounds(index);
			row.set(index, new Pair<>(!row.get(index).getFirst(), index));
		}
	}
	
	/**
	 * Creates a new GrooveBox data Structure if it wasn't 
	 * present otherwise returns the already initialized groovebox
	 * 
	 * @param list
	 * @return an initialized list
	 */
	public static List<GrooveValue> getGrooveBox() {

		if (GROOVEBOX.isEmpty()) {
			for (int i = 0; i < DefaultValues.values().length; i++) {
				GROOVEBOX.add(new GrooveValue(DefaultValues.values()[i]));
			}
		}
		return GROOVEBOX;
	}

	/**
	 * @param def
	 *            the tone to be added
	 */
	// Not very useful now, but i'm thinking of making the groovebox expandibles
	public static void addAToneToTheTable(final DefaultValues def) {
		GROOVEBOX.add(new GrooveValue(def));
	}
	
	
	
	/**
	 * Reset the data in the groove
	 */
	public static void resetGrooveBox(){
		GROOVEBOX.clear();
		GrooveTableManager.getGrooveBox();
	}
	
	/**
	 * Getter for the dimension in Time Quanti of the GrooveBox
	 */
	public static int getTimeQuanti(){
		return GrooveTableManager.TIME_QUANTI;
	}
}