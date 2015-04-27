package view.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JLabel;
//import javax.swing.JSlider;
//import javax.swing.border.CompoundBorder;
//import javax.swing.border.TitledBorder;
import view.buttons.PersonalJButton;
import view.interfaces.Updatable;
import model.PlayerState;
import controller.MusicPlayer;
import static model.PlayerState.*;
import static view.buttons.ButtonFactory.*;
import static view.config.Utility.*;

/**
 * Personalized JPanel for the PlayBackPanel, this class "handles" the playing
 * and pausing of a choosen song.
 * 
 * @author Alessandro
 *
 */
public class MusicPlayerPanel extends PersonalJPanel{

	private static final long serialVersionUID = 4164776505153007930L;

	private final JLabel songName = new JLabel("< Nothing Else Matters >");
	private final JLabel songTime= new JLabel("00:00:00");
	//private final JSlider gain = new JSlider(JSlider.HORIZONTAL, 0, 100, 35);
	
	/**
	 * Default build for this object, creates a raedy to use
	 * Music Player Panel with the given controller 
	 * 
	 * @param mp
	 */
	public MusicPlayerPanel(final MusicPlayer mp) {
		super(new BorderLayout());

		this.setController(mp);
		mp.addUpdatableObserver(this);
		
		this.setBuiltInBorder();
		songName.setBackground(WHITE);
		songName.setForeground(DARK_GREEN);
		
		final PersonalJPanel north = new PersonalJPanel(new BorderLayout());
		populateNorthPanel(north);
		this.add(north, BorderLayout.NORTH);

		final PersonalJPanel gainPanel = new PersonalJPanel(new BorderLayout(
				10, 5));
		//this.populateGainPanel(gainPanel);
		this.add(gainPanel, BorderLayout.SOUTH);
		
		//this.addKeyListener(super.getPlayAdapter());
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
			((MusicPlayer) getController()).goToNextSong();
		});

		final PersonalJButton rw = new PersonalJButton(RW_IMG);
		rw.addActionListener(e -> {
			// go back to the previous song
			((MusicPlayer) getController()).goToPreviousSong();
		});

		center.add(rw);
		addObserver((Updatable) createButton(PLAY_BUTTON, false, getController()));
		center.add((Component) getObservers().get(getObservers().size()-1)); 
		addObserver((Updatable) createButton(STOP_BUTTON, false, getController()));
		center.add((Component) getObservers().get(getObservers().size()-1));
		center.add(fw);

		panel.add(center, BorderLayout.CENTER);
		
		final PersonalJPanel south = new PersonalJPanel(new FlowLayout(1, -5, 0));
		south.add(createButton(LOOP_BUTTON, false, getController()));
		south.add(createButton(SHUFFLE_BUTTON, false, getController()));
		panel.add(south, BorderLayout.SOUTH);
	}

//	private void populateGainPanel(PersonalJPanel gainPanel) {
//
//		final CompoundBorder gainLabel = (CompoundBorder) getACompoundTitledBorder("Volume: "
//				+ gain.getValue());
//
//		((TitledBorder) gainLabel.getOutsideBorder()).setTitleColor(DARK_GRAY);
//		gain.setBorder(gainLabel);
//		gain.setBackground(WHITE);
//		gain.setForeground(DARK_GRAY);
//		gain.setEnabled(true);
//
//		gain.addChangeListener(e -> {
//			// Change the Volume of the song
//			((TitledBorder) gainLabel.getOutsideBorder())
//					.setTitle("Volume: "+ gain.getValue());
//
//			gain.repaint();
//		});
//
//		gainPanel.add(gain, BorderLayout.CENTER);
//	}

	@Override
	public void updateStatus(final PlayerState status) {
		//Notify all the observers
		super.updateStatus(status);
		//self update
		if (status.equals(RUNNING)
				|| status.equals(SONGCHANGED)) {
			//change the name of the songs to the new one
			this.songName.setText("< " + convertURLPath(((MusicPlayer) getController())
					.getCurrentSong().get().getPath()) + " >");
			
		} else if (status.equals(STOPPED) || status.equals(REMOVED)) {
			//set the no-song string
			this.songName.setText("< Any song is playing >");
		}
	}
}