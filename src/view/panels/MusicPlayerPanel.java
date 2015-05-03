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
 * Personalized JPanel for the PlayerPanel, this class "handles" the playing
 * and pausing of a chosen song.
 * 
 * @author Alessandro
 *
 */
public class MusicPlayerPanel extends PersonalJPanel<MusicPlayer> {

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

		final PersonalJPanel<Object> north = new PersonalJPanel<>(new BorderLayout());
		final PersonalJPanel<Object> labelsPane = new PersonalJPanel<>(new FlowLayout(1, 20,
				10));
		labelsPane.add(songName);
		labelsPane.add(songTime);
		north.add(labelsPane, BorderLayout.NORTH);

		final PersonalJPanel<Object> center = new PersonalJPanel<>(new FlowLayout(1, 10,
				10));
		final JButton rw = createButton(RW_BUTTON, getController(), false);
		final JButton play = createButton(PLAY_BUTTON, getController(), false);
		final JButton stop = createButton(STOP_BUTTON, getController(), false);
		final JButton fw = createButton(FW_BUTTON, getController(), false);
		final JButton shfl= createButton(SHUFFLE_BUTTON, getController(), false);
		final JButton loop= createButton(LOOP_BUTTON, getController(), false);
		
		center.addComponents(rw, play, stop,  fw);
		north.add(center, BorderLayout.CENTER);

		final PersonalJPanel<Object> south = new PersonalJPanel<>(new FlowLayout(1, -5, 0));
		south.addComponents(loop, shfl);
		north.add(south, BorderLayout.SOUTH);
		
		this.addObservers((Updatable) play, (Updatable) stop, (Updatable)shfl, (Updatable) loop);
		this.add(north, BorderLayout.NORTH);

		// this.addKeyListener(super.getPlayAdapter());
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