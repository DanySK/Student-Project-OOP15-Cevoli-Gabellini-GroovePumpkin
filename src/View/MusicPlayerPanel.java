package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import controller.MusicPlayer;
import Model.PlayerState;
import View.buttons.PersonalJButton;
import static Model.PlayerState.*;
import static View.buttons.ButtonFactory.*;
import static View.config.Utility.*;

/**
 * Personalized JPanel for the PlayBackPanel, this class "handles" the playing
 * and pausing of a choosen song.
 * 
 * @author Alessandro
 *
 */
public class MusicPlayerPanel extends PersonalJPanel implements Updatable {

	private static final long serialVersionUID = 4164776505153007930L;

	private final JLabel songName = new JLabel("< Nothing Else Matters >");
	private final JLabel songTime= new JLabel("00:00:00");
	private final JSlider gain = new JSlider(JSlider.HORIZONTAL, 0, 100, 35);
	private final List<Updatable> observer = new ArrayList<>(); //list of observer attached to eventually update
	private Updatable generic; // that only for support
	private MusicPlayer controller; // controller attached to this view
	
	/**
	 * Default build for this object, creates a raedy to use
	 * Music Player Panel with the given controller 
	 * 
	 * @param controller
	 */
	public MusicPlayerPanel(final MusicPlayer controller) {
		super(new BorderLayout());

		this.controller = controller;
		controller.addUpdatableObserver(this);
		
		this.setBuiltInBorder();
		songName.setBackground(WHITE);
		songName.setForeground(DARK_GREEN);
		
		final PersonalJPanel north = new PersonalJPanel(new BorderLayout());
		populateNorthPanel(north);
		this.add(north, BorderLayout.NORTH);

		final PersonalJPanel gainPanel = new PersonalJPanel(new BorderLayout(
				10, 5));
		this.populateGainPanel(gainPanel);
		this.add(gainPanel, BorderLayout.SOUTH);
	}

	private void populateNorthPanel(final PersonalJPanel panel) {

		final PersonalJPanel north = new PersonalJPanel(new FlowLayout(1, 20, 10));
		north.add(songName);
		north.add(songTime);
		panel.add(north, BorderLayout.NORTH);

		final PersonalJPanel center = new PersonalJPanel(new FlowLayout(1, 10, 10));

		final PersonalJButton fw = new PersonalJButton(FW_IMG);
		fw.addActionListener(e -> {
			// go to the next song
			controller.goToNextSong();
		});

		final PersonalJButton rw = new PersonalJButton(RW_IMG);
		rw.addActionListener(e -> {
			// go back to the previous song
			controller.goToPreviousSong();
		});

		center.add(rw);
		generic = (Updatable) createButton(PLAY_BUTTON, false, controller);
		observer.add(generic);
		center.add((Component) generic);
		generic = (Updatable) createButton(STOP_BUTTON, false, controller);
		observer.add(generic);
		center.add((Component) generic);
		center.add(fw);

		panel.add(center, BorderLayout.CENTER);
		
		final PersonalJPanel south = new PersonalJPanel(new FlowLayout(1, -5, 0));
		south.add(createButton(LOOP_BUTTON, false, controller));
		south.add(createButton(SHUFFLE_BUTTON, false, controller));
		panel.add(south, BorderLayout.SOUTH);
	}

	private void populateGainPanel(PersonalJPanel gainPanel) {

		final CompoundBorder gainLabel = (CompoundBorder) getACompoundTitledBorder("Volume: "
				+ gain.getValue());

		((TitledBorder) gainLabel.getOutsideBorder()).setTitleColor(DARK_GRAY);
		gain.setBorder(gainLabel);
		gain.setBackground(WHITE);
		gain.setForeground(DARK_GRAY);
		gain.setEnabled(true);

		gain.addChangeListener(e -> {
			// Change the Volume of the song
			((TitledBorder) gainLabel.getOutsideBorder()).setTitle("Volume: "
					+ gain.getValue());

			gain.repaint();
		});

		gainPanel.add(gain, BorderLayout.CENTER);
	}
	
	/**
	 * 
	 * @return the controler attached to this object
	 */
	public MusicPlayer getController(){
		return this.controller;
	}
	
	/**
	 * Attach a new controller to this object
	 * 
	 * @param mp
	 */
	public void setController(final MusicPlayer mp){
		this.controller= mp;
		controller.addUpdatableObserver(this);
	}

	@Override
	public void updateStatus(PlayerState status) {
		
		//Notify all the observers
		observer.stream().forEach(u->u.updateStatus(status));
		
		//self update
		if (status.equals(RUNNING)
				|| status.equals(SONGCHANGED)) {
			//change the name of the songs to the new one
			this.songName.setText("< " + convertURLPath(controller.getCurrentSong().get().getPath()) + " >");
			
		} else if (status.equals(STOPPED) || status.equals(REMOVED)) {
			//set the no-song string
			this.songName.setText("< Any song is playing >");
		}
	}
}
