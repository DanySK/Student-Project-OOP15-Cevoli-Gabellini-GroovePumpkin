package view.buttons.strategies;

import static model.PlayerState.*;
import model.PlayerState;
import view.buttons.StrategicalButton;

import java.util.function.BiConsumer;

import controller.Player;

public class StopConsumer implements BiConsumer<StrategicalButton<Player>, PlayerState> {

	@Override
	public void accept(StrategicalButton<Player> b, PlayerState s) {
		if (s.equals(STOPPED) || s.equals(REMOVED)) {
			b.setEnabled(false);
		} else if (s.equals(RUNNING) && !b.isEnabled()) {
			b.setEnabled(true);
		}
	}

}
