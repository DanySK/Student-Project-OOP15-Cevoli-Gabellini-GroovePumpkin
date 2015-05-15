package view.buttons.strategies.consumers;

import static model.PlayerState.REMOVED;
import static model.PlayerState.RUNNING;
import static model.PlayerState.STOPPED;

import java.util.function.BiConsumer;

import model.PlayerState;
import view.buttons.AbsStratBtn;
import controller.Player;

/**
 * 
 * @author Alessandro
 *
 */
public class StopConsumer implements BiConsumer<AbsStratBtn<Player>, PlayerState> {

	@Override
	public void accept(AbsStratBtn<Player> b, PlayerState s) {
		if (s.equals(STOPPED) || s.equals(REMOVED)) {
			b.setEnabled(false);
		} else if (s.equals(RUNNING) && !b.isEnabled()) {
			b.setEnabled(true);
		}
	}

}
