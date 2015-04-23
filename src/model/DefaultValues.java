package model;

/**
 * This enum rapresent the default values that can be used with the groovebox
 * 
 * @author Alessandro
 * @author Matteo Gabellini
 * 
 */
public enum DefaultValues{
	ACOUSTICBASSDRUM("Acoustic bass drum", 35), BASSDRUM1("Bass drum 1", 1), SIDESTICK(
			"Side stick", 2), ACOUSTICSNARE("Acoustic snare", 38), ELECTRICSNARE(
			"Hand clap", 39), LOWFLOORTOM("Electric snare", 5), CLOSEDHITHAT(
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
		return instrument;
	}
	
	/**
	 * @return the ID of this object
	 */
	public Integer getID() {
		return id;
	}
	
}
