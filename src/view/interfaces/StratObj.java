package view.interfaces;

/**
 * An interface for an object that implements a strategy pattern
 * 
 * @author Alessandro
 *
 * @param <S> the type of strategy implemented by the object
 */
public interface StratObj <S>{
	
	/**
	 * 
	 * @return the strategy implemented by the object
	 */
	S getStrategy();
	
	/**
	 * Set a new strategy within the object
	 * 
	 * @param s
	 */
	void setStrategy(final S s);
	
	/**
	 * Execute the implemented strategy
	 * 
	 */
	void doStrategy();
}
