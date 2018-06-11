package mvc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;



public class Sounds {

	public static void clic_sound() {

	    String gongFile = "sons/click.wav";
	    InputStream in = null;
		try {
			in = new FileInputStream(gongFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.. :(");
			e.printStackTrace();
		}

	    AudioStream audioStream = null;
		try {
			audioStream = new AudioStream(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

	    AudioPlayer.player.start(audioStream);
	}
}
