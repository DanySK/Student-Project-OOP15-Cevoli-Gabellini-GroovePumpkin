package view.buttons.strategies;

/**
 * That a functional interface which allows to apply 
 * a simple strategy to an object
 * 
 * @author Alessandro
 *
 */
public interface Strategy<T> {
	
	/**
	 * Apply the desired strategy to the given object
	 * 
	 * @param t
	 */
	public void doStrategy(final T t);
}
