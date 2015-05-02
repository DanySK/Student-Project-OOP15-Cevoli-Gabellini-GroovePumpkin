package view.buttons.strategies.consumers;

import static model.PlayerState.*;
import static view.buttons.strategies.PlayerStrategy.*;
import model.PlayerState;
import view.buttons.StrategicalButton;
import java.util.function.BiConsumer;
import controller.Player;

/**
 * 
 * @author Alessandro
 *
 */
public class PlayPauseConsumer implements BiConsumer<StrategicalButton<Player>, PlayerState> {

	@Override
	public void accept(StrategicalButton<Player> b, PlayerState s) {
		if(s.equals(RUNNING) || s.equals(PAUSED)
				|| s.equals(STOPPED) || s.equals(REMOVED)){
			b.setStrategy(s.equals(RUNNING) ? PAUSE : PLAY);
			b.setIcon(b.getStrategy().getImage());
			b.changeTitle(b.getStrategy().getTitle());
			b.repaint();
		}
	}

}
