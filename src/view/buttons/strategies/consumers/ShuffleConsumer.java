package view.buttons.strategies.consumers;

import java.util.function.BiConsumer;

import controller.MusicPlayer;
import static model.PlayerState.*;
import static view.buttons.strategies.MusicPlayerStrategy.*;
import model.PlayerState;
import view.buttons.AbstractStrategicalButton;

/**
 * 
 * @author Alessandro
 *
 */
public class ShuffleConsumer implements
		BiConsumer<AbstractStrategicalButton<MusicPlayer>, PlayerState> {

	@Override
	public void accept(AbstractStrategicalButton<MusicPlayer> b, PlayerState s) {
		if (s.equals(SHUFFLED) || s.equals(UNSHUFFLED)) {
			b.setStrategy(b.getController().isShuffleModeActive() ? 
					UNSHUFFLE : SHUFFLE);
			b.changeTitle(b.getStrategy().getTitle());
			b.setIcon(b.getStrategy().getImage());
			b.repaint();
		}
	}
}
