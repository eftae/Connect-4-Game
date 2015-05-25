import sun.audio.*;

import java.io.*;

public class ButtonSound {
	static AudioPlayer MGP;

	// static ContinuousAudioDataStream loop;

	public static void music(String songName) {

		MGP = AudioPlayer.player;
		AudioStream sound = null;

		try {
			sound = new AudioStream(new FileInputStream(songName));

		} catch (IOException error) {
			System.out.print("file not found");
		}

		MGP.start(sound);
	}

}
