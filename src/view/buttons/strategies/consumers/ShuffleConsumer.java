package view.buttons.strategies.consumers;

import static model.PlayerState.*;
import static view.buttons.strategies.ShuffleStrategy.*;

import java.util.function.BiConsumer;

import model.PlayerState;
import view.buttons.AbsStratBtn;
import controller.musicplayer.Shuffable;

/**
 * 
 * @author Alessandro
 *
 */
public class ShuffleConsumer implements
		BiConsumer<AbsStratBtn<Shuffable>, PlayerState> {

	@Override
	public void accept(AbsStratBtn<Shuffable> b, PlayerState s) {
		if (s.equals(SHUFFLED) || s.equals(UNSHUFFLED)) {
			b.setStrategy(b.getStrategy().equals(SHUFFLE) ? 
					UNSHUFFLE : SHUFFLE);
			b.changeTitle(b.getStrategy().getTitle());
			b.setIcon(b.getStrategy().getImage());
			b.repaint();
		}
	}
}
