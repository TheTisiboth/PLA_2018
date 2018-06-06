package mvc;

import java.awt.CheckboxMenuItem;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.text.html.StyleSheet.ListPainter;

import edu.ricm3.game.GameModel;
import edu.ricm3.game.GameUI;
import edu.ricm3.game.Options;
import javafx.scene.shape.Line;
import no.physic.entity.Bonus;
import no.physic.entity.Freeze;
import no.physic.entity.Item_Zbire;
import no.physic.entity.No_Physic_Entity;
import no.physic.entity.Recharge;
import no.physic.entity.Speed;
import physic.entity.Joueur;
import physic.entity.Obstacle;
import physic.entity.Zbire;

public class Model extends GameModel {
	private Joueur c;
	private Joueur c1;
	List<Zbire> j1_zbire;
	List<Zbire> j2_zbire;
	private Obstacle o[];
	int minute;
	int secondes;

	long elapsed;
	long lastTick;

	boolean timer;

	LinkedList<Bonus> listBonus;
	LinkedList<Item_Zbire> listItem;
	LinkedList<Recharge> listRecharge;

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
	BufferedImage m_thunder;
	BufferedImage m_stop;
	BufferedImage m_item;
	BufferedImage m_recharge;

	public Model() {
		lastTick = 0L;

		loadSprites();

		score1 = 0;
		score2 = 0;
		minute = MesOptions.min;
		secondes = 0;
		timer = true;

		plateau = new Case[MesOptions.nbCol][MesOptions.nbLigne];

		initPlat(plateau);

		c = new Joueur(m_personnage, 4, 6, MesOptions.nbCol - 1, MesOptions.nbLigne - 1, 0.9F, Color.RED);
		plateau[MesOptions.nbCol - 1][MesOptions.nbLigne - 1].setE(c);
		plateau[MesOptions.nbCol - 1][MesOptions.nbLigne - 1].setCouleur((Color) c.getColor());
		plateau[MesOptions.nbCol - 1][MesOptions.nbLigne - 1].setRefresh(true);

		c1 = new Joueur(m_personnage, 4, 6, 0, 0, 0.9F, Color.BLUE);
		plateau[0][0].setE(c1);
		plateau[0][0].setCouleur((Color) c1.getColor());
		plateau[0][0].setRefresh(true);

		listBonus = new LinkedList<Bonus>();
		listItem = new LinkedList<Item_Zbire>();
		listRecharge = new LinkedList<Recharge>();

		o = new Obstacle[MesOptions.nb_obstacle];
		initObstacle();
	}

