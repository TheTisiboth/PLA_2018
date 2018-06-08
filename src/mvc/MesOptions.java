package mvc;


public class MesOptions {
	public static final int taille_case = 40;
	public static final int nbCol  = 24;
	public static final int nbLigne = 12;
	public static final int nombre_case = nbCol*nbLigne;
	public static int nb_obstacle = 30;
	
	public static final int popBonus = 2; // 1/popBonus chances de faire pop un bonus par seconde 
	public static final int depopBonus = 5; // temps avant depop (secondes)

	public static final int popItem = 4; // 1/popBonus chances de faire pop un item par seconde 
	public static final int Nb_Max_Item = 4;
	
	public static final int PopPaint = 4; // 1/popBonus chances de faire pop un item par seconde 
	public static final int Nb_Max_Paint = 4;
	public static final int paintMax = 30;
	public static final int recharge = 15; 
	
	public static final int pos_init_x_j1 = 0;
	public static final int pos_init_y_j1 = 0  ;
	public static final int pos_init_x_j2 = nbCol-1 ;
	public static final int pos_init_y_j2 =  nbLigne -1;

	public static final int min =3;

}
