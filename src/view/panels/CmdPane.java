package view.panels;

import java.awt.Component;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import view.buttons.AbstractStratBtn;
import controller.musicplayer.MusicPlayer;
import controller.musicplayer.PlaylistFeatureCommand;
import controller.Loopable;
import controller.Player;
import controller.groovebox.GrooveBoxPlayer;

/**
 * This Panels handle a single row of functional Buttons
 * using a wrapper
 * This class implements the Builder Strategy to populates itself 
 * 
 * @author Alessandro
 * 
 */
public class CmdPane extends PersonalJPanel {

	private static final long serialVersionUID = 7485562589795747966L;

	private final CmdWrapper cmdW;
	
	/**
	 * Constructor with commands wrapper
	 * 
	 */
	public CmdPane(final CmdWrapper cW ) {
		super();
		this.cmdW= cW;
	}
	
	/**
	 * Constructor with commands wrapper and LayoutManager
	 */
	public CmdPane(final CmdWrapper cW , final LayoutManager l) {
		super(l);
		this.cmdW= cW;
	}
	
	/**
	 * 
	 * @return The command wrapper attached to this object
	 */
	public CmdWrapper getWrapper(){
		return cmdW;
	}
	
	/**
	 * This class is a builder for the class Command Pane
	 * It offers a simple way to create a row of 
	 * functional buttons with a given LayoutManager
	 * 
	 * @author Alessandro
	 *
	 */
	public static class Builder {
		
		private final List<AbstractStratBtn<?>> l = new ArrayList<>();
		private final CmdWrapper cW= new CmdWrapper();
		private boolean play;
		private boolean stop;
		private boolean fw;
		private boolean bw;
		private boolean loop;
		private boolean shuffle;
		private boolean add;
		private boolean remove;
		private boolean save;
		private boolean reset;

		public Builder setPlay(final AbstractStratBtn<Player> b) 
				throws NullPointerException{
			if(!play){
				cW.setPlay(b);
				l.add(b);
				play=true;
			}
			return this;
		}

		public Builder setStop(final AbstractStratBtn<Player> b) 
				throws NullPointerException{
			if(!stop){
				cW.setStop(b);
				l.add(b);
				stop= true;
			}
			return this;
		}

		public Builder setFW(final AbstractStratBtn<MusicPlayer> b) 
				throws NullPointerException{
			if(!fw){
				cW.setFW(b);
				l.add(b);
				fw=true;
			}
			return this;
		}

		public Builder setBW(final AbstractStratBtn<MusicPlayer> b) 
				throws NullPointerException{
			if(!bw){
				cW.setBW(b);
				l.add(b);
				bw=true;
			}
			return this;
		}

		public Builder setLoop(final AbstractStratBtn<Loopable> b) 
				throws NullPointerException{
			if(!loop){
				cW.setLoop(b);
				l.add(b);
				loop=true;
			}
			return this;
		}

		public Builder setShuffle(final AbstractStratBtn<PlaylistFeatureCommand> b) 
				throws NullPointerException{
			if(!shuffle){
				cW.setShuffle(b);
				l.add(b);
			}
			return this;
		}

		public Builder setAdd(final AbstractStratBtn<MusicPlayer> b) 
				throws NullPointerException{
			if(!add){
				cW.setAdd(b);
				l.add(b);
				add=true;
			}
			return this;
		}

		public Builder setRemove(final AbstractStratBtn<MusicPlayer> b) 
				throws NullPointerException{
			if(!remove){
				cW.setRemove(b);
				l.add(b);
				remove=true;
			}
			return this;
		}

		public Builder setSave(final AbstractStratBtn<GrooveBoxPlayer> b) 
				throws NullPointerException{
			if(!save){
				cW.setSave(b);
				l.add(b);
				save=true;
			}
			return this;
		}
		
		public Builder setReset(final AbstractStratBtn<GrooveBoxPlayer> b) 
				throws NullPointerException{
			if(!reset){
				cW.setReset(b);
				l.add(b);
				reset=true;
			}
			return this;
		}

		public CmdPane build() {
			
			final CmdPane p= new CmdPane(cW);
			p.addComponents(l.toArray(new Component[l.size()]));

			return p;
		}

		public CmdPane build(final LayoutManager lm) {
			final CmdPane p= new CmdPane(cW, lm);
			
			p.addComponents(l.toArray(new Component[l.size()]));
			
			return p;
		}
	}
}