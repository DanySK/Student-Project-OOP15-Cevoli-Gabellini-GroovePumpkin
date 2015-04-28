package view.buttons;

import static view.config.Utility.*;

import javax.swing.ImageIcon;

import controller.MusicPlayer;

/**
 * This class implements the functionality of a forwarder or a Rewinder
 * depending on which strategy as been chosen
 * 
 * @author Alessandro
 *
 */
public class FwRwButton extends TJB {

	private static final long serialVersionUID = -5804691980876122814L;

	protected FwRwButton(final MusicPlayer controller, final boolean showTitle, final FRStrategy strategy) {
		super(strategy.getImage());
		doShow(showTitle, strategy.getTitle());
		super.setController(controller);
		strategy.attachController(controller);
		
		this.addActionListener(e -> {
			// go to the next song
			strategy.doStrategy();
		});
	}
	
	public static enum FRStrategy{
		FORWARD("Forward", FW_IMG), REWIND("Rewind", RW_IMG);
		
		private MusicPlayer controller;
		private String title;
		private ImageIcon img;
		
		private FRStrategy(final String title, final ImageIcon img) {
			this.title=title;
			this.img=img;
		}
		
		public void attachController(final MusicPlayer controller){
			this.controller= controller;
		}
		
		public void doStrategy(){
			if (this.equals(FORWARD)){
				controller.goToNextSong();
			} else{
				controller.goToPreviousSong();
			}
		}
		
		public String getTitle(){
			return title;
		}
		
		public ImageIcon getImage(){
			return img;
		}
	}
}
