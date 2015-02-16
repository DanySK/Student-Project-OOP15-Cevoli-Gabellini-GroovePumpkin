package Model;

import java.awt.Color;

public enum GrooveValues {
	ACOUSTICBASSDRUM("Acoustic bass drum", 0), BASSDRUM1("Bass drum 1", 1), SIDESTICK(
			"Side stick", 2), ACOUSTICSNARE("Acoustic snare", 3), ELECTRICSNARE(
			"Hand clap", 4), LOWFLOORTOM("Electric snare", 5), CLOSEDHITHAT(
			"Low floor tom", 5);

	private String instrument;
	private String name;
	private Color color= Color.WHITE;
	
	private Integer id;

	private GrooveValues(final String instrument, final int id) {
		this.instrument = instrument;
		this.id=id;
	}

	public String getInstrument() {
		return new String(instrument);
	}
	
	public String getName(){
		return new String(name);
	}

	public Color getColor() {
		return new Color(color.getRGB());
	}
	
	public Integer getID(){
		return new Integer(id);
	}

	public void setColor(final Color c) {
		color = new Color(c.getRed(), c.getGreen(), c.getGreen());
	}
	
	public void setName(final String name){
		this.name=name;
	}
}
