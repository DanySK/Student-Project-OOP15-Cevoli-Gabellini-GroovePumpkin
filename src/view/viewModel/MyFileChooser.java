package view.viewModel;

import javax.swing.JFileChooser;

public class MyFileChooser extends JFileChooser {

	private static final long serialVersionUID = 3073167697924621040L;
	
	private MyFileFilter filter = new MyFileFilter();
	
	public MyFileChooser() {
		super(System.getProperty("user.home"));
		
		//This way only supported files will be shown
		this.removeChoosableFileFilter(this.getFileFilter());
		this.setFileFilter(filter);
		this.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		this.setMultiSelectionEnabled(true);
		this.setVisible(true);
	}
	
	public MyFileFilter getMyFileFilter(){
		return filter;
	}
}
