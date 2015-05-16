package view.model;

import javax.swing.JFileChooser;

/**
 * A personalized file chooser 
 * with the specifed selection mode
 * and multiple selection
 * 
 * @author Alessandro
 *
 */
public class MyFileChooser extends JFileChooser {

	private static final long serialVersionUID = 3073167697924621040L;
	
	private MyFileFilter filter = new MyFileFilter();
	
	public MyFileChooser(final int selectionMode) {
		super(System.getProperty("user.home"));
		
		//This way only supported files will be shown
		this.removeChoosableFileFilter(this.getFileFilter());
		this.setFileFilter(filter);
		this.setFileSelectionMode(selectionMode);
		this.setMultiSelectionEnabled(true);
		this.setVisible(true);
	}
	
	public MyFileFilter getMyFileFilter(){
		return filter;
	}
}
