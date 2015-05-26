

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
	
	static Clip clip;
	static AudioInputStream ais;
	static URL url;
/**
 * the constructor tasks in the path of audio file that user like to play
 * @param songName
 */
	public static void music(String songName) {
	
		 	try {
				 url = new File(songName).toURI().toURL();
			} catch (MalformedURLException e1) {
				System.out.println("file not found 4");
			}
			
	        clip = null;
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
	
				System.out.println("file not found 1");
			}
	         ais = null;
			try {
				ais = AudioSystem.getAudioInputStream(url);
			} catch (UnsupportedAudioFileException | IOException e) {

				System.out.println("file not found 2");
			}
	        try {
				clip.open(ais);
			} catch (LineUnavailableException | IOException e) {
				System.out.println("file not found 3");
			}
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
				

	}
	/**
	 * static top method for easy access to turn off the music
	 */
	public static void stopMusic() {
		clip.stop();
	}

}
