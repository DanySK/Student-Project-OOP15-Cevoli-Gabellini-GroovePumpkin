package view.buttons.strategies.consumers;

import static model.PlayerState.*;
import static view.buttons.strategies.PlayerStrategy.*;
import java.util.function.BiConsumer;
import model.PlayerState;
import view.buttons.AbstractStratBtn;
import controller.Player;

/**
 * That biconsumer implementation handles the 
 * update of the button that use a PlayerStrategy.PLAY or
 * PlayerStrategy.PAUSE;
 * 
 * @author Alessandro
 *
 */
public class PlayPauseConsumer implements
		BiConsumer<AbstractStratBtn<Player>, PlayerState> {

	@Override
	public void accept(AbstractStratBtn<Player> b, PlayerState s) {
		if (s.equals(RUNNING) || s.equals(PAUSED) || s.equals(STOPPED)
				|| s.equals(REMOVED)) {

			b.setStrategy(s.equals(RUNNING) ? PAUSE : PLAY);
			b.setIcon(b.getStrategy().getImage());
			b.changeTitle(b.getStrategy().getTitle());
			b.repaint();
		}
	}

}
