

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**
 * This Class Plays/stops background Music in loop.
 * @author Jiangkan Pan
 *
 */
public class BackgroundMusic {
	
	//static Clip clip;
	static AudioInputStream ais;
	static File soundFile;
	static AudioClip clip;
	static URL url;
/**
 * the constructor tasks in the path of audio file that user like to play
 * @param songName
 */
	public static void music(String songName) {
	
		 	
			try {
				url = new File(songName).toURI().toURL();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
		
				 
				clip = Applet.newAudioClip(url);
				clip.play();

	}
	/**
	 * static top method for easy access to turn off the music
	 */
	public static void stopMusic() {
		clip.stop();
	}

}
