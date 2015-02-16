package Model;

import java.util.ArrayList;
import java.util.List;

public enum GrooveTimeValues {
	ZERO("Sounds"), ONE("1", "A", "B", "C"), TWO("2", "A", "B", "C"), THREE(
			"3", "A", "B", "C"), FOUR("4", "A", "B", "C"), FIVE("5", "A", "B",
			"C"), SIX("6", "A", "B", "C"), SEVEN("7", "A", "B", "C"), EIGHT(
			"9", "A", "B", "C"), NINE("9", "A", "B", "C"), TEN("10", "A", "B",
			"C");

	public static final int N_COLUMNS = 41;
	
	private final List<String> values = new ArrayList<>();

	private GrooveTimeValues(final String... strings) {
		for (String s : strings) {
			values.add(s);
		}
	}

	public List<String> getValues() {
		return new ArrayList<String>(values);
	}
}
