package view.model;

import javax.swing.JFileChooser;

import view.model.MyFileFilter.ExtensionStrategy;

/**
 * A personalized file chooser 
 * with the specifed selection mode
 * and multiple selection by default.
 * 
 * Is also choosable the strategy used for files
 * 
 * @author Alessandro
 *
 */
public class MyFileChooser extends JFileChooser {

	private static final long serialVersionUID = 3073167697924621040L;
	private final MyFileFilter filter;

	public MyFileChooser(final int selectionMode, 
			final ExtensionStrategy strategy) {
		super(System.getProperty("user.home"));
		this.filter = new MyFileFilter(strategy);
		//This way only supported files will be shown
		this.removeChoosableFileFilter(this.getFileFilter());
		this.setFileFilter(filter);
		this.setFileSelectionMode(selectionMode);
		this.setMultiSelectionEnabled(true);
		this.setVisible(true);
	}
	
	/**
	 * @return the filter
	 */
	public MyFileFilter getMyFileFilter() {
		return filter;
	}
}
