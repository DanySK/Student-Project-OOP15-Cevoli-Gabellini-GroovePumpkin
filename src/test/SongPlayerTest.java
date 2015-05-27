package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import controller.songplayer.MidiSongPlayer;
import controller.songplayer.SampledSongPlayer;
import controller.songplayer.SongPlayer;

/**
 * This is an automatic test with JUnit for classes that manage the reproduction
 * of a single song
 * 
 * @author Matteo Gabellini
 *
 */
public class SongPlayerTest {
	private static final String SAMPLED_SONG = "file:/Users/matteogabellini/Music/iTunes/iTunes Media/Music/Unknown Artist/Unknown Album/NACCARENA master (STEVE).wav";
	private static final String MIDI_SONG = "file:/Users/matteogabellini/Documents/Materiale Università/2ANNO/Object Oriented Programming/Progetto/chango.mid";
	@org.junit.Test
	public void testSampledSongPlayer() {
		URL songPath = null;
		try {
			songPath = new URL(SAMPLED_SONG);
		} catch (MalformedURLException e1) {
			fail();
		}

		AudioInputStream audioStream;
		Optional<SongPlayer> sSPlayer = Optional.empty();
		
		try {
			audioStream = AudioSystem.getAudioInputStream(songPath);
			sSPlayer = Optional.of(new SampledSongPlayer(audioStream));
		} catch (UnsupportedAudioFileException e1) {
			fail();
		} catch (LineUnavailableException e) {
			fail();
		} catch (IOException e1) {
			fail();
		}
		
		
		if(sSPlayer.isPresent()){
			this.testReproduction(sSPlayer.get(), 3000);
		} else {
			 fail();
		}
		

	}
	
	@org.junit.Test
	public void testMidiSongPlayer(){
		URL songPath = null;
		try {
			songPath = new URL(MIDI_SONG);
		} catch (MalformedURLException e1) {
			fail();
		}
		
		Sequence midiSequence;
		Optional<SongPlayer> sSPlayer = Optional.empty();

		try {
			midiSequence = MidiSystem.getSequence(songPath);
				
			try {
				sSPlayer = Optional.of(new MidiSongPlayer(midiSequence));
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			}
		} catch (InvalidMidiDataException e1) {
			fail();
		} catch (IOException e1) {
			fail();
		}	
		
		
		if(sSPlayer.isPresent()){
			this.testReproduction(sSPlayer.get(), 3000);
		} else {
			 fail();
		}
		
	}
	
	private void testReproduction(SongPlayer lettore, int pauseTime){
		double elapsedTime;
		
		System.out.println("Start Reproduction!!!!");
		lettore.play();
		
		try {
			Thread.sleep(pauseTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Controllo che il tempo della traccia scorra
		assertTrue(lettore.getElapsedTime() > 0);
		System.out.println("Pause!!!!");
		lettore.pause();
		elapsedTime = lettore.getElapsedTime();
		
		// Attendo il tempo deciso dal parametro pauseTime millisecondi
		try {
			Thread.sleep(pauseTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(lettore.getElapsedTime() == elapsedTime);
		System.out.println("Resume!!!!");
		lettore.play();
		
		// Attendo il tempo deciso dal parametro pauseTime secondi
		try {
			Thread.sleep(pauseTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Stop Reproduction!!!!");
		lettore.stop();
		
		assertTrue(lettore.getElapsedTime() == 0);	
	}	

}
