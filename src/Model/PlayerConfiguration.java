package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;

/**
 * This class rappresent a audio configuration
 * @author Matteo Gabellini
 *
 */
public class PlayerConfiguration {
	// Mixer is basicly the top level component of Java Sound Api
	private Mixer mixer;
	private Optional<Integer> lineIndex; //the index of the selected audio line
	private Mixer.Info[] AudioPortInfo;
	
	public PlayerConfiguration(){
		this.lineIndex = Optional.empty();
		//At the creation of the object i 
		this.AudioPortInfo = AudioSystem.getMixerInfo();
	}
	
	
	public List<String> getAudioPort(){
		//Every time that i call this method i refresh the audio port list
		this.AudioPortInfo = AudioSystem.getMixerInfo();	
		return Arrays.asList(this.AudioPortInfo)
					 .stream()
					 .map(x -> x.getName() + " --- " + x.getDescription())
					 .collect(Collectors.toList());
	}
	
	public void setAudioPortIndex(int lineIndex){
		mixer = AudioSystem.getMixer(this.AudioPortInfo[lineIndex]);
	}
	
	public Mixer getConfiguratedMixer(){
		return this.mixer;
	}
}
