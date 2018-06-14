package mvc;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;

public class Sounds {


	static Player m_player_game, m_player_clic, m_player_hit, m_player_charge, m_player_pop, m_player_portail;

	// load all the sounds in global players
	// function called in GameUI when loading the first page "accueil"

	@SuppressWarnings("deprecation")
	public static void load_sound() throws NoPlayerException, CannotRealizeException, IOException {

		File clicFile = new File("sons/click.wav");
		URL clicUrl = clicFile.toURL();

		File portailFile = new File("sons/portail.wav");
		URL portailUrl = portailFile.toURL();

		File hitFile = new File("sons/hit.wav");
		URL hitUrl = hitFile.toURL();

		File chargeFile = new File("sons/charge.wav");
		URL chargeUrl = chargeFile.toURL();

		File popFile = new File("sons/pop.wav");
		URL popUrl = popFile.toURL();

		File gameFile = new File("sons/game.wav");
		URL gameUrl = gameFile.toURL();

		// creation of the players
		m_player_game = Manager.createRealizedPlayer(gameUrl);
		m_player_pop = Manager.createRealizedPlayer(popUrl);
		m_player_charge = Manager.createRealizedPlayer(chargeUrl);
		m_player_hit = Manager.createRealizedPlayer(hitUrl);
		m_player_clic = Manager.createRealizedPlayer(clicUrl);
		m_player_portail = Manager.createRealizedPlayer(portailUrl);
	}

	// we rewind all the sounds with the setMediaTime(new Time(0)) command

	// the sound of the game
	public static void game_sound() {
		m_player_game.start();
		m_player_game.setMediaTime(new Time(0));
	}

	// the sound when the player takes a bonus
	public static void pop_sound() {
		m_player_pop.start();
		m_player_pop.setMediaTime(new Time(0));
	}

	// the sound when the player takes a bucket paint
	public static void charge_sound() {
		m_player_charge.start();
		m_player_charge.setMediaTime(new Time(0));
	}

	// the sound when the player hit a wall
	public static void hit_sound() {
		m_player_hit.start();
		m_player_hit.setMediaTime(new Time(0));
	}

	// the sound when the player enters in a portal
	public static void portail_sound() {
		m_player_portail.start();
		m_player_portail.setMediaTime(new Time(0));
	}

	// the sound when the player clicks on a button
	public static void clic_sound() {
		m_player_clic.start();
		m_player_clic.setMediaTime(new Time(0));
	}

}
