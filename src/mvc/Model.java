package mvc;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import edu.ricm3.game.GameModel;
import physic.entity.Joueur;
import physic.entity.Obstacle;

public class Model extends GameModel {
	private Joueur c;
	private Joueur c1;
	private Obstacle o[];

	Case plateau[][];

	private float score1;
	private float score2;

	public Model() {
		score1 = 0;
		score2 = 0;
		plateau = new Case[Options.nbCol][Options.nbLigne];
		initPlat(plateau);
		c = new Joueur(0, 0, Color.BLUE);
		plateau[0][0] = new Case(c);
		c1 = new Joueur(31, 17, Color.RED);
		plateau[Options.nbCol-1][Options.nbLigne-1] = new Case(c1);
		
		o = new Obstacle[Options.nb_obstacle];
		initObstacle();
	}

	private void initObstacle() {
		boolean diff = true;
		int[] tab_x = new int[Options.nb_obstacle];
		int[] tab_y = new int[Options.nb_obstacle];
		int compteur =0;
		do {
			diff=true;
			Random rand = new Random();
			int y = rand.nextInt(18);
			int x = rand.nextInt(32);
			for(int i = 0; i<compteur; i++) {
				if (tab_y[i] == y && tab_x[i] == x) {
					diff=false;
				}
			}
			if((x == 0 && y==0 )||( x == Options.nbCol -1 && y == Options.nbLigne -1)) {
				diff =false ;
			}
			if(diff) {
				tab_y[compteur]=y;
				tab_x[compteur]=x;
				compteur++;
			}
		}while(compteur != Options.nb_obstacle );
		for(int i=0; i<Options.nb_obstacle; i++) {
			o[i]= new Obstacle(tab_x[i],tab_y[i],3);
			plateau[tab_x[i]][tab_y[i]].setE(o[i]);
			plateau[tab_x[i]][tab_y[i]].setCouleur(o[i].getCouleur());
			
		}
	}

	private void initPlat(Case[][] p) {
		for (int i = 0; i < Options.nbCol; i++) {
			for (int j = 0; j < Options.nbLigne; j++) {
				p[i][j] = new Case(null);
			}
		}

	}

	@Override
	public void step(long now) {
		c1.canMove(plateau);
		c1.step(now);

		c.canMove(plateau);
		c.step(now);

		update_plat();
		
		afficheScore();
	}

	private void afficheScore() {
		NumberFormat formatter = new DecimalFormat("#00.0");
		if(c.isInMovement()) {
			System.out.println("Score n°1 :" + formatter.format((score1/Options.nombre_case)*100));
		}
		if(c1.isInMovement()) {
			System.out.println("Score n°2 :" + formatter.format((score2/Options.nombre_case)*100));
		}
		
		
	}

	public void update_plat() {
		// mis a jour de la matrice pour les collisions
		int last_xc = c.getLastX();
		int last_yc = c.getLastY();
		int xc = c.getX();
		int yc = c.getY();

		int last_xc1 = c1.getLastX();
		int last_yc1 = c1.getLastY();
		int x1 = c1.getX();
		int y1 = c1.getY();

		if (last_xc != xc || last_yc != yc) {
			plateau[last_xc][last_yc].setE(null);
			if(plateau[xc][yc].getCouleur() == Color.ORANGE ) {
				score1++;
			}
			else if(plateau[xc][yc].getCouleur() == c1.getColor()) {
				score1++;
				score2--;
			}
			plateau[xc][yc].setE(c);
			plateau[xc][yc].setCouleur((Color) c.getColor());

		}
		if (last_xc1 != x1 || last_yc1 != y1) {
			plateau[last_xc1][last_yc1].setE(null);
			if(plateau[x1][y1].getCouleur() == Color.ORANGE) {
				score2++;
			}else if(plateau[x1][y1].getCouleur() == c.getColor()) {
				score2++;
				score1--;
			}
			
			plateau[x1][y1].setE(c1);
			plateau[x1][y1].setCouleur((Color) c1.getColor());
		}
		

	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	public Joueur getCircle() {
		return c;
	}

	public Joueur getCircle2() {
		return c1;
	}

	public Obstacle[] getObstacle() {
		return o;
	}
}
