package view.viewModel;

import static model.PlayerState.*;
import model.PlayerState;
import view.buttons.PlayerButton;
import java.util.function.BiConsumer;

public class StopConsumer implements BiConsumer<PlayerButton, PlayerState> {

	@Override
	public void accept(PlayerButton b, PlayerState s) {
		if (s.equals(STOPPED) || s.equals(REMOVED)) {
			b.setEnabled(false);
		} else if (s.equals(RUNNING) && !b.isEnabled()) {
			b.setEnabled(true);
		}
	}

}
