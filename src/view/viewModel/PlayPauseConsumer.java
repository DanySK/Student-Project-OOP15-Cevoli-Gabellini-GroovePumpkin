package view.viewModel;

import static model.PlayerState.*;
import static view.buttons.strategies.PlayerStrategy.*;
import model.PlayerState;
import view.buttons.PlayerButton;

import java.util.Optional;
import java.util.function.BiConsumer;

public class PlayPauseConsumer implements BiConsumer<PlayerButton, PlayerState> {

	@Override
	public void accept(PlayerButton b, PlayerState s) {
		b.setStrategy(s.equals(RUNNING) || s.equals(PAUSED)
				|| s.equals(STOPPED) || s.equals(REMOVED) ? s.equals(RUNNING) ? 
						Optional.ofNullable(PAUSE) : Optional.ofNullable(PLAY)
				: Optional.empty());
		b.setIcon(b.getStrategy().getImage());
		b.changeTitle(b.getStrategy().getTitle());
		b.repaint();
	}

}
