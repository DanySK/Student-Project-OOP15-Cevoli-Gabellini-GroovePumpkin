package view.model;

import java.io.File;
import java.io.FilenameFilter;
import java.util.function.Function;

import javax.swing.filechooser.FileFilter;

public class MyFileFilter extends FileFilter implements FilenameFilter {
	
	private static final String _MID= ".midi";
	private static final String _WAV= ".wav";
	
	public static enum ExtensionStrategy{
		
		MIDI(s-> s.endsWith(_MID) ? true : false, _MID),
		WAVE(f-> f.endsWith(_WAV) ? true : false, _WAV), 
		MIDI_AND_WAVE(s-> s.endsWith(_MID) 
				|| s.endsWith(_WAV) ? true : false, _MID, _WAV);
		
		
		private final String[] descr;
		private final Function<String, Boolean> accept;
		
		private ExtensionStrategy(final Function<String, Boolean> f, final String ...descr){
			this.descr=descr;
			this.accept=f;
		}
		
		public String getDescription() {
			return String.join(" ", this.descr);
		}
		
		public boolean accept(final File f) {
			return f.isDirectory() || accept.apply(f.getName()) ? true : false;
		}
		
		public boolean accept(final File dir, final String name) {

			return accept.apply(name);
		}
		
	}

	private final ExtensionStrategy strategy;
	
	public MyFileFilter(final ExtensionStrategy strategy) {
		super();
		this.strategy= strategy;
	}
	
	@Override
	public String getDescription() {
		return this.strategy.getDescription();
	}

	@Override
	public boolean accept(final File f) {
		return strategy.accept(f);
	}

	@Override
	public boolean accept(final File dir, final String name) {
		return this.strategy.accept(dir, name);
	}
}