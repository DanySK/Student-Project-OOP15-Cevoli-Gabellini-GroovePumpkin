package view.buttons.strategies.consumers;

import static model.PlayerState.*;
import static view.buttons.strategies.LoopStrategy.*;

import java.util.function.BiConsumer;

import model.PlayerState;
import view.buttons.AbstractStratBtn;
import controller.Loopable;

/**
 * 
 * @author Alessandro
 *
 */
public class LoopConsumer implements BiConsumer<AbstractStratBtn<Loopable>, PlayerState> {

	@Override
	public void accept(AbstractStratBtn<Loopable> b, PlayerState s) {
		if(s.equals(LOOPED) || s.equals(UNLOOPED)){
			b.setStrategy(b.getController().isLoopModeActive() ? 
					UNLOOP : LOOP);
			b.setIcon(b.getStrategy().getImage());
			b.changeTitle(b.getStrategy().getTitle());
			b.repaint();
		}
	}

}
