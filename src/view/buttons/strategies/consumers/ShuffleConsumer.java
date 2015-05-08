package view.buttons.strategies.consumers;

import java.util.function.BiConsumer;

import controller.MusicPlayer;
import static model.PlayerState.*;
import static view.buttons.strategies.ShuffleStrategy.*;
import model.PlayerState;
import view.buttons.AbsStratBtn;

/**
 * 
 * @author Alessandro
 *
 */
public class ShuffleConsumer implements
		BiConsumer<AbsStratBtn<MusicPlayer>, PlayerState> {

	@Override
	public void accept(AbsStratBtn<MusicPlayer> b, PlayerState s) {
		if (s.equals(SHUFFLED) || s.equals(UNSHUFFLED)) {
			b.setStrategy(b.getController().isShuffleModeActive() ? 
					UNSHUFFLE : SHUFFLE);
			b.changeTitle(b.getStrategy().getTitle());
			b.setIcon(b.getStrategy().getImage());
			b.repaint();
		}
	}
}
