
/**
 * This class plays sound clip when Button is pressed
 */

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ButtonSound {

	static AudioClip clip;
	static URL url;

	/**
	 * The constructor takes in the path of the audio file
	 * 
	 * @param songName sound file
	 */
	public static void music(String songName) {
		try {
			url = new File(songName).toURI().toURL();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		clip = Applet.newAudioClip(url);
		clip.play();
	}
}
