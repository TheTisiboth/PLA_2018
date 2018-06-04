package mvc;


public class MesOptions {
	public static final int taille_case = 38;
	public static final int nbCol  = 960/taille_case;
	public static final int nbLigne = 480/taille_case;
	public static final int nombre_case = nbCol*nbLigne;
	public static int nb_obstacle = 40;
	public static final int popBonus = 2; // 1/popBonus chances de faire pop un bonus par seconde 
	public static final int depopBonus = 5; // temps avant depop (secondes)
}
