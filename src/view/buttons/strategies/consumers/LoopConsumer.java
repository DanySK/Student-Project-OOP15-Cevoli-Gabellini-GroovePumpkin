package view.buttons.strategies.consumers;

import java.util.function.BiConsumer;

import model.PlayerState;
import view.buttons.AbsStratBtn;
import controller.LoopablePlayer;
import static view.buttons.strategies.LoopStrategy.*;
import static model.PlayerState.*;

/**
 * 
 * @author Alessandro
 *
 */
public class LoopConsumer implements BiConsumer<AbsStratBtn<LoopablePlayer>, PlayerState> {

	@Override
	public void accept(AbsStratBtn<LoopablePlayer> b, PlayerState s) {
		if(s.equals(LOOPED) || s.equals(UNLOOPED)){
			b.setStrategy(b.getController().isLoopModeActive() ? 
					UNLOOP : LOOP);
			b.setIcon(b.getStrategy().getImage());
			b.changeTitle(b.getStrategy().getTitle());
			b.repaint();
		}
	}

}
