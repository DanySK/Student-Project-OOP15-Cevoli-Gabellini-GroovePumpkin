package view.buttons.strategies.consumers;

import static model.PlayerState.*;
import static view.buttons.strategies.ShuffleStrategy.*;

import java.util.function.BiConsumer;

import model.PlayerState;
import view.buttons.AbstractStratBtn;
import controller.musicplayer.Shuffable;

/**
 * 
 * @author Alessandro
 *
 */
public class ShuffleConsumer implements
		BiConsumer<AbstractStratBtn<Shuffable>, PlayerState> {

	@Override
	public void accept(AbstractStratBtn<Shuffable> b, PlayerState s) {
		if (s.equals(SHUFFLED) || s.equals(UNSHUFFLED)) {
			b.setStrategy(b.getStrategy().equals(SHUFFLE) ? 
					UNSHUFFLE : SHUFFLE);
			b.changeTitle(b.getStrategy().getTitle());
			b.setIcon(b.getStrategy().getImage());
			b.repaint();
		}
	}
}
