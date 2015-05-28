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

public class BackgroundMusic {

	static AudioClip clip;
	static URL url;

	/**
	 * the constructor tasks in the path of audio file that user like to play
	 * 
	 * @param songName
	 *            sound file
	 */
	public static void music(String songName) {

		try {
			url = new File(songName).toURI().toURL();
		} catch (MalformedURLException e1) {
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