	private void loadSprites() {

		File imageFile = new File("images/winchester.png");
		File BriqueFile = new File("images/brique.png");
		File SplashBlue = new File("images/splashblue.png");
		File SplashRed = new File("images/splashred.png");
		File Bblue = new File("images/blocbleu.png");
		File Bgray = new File("images/blocgris.png");
		File thunder = new File("images/eclair.png");
		File stop = new File("images/stop.png");
		File itemzbire = new File("images/eclair_guillaume.jpg");
		File recharge = new File("images/recharge.png");
		File zbire1;

		try {
			m_obstacle = ImageIO.read(BriqueFile);
			m_personnage = ImageIO.read(imageFile);
			m_Blue = ImageIO.read(SplashBlue);
			m_Red = ImageIO.read(SplashRed);
			m_BlockBlue = ImageIO.read(Bblue);
			m_BlockGray = ImageIO.read(Bgray);
			m_thunder = ImageIO.read(thunder);
			m_stop = ImageIO.read(stop);
			m_item = ImageIO.read(itemzbire);
			m_recharge = ImageIO.read(recharge);

		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

	}

	private void initObstacle() {
		boolean diff = true;
		int[] tab_x = new int[MesOptions.nb_obstacle];
		int[] tab_y = new int[MesOptions.nb_obstacle];
		int compteur = 0;
		do {
			diff = true;
			Random rand = new Random();
			int y = rand.nextInt(MesOptions.nbLigne);
			int x = rand.nextInt(MesOptions.nbCol);
			for (int i = 0; i < compteur; i++) {
				if (tab_y[i] == y && tab_x[i] == x) {
					diff = false;
				}
			}
			if ((x == 0 && y == 0) || (x == MesOptions.nbCol - 1 && y == MesOptions.nbLigne - 1)) {
				diff = false;
			}
			if (diff) {
				tab_y[compteur] = y;
				tab_x[compteur] = x;
				compteur++;
			}
		} while (compteur != MesOptions.nb_obstacle);
		for (int i = 0; i < MesOptions.nb_obstacle; i++) {
			o[i] = new Obstacle(tab_x[i], tab_y[i], 3, m_obstacle);
			plateau[tab_x[i]][tab_y[i]].setE(o[i]);
			plateau[tab_x[i]][tab_y[i]].setRefresh(true);
			plateau[tab_x[i]][tab_y[i]].setCouleur(o[i].getCouleur());

		}
	}

	private void initPlat(Case[][] p) {
		for (int i = 0; i < MesOptions.nbCol; i++) {
			for (int j = 0; j < MesOptions.nbLigne; j++) {
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

		if (timer) {
			c1.canMove(plateau);
			c1.step(now);

			c.canMove(plateau);
			c.step(now);

			checkBonus();
			checkItem();
			checkPaint();
			update_plat();

			elapsed = now - lastTick;

			if (elapsed >= 1000L) {
				if (minute != 0 && secondes == 0) {
					secondes = 60;
					minute--;
				}
				if (minute == 0 && secondes == 0)
					timer = false;
				else {
					secondes--;
					System.out.println(minute + "min" + secondes + "s");
				}

				popItem();
				PopPaint();
				popBonus();
				depopBonus();
				lastTick = now;
			}
		}

	}

	private void checkPaint() {
		if (plateau[c1.getX()][c1.getY()].getE() instanceof Recharge) {
			Recharge r = (Recharge) plateau[c1.getX()][c1.getY()].getE();
			c1.recharger();
			plateau[c1.getX()][c1.getY()].setE(null);
			plateau[c1.getX()][c1.getY()].setRefresh(true);
			listRecharge.remove(r);
		}
		if (plateau[c.getX()][c.getY()].getE() instanceof Recharge) {
			Recharge r = (Recharge) plateau[c.getX()][c.getY()].getE();
			c.recharger();
			plateau[c.getX()][c.getY()].setE(null);
			plateau[c.getX()][c.getY()].setRefresh(true);
			listRecharge.remove(r);
		}

	}

	private void checkItem() {
		if (plateau[c1.getX()][c1.getY()].getE() instanceof Item_Zbire) {
			Item_Zbire item = (Item_Zbire) plateau[c1.getX()][c1.getY()].getE();
			c1.appliquerItem(2);
			plateau[c1.getX()][c1.getY()].setE(null);
			plateau[c1.getX()][c1.getY()].setRefresh(true);
			listItem.remove(item);
		}
		if (plateau[c.getX()][c.getY()].getE() instanceof Item_Zbire) {
			Item_Zbire item = (Item_Zbire) plateau[c.getX()][c.getY()].getE();
			c.appliquerItem(1);
			plateau[c.getX()][c.getY()].setE(null);
			plateau[c.getX()][c.getY()].setRefresh(true);
			listItem.remove(item);
		}

	}

	private void checkBonus() {
		if (plateau[c1.getX()][c1.getY()].getE() instanceof no.physic.entity.Bonus) {
			Bonus bonus = (Bonus) plateau[c1.getX()][c1.getY()].getE();
			c1.appliquerBonus(bonus, c);
			plateau[c1.getX()][c1.getY()].setE(null);
			plateau[c1.getX()][c1.getY()].setRefresh(true);
			listBonus.remove(bonus);
		}
		if (plateau[c.getX()][c.getY()].getE() instanceof no.physic.entity.Bonus) {
			Bonus bonus = (Bonus) plateau[c.getX()][c.getY()].getE();
			c.appliquerBonus(bonus, c1);
			plateau[c.getX()][c.getY()].setE(null);
			plateau[c.getX()][c.getY()].setRefresh(true);
			listBonus.remove(bonus);
		}

	}

	private void popItem() {
		if (MesOptions.Nb_Max_Item >= listItem.size()) {
			Random rand = new Random();
			int i = rand.nextInt(MesOptions.popItem);
			if (i < 1) {
				boolean occuped = true;
				int col, ligne;
				while (occuped) {
					col = rand.nextInt(MesOptions.nbCol);
					ligne = rand.nextInt(MesOptions.nbLigne);
					if (!plateau[col][ligne].isOccuped()) {
						Item_Zbire item = new Item_Zbire(col, ligne, m_item);
						plateau[col][ligne].setE(item);
						plateau[col][ligne].setRefresh(true);
						listItem.add(item);
						occuped = false;
					}
				}
			}
		}
	}

	private void PopPaint() {
		if (MesOptions.Nb_Max_Paint >= listRecharge.size()) {
			Random rand = new Random();
			int i = rand.nextInt(MesOptions.PopPaint);
			if (i < 1) {
				boolean occuped = true;
				int col, ligne;
				while (occuped) {
					col = rand.nextInt(MesOptions.nbCol);
					ligne = rand.nextInt(MesOptions.nbLigne);
					if (!plateau[col][ligne].isOccuped()) {
						Recharge r = new Recharge(col, ligne, m_recharge);
						plateau[col][ligne].setE(r);
						plateau[col][ligne].setRefresh(true);
						listRecharge.add(r);
						occuped = false;
					}
				}
			}
		}

	}

	private void depopBonus() {
		if (!listBonus.isEmpty()) {
			LinkedList<Bonus> used = (LinkedList<Bonus>) listBonus.clone();
			for (Iterator iterator = used.iterator(); iterator.hasNext();) {
				// while (iterator.hasNext()) {
				Bonus b = (Bonus) iterator.next();// .next();
				b.step();
				if (b.getDurationPop() <= 0) {
					listBonus.remove(b);

					plateau[b.getX()][b.getY()].setE(null);
					plateau[b.getX()][b.getY()].setRefresh(true);

				}
			}
		}
	}

	private void popBonus() {
		Random rand = new Random();
		int i = rand.nextInt(MesOptions.popBonus);
		if (i < 1) {
			boolean occuped = true;
			int col, ligne;
			while (occuped) {
				col = rand.nextInt(MesOptions.nbCol);
				ligne = rand.nextInt(MesOptions.nbLigne);
				if (!plateau[col][ligne].isOccuped()) {
					int which = rand.nextInt(3);
					Bonus bonus;
					if (which == 0) {
						bonus = new Freeze(col, ligne, m_stop);

					} else {
						bonus = new Speed(col, ligne, m_thunder);
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
			System.out.println("Score n°1 :" + formatter.format((score1 / MesOptions.nombre_case) * 100));
			System.out.println("Score n°2 :" + formatter.format((score2 / MesOptions.nombre_case) * 100));
			refresh_score = false;
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
			if (c.getPaintStock() != 0)
				plateau[last_xc][last_yc].setM_couleur(m_Blue);
			plateau[last_xc][last_yc].setRefresh(true);

			if (c.getPaintStock() != 0) {
				if (plateau[xc][yc].getM_couleur() == m_BlockBlue || plateau[xc][yc].getM_couleur() == m_BlockGray) {
					score1++;
					refresh_score = true;
				} else if (plateau[xc][yc].getM_couleur() == m_Red) {
					score1++;
					score2--;
					refresh_score = true;
				}
			}
			plateau[xc][yc].setE(c);
			if (c.getPaintStock() != 0) {
				plateau[xc][yc].setCouleur((Color) c.getColor());
				c.decreasePaintStock();
				// GameUI.setProgresse1(c.getPaintStock());
			}
			plateau[xc][yc].setRefresh(true);

		}
		if (last_xc1 != x1 || last_yc1 != y1) {
			plateau[last_xc1][last_yc1].setE(null);
			if (c1.getPaintStock() != 0)
				plateau[last_xc1][last_yc1].setM_couleur(m_Red);
			plateau[last_xc1][last_yc1].setRefresh(true);

			if (c.getPaintStock() != 0) {
				if (plateau[x1][y1].getM_couleur() == m_BlockBlue || plateau[x1][y1].getM_couleur() == m_BlockGray) {
					score2++;
					refresh_score = true;
				} else if (plateau[x1][y1].getM_couleur() == m_Blue) {
					score2++;
					score1--;
					refresh_score = true;
				}
			}

			plateau[x1][y1].setE(c1);
			if (c1.getPaintStock() != 0) {
				plateau[x1][y1].setCouleur((Color) c.getColor());
				c1.decreasePaintStock();
			}
			plateau[x1][y1].setRefresh(true);
		}

	}

	public void spawnzbire(Joueur j, int n, char direction) {
		if (j.getZbire()[n] != null) {
			System.out.println("sbire "+ n);
			int x = j.getX();
			int y = j.getY();
			if (direction == 'D')
				y++;
			else if (direction == 'L')
				x--;
			else if (direction == 'U')
				y--;
			else if (direction == 'R')
				x++;
			if ((x < MesOptions.nbCol && x >= 0) && (y < MesOptions.nbLigne && y >= 0)) {
				System.out.println("if2");
				if (plateau[x][y].getE() instanceof No_Physic_Entity || plateau[x][y].getE() == null) {
					System.out.println("invoque zbire");
					j.getZbire()[n].setX(x);
					j.getZbire()[n].setY(y);
					plateau[x][y].setE(j.getZbire()[n]);
					if(j.getZbire()[n].getJoueur() == 1)
						j1_zbire.add(j.getZbire()[n]);
					else
						j2_zbire.add(j.getZbire()[n]);
					plateau[x][y].setRefresh(true);
					j.resetZbire(n);
				}
			}
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
