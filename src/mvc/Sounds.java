package mvc;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Sounds {
	
	static AudioStream audioStreamGame = null;

	
	public static void game_sound() {

	    String gongFile = "sons/game.wav";
	    InputStream in = null;
		try {
			in = new FileInputStream(gongFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.. :(");
			e.printStackTrace();
		}

		try {
			audioStreamGame = new AudioStream(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	    AudioPlayer.player.start(audioStreamGame);
	}
	
	
	public static void pop_sound() {

	    String gongFile = "sons/pop.wav";
	    InputStream in = null;
		try {
			in = new FileInputStream(gongFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.. :(");
			e.printStackTrace();
		}

		try {
			audioStreamGame = new AudioStream(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

	    AudioPlayer.player.start(audioStreamGame);
	}
	

	public static void charge_sound() {

	    String gongFile = "sons/charge.wav";
	    InputStream in = null;
		try {
			in = new FileInputStream(gongFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.. :(");
			e.printStackTrace();
		}

		try {
			audioStreamGame = new AudioStream(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

	    AudioPlayer.player.start(audioStreamGame);
	}
	
	public static void hit_sound() {

	    String gongFile = "sons/hit.wav";
	    InputStream in = null;
		try {
			in = new FileInputStream(gongFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.. :(");
			e.printStackTrace();
		}

		try {
			audioStreamGame = new AudioStream(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

	    AudioPlayer.player.start(audioStreamGame);
	}

	public static void portail_sound() {

	    String gongFile = "sons/portail.wav";
	    InputStream in = null;
		try {
			in = new FileInputStream(gongFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.. :(");
			e.printStackTrace();
		}

		try {
			audioStreamGame = new AudioStream(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

	    AudioPlayer.player.start(audioStreamGame);
	}

	public static void clic_sound() {

		String gongFile = "sons/click.wav";
	    InputStream in = null;
		try {
			in = new FileInputStream(gongFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.. :(");
			e.printStackTrace();
		}

		try {
			audioStreamGame = new AudioStream(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

	    AudioPlayer.player.start(audioStreamGame);
	    
	}

}
