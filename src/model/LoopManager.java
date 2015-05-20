package model;
/**
 * This manager can be used from another class to manage the loop mode 
 * 
 * see also {@link model.LoopableComponent}
 * @author Matteo Gabellini
 *
 */
public class LoopManager implements LoopableComponent{	
	private boolean loop;
	
	@Override
	public void setLoopMode(final boolean value) {
		this.loop = value;
	}
	
	@Override
	public boolean isLoopModeActive() {
		return this.loop;
	}
}
