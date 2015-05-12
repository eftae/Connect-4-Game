import sun.audio.*;
import java.io.*;
import javax.swing.*;

public class BackgroundMusic {
	static AudioPlayer MGP;
	static ContinuousAudioDataStream loop;

	public static void music(String songName) {

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

	}
	public static void stopMusic() {
		MGP.stop(loop);
	}
}
