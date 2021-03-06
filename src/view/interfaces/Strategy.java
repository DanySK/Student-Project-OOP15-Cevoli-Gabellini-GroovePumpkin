package view.interfaces;

/**
 * That a functional interface which allows to apply 
 * a simple strategy to an object
 * 
 * @author Alessandro
 *
 */
public interface Strategy<C> {
	
	/**
	 * Apply the desired strategy to the given object
	 * 
	 * @param c
	 */
	void doStrategy(final C c);
}
