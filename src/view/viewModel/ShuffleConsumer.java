package view.viewModel;

import java.util.Optional;
import java.util.function.BiConsumer;

import static model.PlayerState.*;
import static view.buttons.strategies.MusicPlayerStrategy.*;
import model.PlayerState;
import view.buttons.MusicPlayerButton;

public class ShuffleConsumer implements
		BiConsumer<MusicPlayerButton, PlayerState> {

	@Override
	public void accept(MusicPlayerButton b, PlayerState s) {
		if (s.equals(SHUFFLED) || s.equals(UNSHUFFLED)) {
			b.setStrategy(b.getStrategy().equals(SHUFFLE) ? 
					Optional.ofNullable(UNSHUFFLE) : Optional.ofNullable(SHUFFLE));
			b.changeTitle(b.getStrategy().getTitle());
			b.setIcon(b.getStrategy().getImage());
			b.repaint();
		}
	}
}
