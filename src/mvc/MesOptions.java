package mvc;

import java.util.LinkedList;
import java.util.List;

import interpreter.Automaton_I;

public class MesOptions {
	public static final int taille_case = 40; // 40x40 pixels
	public static final int nbCol = 24;
	public static final int nbLigne = 12;
	public static final int nb_cases = nbCol * nbLigne;
	public static int nb_obstacles = 30;

	public static final int popBonus = 2; // 1/popBonus chances de faire pop un
											// bonus par seconde
	public static final int depopBonus = 5; // temps avant depop (secondes)

	public static final int popItem = 4; // 1/popBonus chances de faire pop un
											// item par seconde
	public static final int nb_max_items = 4;

	public static final int popPaint = 4; // 1/popBonus chances de faire pop un
											// item par seconde
	public static final int nb_max_paint = 4;
	public static final int paintMax = 30;
	public static final int recharge = 15; // 15 cases

	// initial position of the player 1
	// top left corner
	public static final int pos_init_x_j1 = 0;
	public static final int pos_init_y_j1 = 0;

	// initial position of the player 2
	// bottom right corner
	public static final int pos_init_x_j2 = nbCol - 1;
	public static final int pos_init_y_j2 = nbLigne - 1;

	public static final int min = 2; // minutes

	public static String nom_j1 = "Joueur 1";
	public static String nom_j2 = "Joueur 2";

	public static boolean deja_parse = false;

	public static LinkedList<String> automates_j1;
	public static LinkedList<String> automates_j2;
	public static List<Automaton_I> automates;

}
