package view.buttons.strategies;

import static view.config.Configuration.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.swing.Icon;
import model.PlayerState;
import view.buttons.AbstractStratBtn;
import view.buttons.strategies.consumers.LoopConsumer;
import view.interfaces.BtnStrategy;
import controller.Loopable;

/**
 * This strategy class implements the strategies for shuffle or unshuffling a
 * Loopable player
 * 
 * @author Alessandro
 *
 */
public enum LoopStrategy implements
		BtnStrategy<Loopable, AbstractStratBtn<Loopable>, PlayerState> {
	
	LOOP("Loop", getConfig().getLoopOffImage(), c -> c.setLoop(true), new LoopConsumer()), 
	UNLOOP("UnLoop", getConfig().getLoopOnImage(), c -> c.setLoop(false), new LoopConsumer());

	private String title;
	private Icon img;
	private Consumer<Loopable> ctrlUser;
	private BiConsumer<AbstractStratBtn<Loopable>, PlayerState> updater;

	private LoopStrategy(final String title, final Icon img,
			final Consumer<Loopable> ctrlUser,
			final BiConsumer<AbstractStratBtn<Loopable>, PlayerState> updater) {
		this.title = title;
		this.img = img;
		this.ctrlUser = ctrlUser;
		this.updater = updater;
	}

	@Override
	public void doStrategy(Loopable t) {
		ctrlUser.accept(t);
	}

	@Override
	public Icon getImage() {

		return img;
	}

	@Override
	public String getTitle() {

		return title;
	}

	@Override
	public void updateUser(AbstractStratBtn<Loopable> b, PlayerState s) {
		if(updater!=null){
			this.updater.accept(b, s);
		}
	}

}
