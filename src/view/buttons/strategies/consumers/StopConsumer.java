package view.buttons.strategies.consumers;

import static model.PlayerState.*;
import model.PlayerState;
import view.buttons.AbstractStrategicalButton;

import java.util.function.BiConsumer;

import controller.Player;

/**
 * 
 * @author Alessandro
 *
 */
public class StopConsumer implements BiConsumer<AbstractStrategicalButton<Player>, PlayerState> {

	@Override
	public void accept(AbstractStrategicalButton<Player> b, PlayerState s) {
		if (s.equals(STOPPED) || s.equals(REMOVED)) {
			b.setEnabled(false);
		} else if (s.equals(RUNNING) && !b.isEnabled()) {
			b.setEnabled(true);
		}
	}

}
