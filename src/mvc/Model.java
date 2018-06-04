package mvc;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
import javafx.scene.shape.Line;
import no.physic.entity.Bonus;
import no.physic.entity.Freeze;
import no.physic.entity.Speed;
import physic.entity.Joueur;
import physic.entity.Obstacle;

public class Model extends GameModel {
	private Joueur c;
	private Joueur c1;
	private Obstacle o[];

	long elapsed;
	long lastTick;

	LinkedList<Bonus> listBonus;

	Case plateau[][];

	private float score1;
	private float score2;
	private boolean refresh_score = true;
	BufferedImage m_personnage;
	BufferedImage m_obstacle;
	BufferedImage m_Blue;
	BufferedImage m_Red;
	BufferedImage m_BlockBlue;
	BufferedImage m_BlockGray;

	public Model() {
		lastTick = 0L;

		loadSprites();

		score1 = 0;
		score2 = 0;

		plateau = new Case[Options.nbCol][Options.nbLigne];
		initPlat(plateau);

		c = new Joueur(m_personnage, 4, 6, Options.nbCol - 1, Options.nbLigne - 1, 0.9F, Color.RED);
		plateau[Options.nbCol - 1][Options.nbLigne - 1].setE(c);
		plateau[Options.nbCol - 1][Options.nbLigne - 1].setCouleur((Color) c.getColor());
		plateau[Options.nbCol - 1][Options.nbLigne - 1].setRefresh(true);

		c1 = new Joueur(m_personnage, 4, 6, 0, 0, 0.9F, Color.BLUE);
		plateau[0][0].setE(c1);
		plateau[0][0].setCouleur((Color) c1.getColor());
		plateau[0][0].setRefresh(true);

		listBonus = new LinkedList<Bonus>();

		o = new Obstacle[Options.nb_obstacle];
		initObstacle();
	}

