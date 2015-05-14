package model.groovebox;

/**
 * This enum rapresent the default values that can be used with the groovebox.
 * 
 * The associations between Integer values and the drum parts are defined in the
 * GM(General Midi) standard Drum Map
 * 
 * @author Alessandro
 * @author Matteo Gabellini
 * 
 */
public enum DefaultValues {
	ACOUSTICBASSDRUM("Acoustic bass drum", 35), BASSDRUM1("Bass drum 1", 36), SIDESTICK(
			"Side stick", 37), ACOUSTICSNARE("Acoustic snare", 38), HANDCLAP(
			"Hand clap", 39), ELECTRICSNARE("Electric snare", 40), LOWFLOORTOM(
			"Low floor tom", 41);

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
		return instrument;
	}

	/**
	 * @return the ID of this object
	 */
	public Integer getID() {
		return id;
	}

}
