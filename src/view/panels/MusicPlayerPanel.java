package view.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import model.PlayerState;
import controller.MusicPlayer;
import static model.PlayerState.*;
import static view.buttons.ButtonFactory.*;
import static view.config.Utility.*;
import static view.config.Configuration.*;

/**
 * Personalized JPanel for the PlayerPanel, this class "handles" the playing and
 * pausing of a chosen song.
 * 
 * This class has by default: 
 * -A label for the song name;
 * -A play button;
 * -A stop button;
 * -A Forward and Backward button;
 * 
 * Optionally:
 * 
 * -Add a Loop button;
 * -Add a Shuffle button;
 * -Add a label for elapsed time;-> still in work
 * -Add a progrese bar (slider); -> still in work
 * 
 * @author Alessandro
 *
 */
public class MusicPlayerPanel extends ControllablePane<MusicPlayer> {

	private static final long serialVersionUID = 4164776505153007930L;

	private JLabel songName = new JLabel("< Nothing Else Matters >");
	private JLabel songTime;
	private JProgressBar jpb;
	
	private final PersonalJPanel north = new PersonalJPanel(new BorderLayout());
	private final PersonalJPanel infoPane= new PersonalJPanel(new BorderLayout());
	private final PersonalJPanel barPane= new PersonalJPanel(new BorderLayout(10, 30));
	private final PersonalJPanel labelsPane = new PersonalJPanel(new FlowLayout(1, 20, 10));
	private final List<CmdPane> cmdPanes = new ArrayList<>();

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
		labelsPane.add(songName);
		infoPane.add(labelsPane, BorderLayout.NORTH);
		infoPane.add(barPane, BorderLayout.CENTER);
		north.add(infoPane, BorderLayout.NORTH);
		cmdPanes.add(new CmdPane.Builder()
			.setBW(createButton(RW_B, getController(), false))
			.setPlay(createButton(PLAY_B, getController(), false))
			.setStop(createButton(STOP_B, getController(), false))
			.setFW(createButton(FW_B, getController(), false))
			.build(new FlowLayout(1, 10, 10)));
		cmdPanes.add(new CmdPane.Builder()
			.setLoop(createButton(LOOP_B, getController(), false))
			.setShuffle(createButton(SHUFFLE_B, getController(), false))
			.build(new FlowLayout(1, -5, 0)));		
		
		north.add(cmdPanes.get(0), BorderLayout.CENTER);
		north.add(cmdPanes.get(1), BorderLayout.SOUTH);

		this.addObservers(cmdPanes.get(0).getWrapper().getPlay().get(), 
				cmdPanes.get(0).getWrapper().getStop().get(), 
				cmdPanes.get(1).getWrapper().getShuffle().get(), 
				cmdPanes.get(1).getWrapper().getLoop().get());
		
		this.add(north, BorderLayout.NORTH);
	}
	
	@Override
	public void updateStatus(final PlayerState status) {
		// Notify all the observers
		super.updateStatus(status);
		// self update
		if (status.equals(RUNNING) || status.equals(SONGCHANGED)) {
			// change the name of the songs to the new one
			this.songName.setText(String.join("" ,"< ",
					convertURLPath(((MusicPlayer) getController())
					.getCurrentSong().get().getPath()), " >"));

		} else if (status.equals(STOPPED) || status.equals(REMOVED)) {
			// set the no-song string
			this.songName.setText("< Any song is playing >");
		}
	}

	/**
	 * 
	 * @return
	 */
	public JLabel getSongTimeLabel() {
		return this.songTime;
	}
	
	public JProgressBar getProgressBar(){
		return this.jpb;
	}

	/**
	 * 
	 * @param l
	 */
	public void setSongTimeLabel(final JLabel l) {
		if (checkObj(getSongTimeLabel())) {
			this.labelsPane.remove(getSongTimeLabel());
		}
		this.labelsPane.add(l, 1);
		this.songTime = l;
	}
	
	/**
	 * 
	 */
	public void setProgressBar(final JProgressBar jpb){
		if (checkObj(getProgressBar())) {
			this.barPane.remove(getProgressBar());
		}
		this.barPane.add(jpb, BorderLayout.CENTER);
		this.jpb=jpb;
	}
}