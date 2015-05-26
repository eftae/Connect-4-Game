

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

	static Clip clip;
	static AudioInputStream ais;
	static URL url;
	
	/**
	 * The constructor takes in the path of the audio file
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
			// TODO Auto-generated catch block
			System.out.println("file not found 1");
		}
       // getAudioInputStream() also accepts a File or InputStream
        ais = null;
		try {
			ais = AudioSystem.getAudioInputStream(url);
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("file not found 2");
		}
       try {
			clip.open(ais);
		} catch (LineUnavailableException | IOException e) {
			System.out.println("file not found 3");
		}
       clip.start();
		
			
	}

}
