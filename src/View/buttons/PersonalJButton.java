package View.buttons;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

/**
 * A personal JButton class that stores all the imagines for the oject of this
 * class and the color of Background and Foreground; It manages also the border
 * of the button
 * 
 * NOTE: 
 * Avrei voluto implementare un pattern creazionale quale Factory o Strategy o Decorator
 * ma a livello di riuso del codice/uso della memoria (decorator) non mi convincevano:
 * l'unico che si presterebbe nel modo migliore sarebbe lo Strategy e che se riesco, reimplementer� 
 * anzich� l'ammucchiata di classi.
 * 
 * @author Alessandro
 *
 */
public class PersonalJButton extends JButton {

	private static final long serialVersionUID = -7937866815197131576L;

	public static final Color GRAY = new Color(50, 50, 50);
	public static final Color L_GRAY = new Color(180, 180, 180);
	public static final Color WHITE = new Color(255, 255, 255);
	public static final Color ORANGE= new Color(255, 127, 0);
	
	/**
	 * Those are all the Icons applicables over the button
	 * 
	 */
	public static final ImageIcon PAUSE_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Pause.png");
	public static final ImageIcon PLAY_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Play.png");
	public static final ImageIcon FW_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "FW.png");
	public static final ImageIcon RW_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "RW.png");
	public static final ImageIcon SAVE_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Save.png");
	public static final ImageIcon REC_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Rec.png");
	public static final ImageIcon STOP_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "Stop.png");
	public static final ImageIcon LOOP_ON_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "loop_GREEN.png");
	public static final ImageIcon LOOP_OFF_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "loop_RED.png");
	public static final ImageIcon SHUFFLE_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "shuffle.png");
	public static final ImageIcon LOAD_IMG = new ImageIcon("data"
			+ System.getProperty("file.separator") + "load.png");
	

	private String id = "";
	private Object controller;

	/**
	 * Basic constructor for the personal JButton
	 */
	protected PersonalJButton() {
		this.setBorder(getADefaultBorder());
		this.setBackground(WHITE);
		this.setForeground(GRAY);
	}

	/**
	 * @param text to be shown upon the button
	 */
	public PersonalJButton(final String text) {
		this();
		this.setText(text);
	}

	/**
	 * @param img to be applied to the button
	 */
	public PersonalJButton(final ImageIcon img) {
		this();
		this.setIcon(img);
	}

	/**
	 * @param img to be applied to the button
	 * @param ID to be associated to the button
	 */
	public PersonalJButton(final ImageIcon img, final String id) {
		this(img);
		this.id = id;
	}
	
	/**
	 * 
	 * @return the controller associated with this button
	 */
	public Object getController(){
		return controller;
	}

	/**
	 * @return the id of this button
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * I needed his method for changing the name of the titled border gotten
	 * 
	 * @return the TitledBorder if it exists, otherways null
	 */
	public TitledBorder getTitledBorder() {

		if (this.getBorder() instanceof CompoundBorder
				&& ((CompoundBorder) this.getBorder())
						.getOutsideBorder() instanceof TitledBorder){
			return ((TitledBorder) ((CompoundBorder) this.getBorder())
					.getOutsideBorder());
		}
		
		return null;
	}
	
	/**
	 * 
	 * @return the controller associated with this button
	 */
	public void setController(final Object controller){
		this.controller=controller;
	}
	
	/**
	 * @param newID the new ID to be associated with this button
	 */
	public void setID(final String newID) {
		this.id = newID;
	}
	
	/**
	 * 
	 * @param name
	 *            to be shown on the TitledBorder
	 */ 
	public void showTitle(final String name) {

		this.setBorder(getACompoundTitledBorder(name));
	}
	
	/**
	 * 
	 * @param title
	 * @return a built-in TitledBorder
	 */
	public static Border getACompoundTitledBorder(final String title) {

		return new CompoundBorder(new TitledBorder(new SoftBevelBorder(
				SoftBevelBorder.RAISED, L_GRAY, GRAY), title,
				TitledBorder.ABOVE_TOP, TitledBorder.CENTER), new EmptyBorder(
				4, 16, 4, 16));
	}
	
	/**
	 * 
	 * @return a built-in CompoundBorder
	 */
	public static Border getADefaultBorder(){
		return new CompoundBorder(new SoftBevelBorder(
				SoftBevelBorder.RAISED, L_GRAY, GRAY), new EmptyBorder(6, 16,
				6, 16));
	}
	
}