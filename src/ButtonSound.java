import sun.audio.*;

import java.io.*;

import javax.swing.*;

public class ButtonSound {
	static AudioPlayer MGP;
	//static ContinuousAudioDataStream loop;

	public static void music(String songName) {

		MGP = AudioPlayer.player;
		AudioStream BGM = null;
		AudioData MD;

		try {
			BGM = new AudioStream(new FileInputStream(songName));

		} catch (IOException error) {
			System.out.print("file not found");
		}
		MGP.start(BGM);

	}

}
