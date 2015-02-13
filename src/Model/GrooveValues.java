package Model;

public enum GrooveValues {
	ACOUSTICBASSDRUM("Acoustic bass drum"), BASSDRUM1("Bass drum 1"), SIDESTICK(
			"Side stick"), ACOUSTICSNARE("Acoustic snare"), ELECTRICSNARE(
			"Hand clap"), LOWFLOORTOM("Electric snare"), CLOSEDHITHAT(
			"Low floor tom");

	private String name;

	private GrooveValues(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
