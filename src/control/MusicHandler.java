package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
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
			audio.add(AudioSystem.getAudioInputStream(Main.class.getResource("/audio/Intro_speech.aiff")));
			audio.add(AudioSystem.getAudioInputStream(Main.class.getResource("/audio/Boat_tutorial.aiff")));
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
	
	public void volumeUp(boolean isUp, Float value)
	{
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		
		if(isUp)
		{
				gainControl.setValue(+value);
		}
		else
		{
				gainControl.setValue(-value);
		}
	}
	
	public void playSound(AudioType name) throws LineUnavailableException, IOException{
		this.clip = AudioSystem.getClip();
		this.clip.open(audio.get(name.ordinal()));
		this.clip.setFramePosition(0);
		this.clip.start();
	}
	
	public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
	
	public void stopSound(){
		this.clip.stop();
	}
	
	public enum AudioType{
		intro, poor, rich, end, introvoice, boattutorial
	}
	
}
