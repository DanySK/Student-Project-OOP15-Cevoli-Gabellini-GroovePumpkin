package view.buttons.strategies.consumers;

import static model.PlayerState.*;
import static view.buttons.strategies.LoopStrategy.*;
import java.util.function.BiConsumer;
import model.PlayerState;
import view.buttons.AbsStratBtn;
import controller.musicplayer.LoopableMusicPlayer;

/**
 * 
 * @author Alessandro
 *
 */
public class LoopConsumer implements BiConsumer<AbsStratBtn<LoopableMusicPlayer>, PlayerState> {

	@Override
	public void accept(AbsStratBtn<LoopableMusicPlayer> b, PlayerState s) {
		if(s.equals(LOOPED) || s.equals(UNLOOPED)){
			b.setStrategy(b.getController().isLoopModeActive() ? 
					UNLOOP : LOOP);
			b.setIcon(b.getStrategy().getImage());
			b.changeTitle(b.getStrategy().getTitle());
			b.repaint();
		}
	}

}
