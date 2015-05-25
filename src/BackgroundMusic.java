

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class BackgroundMusic {
	
	//static AudioPlayer MGP;
	//static ContinuousAudioDataStream loop;
	static Clip clip;
	static AudioInputStream ais;
	static URL url;

	public static void music(String songName) {
		/*

		MGP = AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		loop = null;
		try {
			BGM = new AudioStream(new FileInputStream(songName));
			MD = BGM.getData();
			loop = new ContinuousAudioDataStream(MD);
		} catch (IOException error) {
			System.out.print("file not found");
		}
		MGP.start(loop);
		*/
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
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
				

	}
	
	public static void stopMusic() {
		clip.stop();
	}

}