	private void loadSprites() {

		File imageFile = new File("images/winchester.png");
		File BriqueFile = new File("images/brique.png");
		File SplashBlue = new File("images/splashblue.png");
		File SplashRed = new File("images/splashred.png");
		File Bblue = new File("images/blocbleu.png");
		File Bgray = new File("images/blocgris.png");
		try {
			m_obstacle = ImageIO.read(BriqueFile);
			m_personnage = ImageIO.read(imageFile);
			m_Blue = ImageIO.read(SplashBlue);
			m_Red = ImageIO.read(SplashRed);
			m_BlockBlue = ImageIO.read(Bblue);
			m_BlockGray = ImageIO.read(Bgray);

		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

	}

	private void initObstacle() {
		boolean diff = true;
		int[] tab_x = new int[Options.nb_obstacle];
		int[] tab_y = new int[Options.nb_obstacle];
		int compteur = 0;
		do {
			diff = true;
			Random rand = new Random();
			int y = rand.nextInt(Options.nbLigne);
			int x = rand.nextInt(Options.nbCol);
			for (int i = 0; i < compteur; i++) {
				if (tab_y[i] == y && tab_x[i] == x) {
					diff = false;
				}
			}
			if ((x == 0 && y == 0) || (x == Options.nbCol - 1 && y == Options.nbLigne - 1)) {
				diff = false;
			}
			if (diff) {
				tab_y[compteur] = y;
				tab_x[compteur] = x;
				compteur++;
			}
		} while (compteur != Options.nb_obstacle);
		for (int i = 0; i < Options.nb_obstacle; i++) {
			o[i] = new Obstacle(tab_x[i], tab_y[i], 3, m_obstacle);
			plateau[tab_x[i]][tab_y[i]].setE(o[i]);
			plateau[tab_x[i]][tab_y[i]].setRefresh(true);
			plateau[tab_x[i]][tab_y[i]].setCouleur(o[i].getCouleur());

		}
	}

	private void initPlat(Case[][] p) {
		for (int i = 0; i < Options.nbCol; i++) {
			for (int j = 0; j < Options.nbLigne; j++) {
				if ((i + j) % 2 == 0) {
					p[i][j] = new Case(null, m_BlockBlue);
				} else {
					p[i][j] = new Case(null, m_BlockGray);
				}
				p[i][j].setRefresh(true);

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

		elapsed = now - lastTick;
		if (elapsed >= 1000L) {
			popBonus();
			depopBonus();
			lastTick = now;
		}

		afficheScore();
	}

	private void depopBonus() {
		if (!listBonus.isEmpty()) {
			LinkedList<Bonus> used = (LinkedList<Bonus>) listBonus.clone();
			for (Iterator iterator = used.iterator(); iterator.hasNext();) {
				// while (iterator.hasNext()) {
				Bonus b = (Bonus) iterator.next();// .next();
				b.step();
				if (b.getDurationPop() <= 0) {
					// listBonus.remove(b); // NOTE AVVOIRIOIR
					plateau[b.getX()][b.getY()].setE(null);
					plateau[b.getX()][b.getY()].setRefresh(true);

				}
			}
		}
	}

	private void popBonus() {
		Random rand = new Random();
		int i = rand.nextInt(Options.popBonus);
		if (i < 1) {
			boolean occuped = true;
			int col, ligne;
			while (occuped) {
				col = rand.nextInt(Options.nbCol);
				ligne = rand.nextInt(Options.nbLigne);
				if (!plateau[col][ligne].isOccuped()) {
					int which = rand.nextInt(2);
					Bonus bonus;
					if (which == 0) {
						bonus = new Freeze(col, ligne);

					} else {
						bonus = new Speed(col, ligne);
					}
					plateau[col][ligne].setE(bonus);
					plateau[col][ligne].setRefresh(true);
					listBonus.add(bonus);
					occuped = false;
				}
			}
		}
	}

	public LinkedList<Bonus> getListBonus() {
		return listBonus;
	}

	private void afficheScore() {
		NumberFormat formatter = new DecimalFormat("#00.0");
		if (refresh_score) {
			System.out.println("Score n°1 :" + formatter.format((score1 / Options.nombre_case) * 100));
			System.out.println("Score n°2 :" + formatter.format((score2 / Options.nombre_case) * 100));
			refresh_score =false;
		}
	}

	public void update_plat() {

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
			plateau[last_xc][last_yc].setM_couleur(m_Blue);
			plateau[last_xc][last_yc].setRefresh(true);

			if (plateau[xc][yc].getM_couleur() == m_BlockBlue || plateau[xc][yc].getM_couleur() == m_BlockGray) {
				score1++;
				refresh_score = true;
			} else if (plateau[xc][yc].getM_couleur() == m_Red) {
				score1++;
				score2--;
				refresh_score = true;
			}
			plateau[xc][yc].setE(c);
			plateau[xc][yc].setCouleur((Color) c.getColor());
			plateau[xc][yc].setRefresh(true);

		}
		if (last_xc1 != x1 || last_yc1 != y1) {
			plateau[last_xc1][last_yc1].setE(null);
			plateau[last_xc1][last_yc1].setM_couleur(m_Red);
			plateau[last_xc1][last_yc1].setRefresh(true);
			if (plateau[x1][y1].getM_couleur() == m_BlockBlue || plateau[x1][y1].getM_couleur() == m_BlockGray) {
				score2++;
				refresh_score = true;
			} else if (plateau[x1][y1].getM_couleur() == m_Blue) {
				score2++;
				score1--;
				refresh_score = true;
			}

			plateau[x1][y1].setE(c1);
			plateau[x1][y1].setCouleur((Color) c1.getColor());
			plateau[x1][y1].setRefresh(true);
		}

	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	public Joueur getJ1() {
		return c;
	}

	public Joueur getJ2() {
		return c1;
	}

	public Obstacle[] getObstacle() {
		return o;
	}

	public Case[][] getPlateau() {
		return plateau;
	}
}
