package view.panels;

import java.awt.Component;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import view.buttons.AbsStratBtn;
import controller.GrooveBoxPlayer;
import controller.LoopablePlayer;
import controller.MusicPlayer;
import controller.Player;

/**
 * This class handle a single row of functional Buttons
 * 
 * This class implements the Builder Strategy for istanciate the
 * row of buttons (wrapped by a premade class) 
 * 
 * @author Alessandro
 *
 * @param <C>
 */
public class CmdPane extends PersonalJPanel {

	private static final long serialVersionUID = 7485562589795747966L;

	private CmdWrapper cmdW;
	
	/**
	 * Constructor with commands wrapper
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
	
	public CmdWrapper getWrapper(){
		return cmdW;
	}

	public static class Builder {
		
		private final List<AbsStratBtn<?>> l = new ArrayList<>();
		private final CmdWrapper cW= new CmdWrapper();

		public Builder setPlay(final AbsStratBtn<Player> b) {
			cW.setPlay(b);
			l.add(b);
			return this;
		}

		public Builder setStop(final AbsStratBtn<Player> b) {
			cW.setStop(b);
			l.add(b);
			return this;
		}

		public Builder setFW(final AbsStratBtn<MusicPlayer> b) {
			cW.setFW(b);
			l.add(b);
			return this;
		}

		public Builder setBW(final AbsStratBtn<MusicPlayer> b) {
			cW.setBW(b);
			l.add(b);
			return this;
		}

		public Builder setLoop(final AbsStratBtn<LoopablePlayer> b) {
			cW.setLoop(b);
			l.add(b);
			return this;
		}

		public Builder setShuffle(final AbsStratBtn<MusicPlayer> b) {
			cW.setShuffle(b);
			l.add(b);
			return this;
		}

		public Builder setAdd(final AbsStratBtn<MusicPlayer> b) {
			cW.setAdd(b);
			l.add(b);
			return this;
		}

		public Builder setRemove(final AbsStratBtn<MusicPlayer> b) {
			cW.setRemove(b);
			l.add(b);
			return this;
		}

		public Builder setSave(final AbsStratBtn<GrooveBoxPlayer> b) {
			cW.setSave(b);
			l.add(b);
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