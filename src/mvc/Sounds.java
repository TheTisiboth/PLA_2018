package mvc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Sounds {
	
	static AudioStream audioStreamGame;
	static AudioStream audioStreamClic;
	static AudioStream audioStreamHit;
	static AudioStream audioStreamCharge;
	static AudioStream audioStreamPop;
	static AudioStream audioStreamPortail;
	
	public static void load_sound() {
		String gameFile = "sons/game_all.wav";
	    InputStream in_game = null;
	    
	    String popFile = "sons/pop.wav";
	    InputStream in_pop = null;
	    
	    String chargeFile = "sons/charge.wav";
	    InputStream in_charge = null;
	    
	    String hitFile = "sons/hit.wav";
	    InputStream in_hit = null;
	    
	    String portailFile = "sons/portail.wav";
	    InputStream in_portail = null;
	    
	    String clicFile = "sons/click.wav";
	    InputStream in_clic = null;
	    
	    // game sound
		try {
			in_game = new FileInputStream(gameFile);
		} catch (FileNotFoundException e) {
			System.out.println("File game sound not found.. :(");
			e.printStackTrace();
		}

		try {
			audioStreamGame = new AudioStream(in_game);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// pop sound
		try {
			in_pop = new FileInputStream(popFile);
		} catch (FileNotFoundException e) {
			System.out.println("File pop sound not found.. :(");
			e.printStackTrace();
		}

		try {
			audioStreamPop = new AudioStream(in_pop);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// charge sound
		try {
			in_charge = new FileInputStream(chargeFile);
		} catch (FileNotFoundException e) {
			System.out.println("File charge sound not found.. :(");
			e.printStackTrace();
		}

		try {
			audioStreamCharge = new AudioStream(in_charge);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// hit sound
		try {
			in_hit = new FileInputStream(hitFile);
		} catch (FileNotFoundException e) {
			System.out.println("File hit sound not found.. :(");
			e.printStackTrace();
		}

		try {
			audioStreamHit = new AudioStream(in_hit);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// portail sound
		try {
			in_portail = new FileInputStream(portailFile);
		} catch (FileNotFoundException e) {
			System.out.println("File portail sound not found.. :(");
			e.printStackTrace();
		}

		try {
			audioStreamPortail = new AudioStream(in_portail);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// clic sound
		try {
			in_clic = new FileInputStream(clicFile);
		} catch (FileNotFoundException e) {
			System.out.println("File clic sound not found.. :(");
			e.printStackTrace();
		}

		try {
			audioStreamClic = new AudioStream(in_clic);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void game_sound() {
	    AudioPlayer.player.start(audioStreamGame);
	}
	
	public static void pop_sound() {
	    AudioPlayer.player.start(audioStreamPop);
	}
	
	public static void charge_sound() {
	    AudioPlayer.player.start(audioStreamCharge);
	}
	
	public static void hit_sound() {
	    AudioPlayer.player.start(audioStreamHit);
	}

	public static void portail_sound() {
	    AudioPlayer.player.start(audioStreamPortail);
	}

	public static void clic_sound() {
	    AudioPlayer.player.start(audioStreamClic);
	}
	

}
