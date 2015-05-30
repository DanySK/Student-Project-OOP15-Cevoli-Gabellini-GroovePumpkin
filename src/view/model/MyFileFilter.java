package view.model;

import java.io.File;
import java.io.FilenameFilter;
import java.util.function.Function;

import javax.swing.filechooser.FileFilter;

/**
 * A File filter class personalized for .midi, .mid and .wav class
 * with already implemented a FilenameFilter
 * 
 * @author Alessandro
 *
 */
public class MyFileFilter extends FileFilter implements FilenameFilter {
	
	private static final String _MIDI= ".midi";
	private static final String _MID= ".mid";
	private static final String _WAV= ".wav";
	
	/**
	 * The strategy from which choose the wanted filter
	 * 
	 * @author Alessandro
	 *
	 */
	public static enum ExtensionStrategy{
		
		MIDI(s-> s.endsWith(_MIDI) ? true : false, _MIDI),
		MID(s-> s.endsWith(_MID) ? true : false, _MID),
		WAVE(f-> f.endsWith(_WAV) ? true : false, _WAV), 
		MIDI_AND_WAVE(s-> s.endsWith(_MIDI) 
				|| s.endsWith(_WAV) || s.endsWith(_MID) ? true : false, _MIDI, _MID, _WAV);
		
		
		private final String[] descr;
		private final Function<String, Boolean> accept;
		
		private ExtensionStrategy(final Function<String, Boolean> f, final String ...descr){
			this.descr=descr;
			this.accept=f;
		}
		
		public String getDescription() {
			return String.join(" ", this.descr);
		}
		
		/**
		 * Whether the given file is accepted by this filter.
		 * 
		 * @param f
		 * @return
		 */
		public boolean accept(final File f) {
			return f.isDirectory() || accept.apply(f.getName()) ? true : false;
		}
		
		/**
		 * Tests if a specified file should be included in a file list.
		 * 
		 * @param directory
		 * @param name
		 * 
		 * @return true if the value is acceptable, false otherwise
		 */
		public boolean accept(final File dir, final String name) {

			return accept.apply(name);
		}
		
	}

	private final ExtensionStrategy strategy;
	
	/**
	 * A constructor for the MyFileFilter class that take in input an
	 * ExtensionStrategy
	 * 
	 * @param strategy
	 */
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