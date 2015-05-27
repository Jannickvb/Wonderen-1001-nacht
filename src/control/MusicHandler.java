package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import view.Main;

public class MusicHandler {

public static ArrayList<AudioInputStream> audio = new ArrayList<AudioInputStream>();
 Clip clip;
	
 	static{
		try{
			audio.add(AudioSystem.getAudioInputStream(Main.class.getResource("/audio/Intro.aiff")));
			audio.add(AudioSystem.getAudioInputStream(Main.class.getResource("/audio/Poor.aiff")));
			audio.add(AudioSystem.getAudioInputStream(Main.class.getResource("/audio/Rich.aiff")));
			audio.add(AudioSystem.getAudioInputStream(Main.class.getResource("/audio/End.aiff")));
		} catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
 
	public MusicHandler() {
		
	}
	
	public static AudioInputStream getTrack(AudioType song){
		return audio.get(song.ordinal());
	}
	
	public void playSound(AudioType name) throws LineUnavailableException, IOException{
		this.clip = AudioSystem.getClip();
		this.clip.open(audio.get(name.ordinal()));
		this.clip.start();
	}
	
	public void stopSound(){
		this.clip.stop();
	}
	
	public enum AudioType{
		intro, poor, rich, end, tutorial
	}
	
}
