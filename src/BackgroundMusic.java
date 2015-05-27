/**
 * This Class Plays/stops background Music in loop.
 * 
 * @author Jiangkan Pan
 *
 */

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;

public class BackgroundMusic {

	// static Clip clip;
	static AudioInputStream ais;
	static AudioClip clip;
	static URL url;

	/**
	 * the constructor tasks in the path of audio file that user like to play
	 * 
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
		clip.loop();

	}

	/**
	 * static top method for easy access to turn off the music
	 */
	public static void stopMusic() {
		clip.stop();
	}

}
