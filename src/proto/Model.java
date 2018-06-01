package proto;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;

public class Model extends GameModel {
	private Circle c;
	private Circle c1;
	private Personnage p1, p2;
	int nbCol;
	int nbLigne;
	Case plateau[][];
	BufferedImage m_personnage;

	public Model() {
		loadSprites();
		nbCol = 32;
		nbLigne = 18;
		plateau = new Case[nbCol][nbLigne];
		initPlat(plateau);
		p1 = new Personnage(this,m_personnage, 4, 6, 4, 4, 0.9F, Color.RED);
		plateau[4][4] = new Case(p1);
		p2 = new Personnage(this,m_personnage, 4, 6, 0, 0, 0.9F, Color.BLUE);
		plateau[4][4] = new Case(p2);
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
		p1.canMove(plateau);
		p1.step(now);
		p2.canMove(plateau);
		p2.step(now);
		update_plat();
	}

	public void update_plat() {
		// mis a jour de la matrice pour les collisions
		
		int last_xp2 = p2.getLastX();
		int last_yp2 = p2.getLastY();
		int xp2 = p2.getX();
		int yp2 = p2.getY();

		int last_xp1 = p1.getLastX();
		int last_yp1 = p1.getLastY();
		int x1 = p1.getX();
		int y1 = p1.getY();

		if (last_xp2 != xp2 || last_yp2 != yp2) {
			plateau[last_xp2][last_yp2].setE(null);
			plateau[xp2][yp2].setE(p2);

		}
		if (last_xp1 != x1 || last_yp1 != y1) {
			plateau[last_xp1][last_yp1].setE(null);
			plateau[x1][y1].setE(p1);
		}

	}
	private void loadSprites() {

	    File imageFile = new File("src/proto/winchester.png");
	    try {
	      m_personnage = ImageIO.read(imageFile);
	    } catch (IOException ex) {
	      ex.printStackTrace();
	      System.exit(-1);
	    }

	  }


	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	public Circle getCircle() {
		return c;
	}

	public Circle getCircle2() {
		return c1;
	}
	
	public Personnage getP1() {
		return p1;
	}
	public Personnage getP2() {
		return p2;
	}
}
