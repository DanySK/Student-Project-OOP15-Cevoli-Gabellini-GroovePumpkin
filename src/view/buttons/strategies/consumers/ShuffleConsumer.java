package view.buttons.strategies.consumers;

import static model.PlayerState.*;
import static view.buttons.strategies.ShuffleStrategy.*;

import java.util.function.BiConsumer;

import model.PlayerState;
import view.buttons.AbstractStratBtn;
import controller.musicplayer.PlaylistFeatureCommand;

/**
 * That biconsumer implementation handles the 
 * update of the button that use a ShuffleStrategy
 * 
 * @author Alessandro
 *
 */
public class ShuffleConsumer implements
		BiConsumer<AbstractStratBtn<PlaylistFeatureCommand>, PlayerState> {

	@Override
	public void accept(AbstractStratBtn<PlaylistFeatureCommand> b, PlayerState s) {
		if (s.equals(SHUFFLED) || s.equals(UNSHUFFLED)) {
			b.setStrategy(b.getStrategy().equals(SHUFFLE) ? 
					UNSHUFFLE : SHUFFLE);
			b.changeTitle(b.getStrategy().getTitle());
			b.setIcon(b.getStrategy().getImage());
			b.repaint();
		}
	}
}
