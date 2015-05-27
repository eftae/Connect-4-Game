

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

/**
 * This class plays sound clip when Button is pressed
 * @author Jiangkan Pan
 *
 */
public class ButtonSound {

	//static Clip clip;
	static AudioInputStream ais;
	static File soundFile;
	static AudioClip clip;
	static URL url;
	/**
	 * The constructor takes in the path of the audio file
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

}
