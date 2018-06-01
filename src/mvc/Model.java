package mvc;

import java.awt.Color;

import edu.ricm3.game.GameModel;
import physic.entity.Joueur;

public class Model extends GameModel {
	private Joueur c;
	private Joueur c1;
	int nbCol;
	int nbLigne;
	Case plateau[][];

	public Model() {
		nbCol = 32;
		nbLigne = 18;
		plateau = new Case[nbCol][nbLigne];
		initPlat(plateau);
		c = new Joueur(0, 0, Color.BLUE);
		plateau[0][0] = new Case(c);
		c1 = new Joueur(2, 2, Color.RED);
		plateau[2][2] = new Case(c1);
	}

	private void initPlat(Case[][] p) {
		for (int i = 0; i < nbCol; i++) {
			for (int j = 0; j < nbLigne; j++) {
				p[i][j] = new Case(null);
			}
		}

	}

	@Override
	public void step(long now) {
		c.canMove(plateau);
		c.step(now);
		c1.canMove(plateau);
		c1.step(now);
		update_plat();
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
			plateau[xc][yc].setE(c);

		}
		if (last_xc1 != x1 || last_yc1 != y1) {
			plateau[last_xc1][last_yc1].setE(null);
			plateau[x1][y1].setE(c1);
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
}
