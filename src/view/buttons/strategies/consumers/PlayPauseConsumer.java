package view.buttons.strategies.consumers;

import static model.PlayerState.PAUSED;
import static model.PlayerState.REMOVED;
import static model.PlayerState.RUNNING;
import static model.PlayerState.STOPPED;
import static view.buttons.strategies.PlayerStrategy.PAUSE;
import static view.buttons.strategies.PlayerStrategy.PLAY;

import java.util.function.BiConsumer;

import model.PlayerState;
import view.buttons.AbsStratBtn;
import controller.Player;

/**
 * 
 * @author Alessandro
 *
 */
public class PlayPauseConsumer implements BiConsumer<AbsStratBtn<Player>, PlayerState> {

	@Override
	public void accept(AbsStratBtn<Player> b, PlayerState s) {
		if(s.equals(RUNNING) || s.equals(PAUSED)
				|| s.equals(STOPPED) || s.equals(REMOVED)){
			b.setStrategy(s.equals(RUNNING) ? PAUSE : PLAY);
			b.setIcon(b.getStrategy().getImage());
			b.changeTitle(b.getStrategy().getTitle());
			b.repaint();
		}
	}

}
