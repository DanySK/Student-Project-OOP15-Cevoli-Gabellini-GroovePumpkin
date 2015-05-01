package view.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import model.PlayerState;
import controller.MusicPlayer;
import controller.Updatable;
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
public class MusicPlayerPanel extends PersonalJPanel {

	private static final long serialVersionUID = 4164776505153007930L;

	private final JLabel songName = new JLabel("< Nothing Else Matters >");
	private final JLabel songTime = new JLabel("00:00:00");

	/**
	 * Default build for this object, creates a raedy to use Music Player Panel
	 * with the given controller
	 * 
	 * @param mp
	 */
	public MusicPlayerPanel(final MusicPlayer mp) {
		super(new BorderLayout());
		this.setBorder(getADefaultPanelBorder());
		this.setController(mp);
		mp.addUpdatableObserver(this);
		
		songName.setBackground(WHITE);
		songName.setForeground(DARK_GREEN);

		final PersonalJPanel north = new PersonalJPanel(new BorderLayout());
		populateNorthPanel(north);
		this.add(north, BorderLayout.NORTH);

		final PersonalJPanel gainPanel = new PersonalJPanel(new BorderLayout(
				10, 5));

		this.add(gainPanel, BorderLayout.SOUTH);

		// this.addKeyListener(super.getPlayAdapter());
	}

	private void populateNorthPanel(final PersonalJPanel panel) {

		final PersonalJPanel north = new PersonalJPanel(new FlowLayout(1, 20,
				10));
		north.add(songName);
		north.add(songTime);
		panel.add(north, BorderLayout.NORTH);

		final PersonalJPanel center = new PersonalJPanel(new FlowLayout(1, 10,
				10));
		final JButton rw = createButton(RW_BUTTON, false, getController());
		final JButton play = createButton(PLAY_BUTTON, false, getController());
		final JButton stop = createButton(STOP_BUTTON, false, getController());
		final JButton fw = createButton(FW_BUTTON, false, getController());
		center.addComponents(rw, play, stop,  fw);

		panel.add(center, BorderLayout.CENTER);

		final PersonalJPanel south = new PersonalJPanel(
				new FlowLayout(1, -5, 0));
		final JButton shfl= createButton(SHUFFLE_BUTTON, false, getController());
		south.addComponents(createButton(LOOP_BUTTON, false, getController()), shfl);
		panel.add(south, BorderLayout.SOUTH);
		this.addObservers((Updatable) play, (Updatable) stop, (Updatable)shfl);
	}

	@Override
	public void updateStatus(final PlayerState status) {
		// Notify all the observers
		super.updateStatus(status);
		// self update
		if (status.equals(RUNNING) || status.equals(SONGCHANGED)) {
			// change the name of the songs to the new one
			this.songName.setText("< " 
					+ convertURLPath(((MusicPlayer) getController())
							.getCurrentSong().get().getPath()) + " >");

		} else if (status.equals(STOPPED) || status.equals(REMOVED)) {
			// set the no-song string
			this.songName.setText("< Any song is playing >");
		}
	}
}