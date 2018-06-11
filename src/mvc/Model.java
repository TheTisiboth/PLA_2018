package mvc;

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
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import edu.ricm3.game.GameModel;
import fenetre.GameWindow;
import no.physic.entity.Bonus;
import no.physic.entity.Freeze;
import no.physic.entity.Item_Zbire;
import no.physic.entity.No_Physic_Entity;
import no.physic.entity.Portal;
import no.physic.entity.Recharge;
import no.physic.entity.Speed;
import physic.entity.Joueur;
import physic.entity.Obstacle;
import physic.entity.Physic_Entity;
import physic.entity.Zbire;

public class Model extends GameModel {
	private Joueur player2, player1;
	List<Zbire> j1_zbire, j2_zbire;
	private Obstacle o[];

	public Statistique statistique;
	private Portal portal;

	private int minutes, secondes;

	long elapsed, lastTick;
	private int counter_sec;

	boolean timer;

	LinkedList<Bonus> listBonus;
	LinkedList<Item_Zbire> listItem;
	LinkedList<Recharge> listRecharge;

	Case plateau[][];

	private float score1, score2;
	private boolean refresh_score = true;
	BufferedImage m_personnage, m_obstacle, m_Blue, m_Red, m_BlockBlue, m_BlockGray, m_thunder, m_stop, m_item,
			m_recharge, m_portal;
	public BufferedImage m_transparent;
	GameWindow m_frame;

	private String name_j1, name_j2;

	public String getName_j1() {
		return name_j1;
	}

	public void setName_j1(String name_j1) {
		this.name_j1 = name_j1;
	}

	public String getName_j2() {
		return name_j2;
	}

	public void setName_j2(String name_j2) {
		this.name_j2 = name_j2;
	}

	public Model(int perso1, int perso2) {
		lastTick = 0L;
		counter_sec = 0;

		loadSprites();

		score1 = 0;
		score2 = 0;

		minutes = MesOptions.min;
		secondes = 0;

		timer = true;

		plateau = new Case[MesOptions.nbCol][MesOptions.nbLigne];

		initPlat(plateau);

		player2 = new Joueur(m_personnage, 12, 24, perso1, MesOptions.nbCol - 1, MesOptions.nbLigne - 1, 0.25F,
				Color.BLUE);
		plateau[MesOptions.pos_init_x_j2][MesOptions.pos_init_y_j2].setE(player2);
		plateau[MesOptions.pos_init_x_j2][MesOptions.pos_init_y_j2].setCouleur((Color) player2.getColor());
		plateau[MesOptions.pos_init_x_j2][MesOptions.pos_init_y_j2].setRefresh(true);

		player1 = new Joueur(m_personnage, 12, 24, perso2, 0, 0, 0.25F, Color.RED);
		plateau[MesOptions.pos_init_x_j1][MesOptions.pos_init_y_j1].setE(player1);
		plateau[MesOptions.pos_init_x_j1][MesOptions.pos_init_y_j1].setCouleur((Color) player1.getColor());
		plateau[MesOptions.pos_init_x_j1][MesOptions.pos_init_y_j1].setRefresh(true);

		listBonus = new LinkedList<Bonus>();
		listItem = new LinkedList<Item_Zbire>();
		listRecharge = new LinkedList<Recharge>();

		o = new Obstacle[MesOptions.nb_obstacles];
		initObstacle();

		statistique = new Statistique();

		initPortal();

		j1_zbire = new LinkedList<Zbire>();
		j2_zbire = new LinkedList<Zbire>();
	}

	public long getLastTick() {
		return lastTick;
	}

	public void setLastTick(long lastTick) {
		this.lastTick = lastTick;
	}

	public JFrame getM_frame() {
		return m_frame;
	}

	private void loadSprites() {

		// credit : https://erikari.itch.io/elements-supremacy-assets
		File imageFile = new File("images/character.png");

		File items = new File("images/items.png");

		try {
			BufferedImage m_items = ImageIO.read(items);
			m_personnage = ImageIO.read(imageFile);
			splitSprite(m_items);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

	}

	private void splitSprite(BufferedImage m_items) {
		int m_ncols = 4;
		int m_nrows = 3;
		int width = m_items.getWidth(null);
		int height = m_items.getHeight(null);
		BufferedImage[] m_listItems = new BufferedImage[m_nrows * m_ncols];
		int m_w = width / m_ncols;
		int m_h = height / m_nrows;
		for (int i = 0; i < m_nrows; i++) {
			for (int j = 0; j < m_ncols; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_listItems[(i * m_ncols) + j] = m_items.getSubimage(x, y, m_w, m_h);
			}
		}
		m_obstacle = m_listItems[2];
		m_Blue = m_listItems[9]; 
		m_Red = m_listItems[3]; 
		m_BlockBlue = m_listItems[0]; 
		m_BlockGray = m_listItems[1]; 
		m_thunder = m_listItems[4];
		m_stop = m_listItems[7];
		m_item = m_listItems[8];
		m_recharge = m_listItems[6];
		m_portal = m_listItems[5];
		m_transparent = m_listItems[10];
		
	}

	private void initPortal() {
		int x, y;
		Random rand = new Random();
		do {
			x = rand.nextInt(MesOptions.nbCol - 1);
			y = rand.nextInt(MesOptions.nbLigne - 1);
		} while (plateau[x][y].isOccuped());
		Portal p = new Portal(x, y, m_portal);
		this.setPortal(p);
		plateau[x][y].setE(p);
		plateau[x][y].setOccuped(true);
		plateau[x][y].setRefresh(true);
	}

	private void initObstacle() {
		boolean diff = true;
		int[] tab_x = new int[MesOptions.nb_obstacles];
		int[] tab_y = new int[MesOptions.nb_obstacles];
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
		} while (compteur != MesOptions.nb_obstacles);
		for (int i = 0; i < MesOptions.nb_obstacles; i++) {
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
			player1.canMove(plateau);
			player1.step(now);

			player2.canMove(plateau);
			player2.step(now);

			Iterator it;
			if (!j1_zbire.isEmpty()) {
				it = j1_zbire.iterator();
				while (it.hasNext()) {
					Zbire z = (Zbire) it.next();
					if (!z.life()) {
						plateau[z.getX()][z.getY()].setE(null);
						j1_zbire.remove(z);
					} else {
						z.step(now);
					}
					plateau[z.getX()][z.getY()].setRefresh(true);
				}
			}
			if (!j2_zbire.isEmpty()) {
				it = j2_zbire.iterator();
				while (it.hasNext()) {
					Zbire z = (Zbire) it.next();
					if (!z.life()) {
						plateau[z.getX()][z.getY()].setE(null);
						j2_zbire.remove(z);
					} else {
						z.step(now);
					}
					plateau[z.getX()][z.getY()].setRefresh(true);

				}
			}

			checkBonus();
			checkItem();
			checkTP();
			checkPaint();
			checkImgBonus();
			update_plat();

			elapsed = now - lastTick;

			if (elapsed >= 1000L) {
				counter_sec++;
				if (counter_sec == 10) {
					counter_sec = 0;
					statistique.plus_Score_joueur1(score1);
					statistique.plus_Score_joueur2(score2);
					statistique.plus_index();
				}
				if (minutes != 0 && secondes == 0) {
					secondes = 60;
					minutes--;
				}
				if (minutes == 0 && secondes == 0) {
					timer = false;
				}

				else {
					secondes--;
					if (secondes < 10) {
						m_frame.time.setText(minutes + ":0" + secondes);
					} else {
						m_frame.time.setText(minutes + ":" + secondes);
					}
					m_frame.doLayout();
					popItem();
					PopPaint();

					popBonus();
					depopBonus();
					lastTick = now;
				}
			}

			afficheScore();
		}
	}

	private void checkImgBonus() {
		int speedj1 = player1.getTimeEffect();
		int speedj2 = player2.getTimeEffect();
		int freezj1 = player1.getTimeEffectFreeze();
		int freezj2 = player2.getTimeEffectFreeze();

		if (speedj1 == 0) {
			m_frame.img_eclair1.setIcon(new ImageIcon());
		}
		if (speedj2 == 0) {
			m_frame.img_eclair2.setIcon(new ImageIcon());

		}
		if (freezj1 == 0) {
			m_frame.img_stop2.setIcon(new ImageIcon());

		}
		if (freezj2 == 0) {
			m_frame.img_stop1.setIcon(new ImageIcon());

		}

	}

	private void checkTP() {
		if (plateau[player1.getX()][player1.getY()].getE() instanceof Portal) {
			Sounds.portail_sound();
			tP(player1);
		}
		if (plateau[player2.getX()][player2.getY()].getE() instanceof Portal) {
			Sounds.portail_sound();
			tP(player2);
		}

	}

	private void tP(Joueur j) {
		int x, y;
		Random rand = new Random();
		do {
			x = rand.nextInt(MesOptions.nbCol - 1);
			y = rand.nextInt(MesOptions.nbLigne - 1);
		} while (plateau[x][y].isOccuped());
		j.teleport(x, y);
		plateau[x][y].setE(j);
		plateau[x][y].setOccuped(true);
		plateau[x][y].setRefresh(true);
	}

	private void checkPaint() {
		player1.recharger(false);
		player2.recharger(false);
		if (plateau[player1.getX()][player1.getY()].getE() instanceof Recharge) {
			Sounds.charge_sound();
			Recharge r = (Recharge) plateau[player1.getX()][player1.getY()].getE();
			player1.recharger(true);
			plateau[player1.getX()][player1.getY()].setE(null);
			plateau[player1.getX()][player1.getY()].setRefresh(true);
			listRecharge.remove(r);
		}
		if (plateau[player2.getX()][player2.getY()].getE() instanceof Recharge) {
			Sounds.charge_sound();
			Recharge r = (Recharge) plateau[player2.getX()][player2.getY()].getE();
			player2.recharger(true);
			plateau[player2.getX()][player2.getY()].setE(null);
			plateau[player2.getX()][player2.getY()].setRefresh(true);
			listRecharge.remove(r);
		}
	}

	private void checkItem() {
		if (plateau[player1.getX()][player1.getY()].getE() instanceof Item_Zbire) {
			Sounds.pop_sound();
			Item_Zbire item = (Item_Zbire) plateau[player1.getX()][player1.getY()].getE();
			player1.appliquerItem(1);
			plateau[player1.getX()][player1.getY()].setE(null);
			plateau[player1.getX()][player1.getY()].setRefresh(true);
			listItem.remove(item);
			afficher_liste_sprite_zbire(player1);
		}
		if (plateau[player2.getX()][player2.getY()].getE() instanceof Item_Zbire) {
			Sounds.pop_sound();
			Item_Zbire item = (Item_Zbire) plateau[player2.getX()][player2.getY()].getE();
			player2.appliquerItem(2);
			plateau[player2.getX()][player2.getY()].setE(null);
			plateau[player2.getX()][player2.getY()].setRefresh(true);
			listItem.remove(item);
			afficher_liste_sprite_zbire(player2);

		}

	}

	private void afficher_liste_sprite_zbire(Joueur player) {
		Zbire[] zbires = player.getZbire();
		if (player == player1) {

			for (int i = 0; i < zbires.length; i++) {
				if (zbires[i] != null) {
					m_frame.bW[i].setIcon(new ImageIcon(zbires[i].m_sprites[4]));
				} else {
					m_frame.bW[i].setIcon(new ImageIcon(m_transparent));
				}
			}
		} else {
			for (int i = 0; i < zbires.length; i++) {
				if (zbires[i] != null) {
					m_frame.bE[i].setIcon(new ImageIcon(zbires[i].m_sprites[4]));
				} else {
					m_frame.bE[i].setIcon(new ImageIcon(m_transparent));
				}
			}
		}
		m_frame.doLayout();
	}

	private void checkBonus() {
		if (plateau[player1.getX()][player1.getY()].getE() instanceof no.physic.entity.Bonus) {
			Sounds.pop_sound();
			Bonus bonus = (Bonus) plateau[player1.getX()][player1.getY()].getE();
			player1.appliquerBonus(bonus, player2);
			if (bonus instanceof Speed) {
				m_frame.img_eclair1.setIcon(new ImageIcon("images/eclair_gauche.png"));
			} else {
				m_frame.img_stop1.setIcon(new ImageIcon("images/stop_gauche.png"));
			}
			m_frame.doLayout();
			statistique.plus_Joueur1_Bonus();
			plateau[player1.getX()][player1.getY()].setE(null);
			plateau[player1.getX()][player1.getY()].setRefresh(true);
			listBonus.remove(bonus);
		}
		if (plateau[player2.getX()][player2.getY()].getE() instanceof no.physic.entity.Bonus) {
			Sounds.pop_sound();
			Bonus bonus = (Bonus) plateau[player2.getX()][player2.getY()].getE();
			statistique.plus_Joueur2_Bonus();
			player2.appliquerBonus(bonus, player1);
			if (bonus instanceof Speed) {
				m_frame.img_eclair2.setIcon(new ImageIcon("images/eclair_droite.png"));
			} else {
				m_frame.img_stop2.setIcon(new ImageIcon("images/stop_droite.png"));
			}
			m_frame.doLayout();
			plateau[player2.getX()][player2.getY()].setE(null);
			plateau[player2.getX()][player2.getY()].setRefresh(true);
			listBonus.remove(bonus);
		}

	}

	private void popItem() {
		if (MesOptions.nb_max_items >= listItem.size()) {
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
		if (MesOptions.nb_max_paint >= listRecharge.size()) {
			Random rand = new Random();
			int i = rand.nextInt(MesOptions.popPaint);
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
			// pourcentage1 --> score2
			// pourcentage2 --> score1
			m_frame.pourcentage1.setText(formatter.format((score2 / MesOptions.nb_cases) * 100));
			m_frame.pourcentage2.setText(formatter.format((score1 / MesOptions.nb_cases) * 100));
			refresh_score = false;
			m_frame.doLayout();
		}

	}

	public void update_plat() {

		int last_xc = player2.getLastX();
		int last_yc = player2.getLastY();
		int xc = player2.getX();
		int yc = player2.getY();
		// char dirc = player2.getDirection();
		// char last_dirc = player2.getLast_direction();

		int last_xc1 = player1.getLastX();
		int last_yc1 = player1.getLastY();
		int x1 = player1.getX();
		int y1 = player1.getY();
		//
		// char dirc1 = player1.getDirection();
		// char last_dirc1 = player1.getLast_direction();
		//
		// if (dirc != last_dirc)
		// plateau[xc][yc].setRefresh(true);
		//
		// if (dirc1 != last_dirc1)
		// plateau[x1][y1].setRefresh(true);

		boolean condJ1 = plateau[xc][yc].getCouleur() != player2.getColor()
				|| (plateau[last_xc][last_yc].getM_couleur() != m_Blue);

		boolean condJ2 = plateau[x1][y1].getCouleur() != player1.getColor()
				|| (plateau[last_xc1][last_yc1].getM_couleur() != m_Red);

		if ((last_xc != xc || last_yc != yc) && player2.getPaintStock() != 0 && condJ1) {
			statistique.plus_Nombrecase_parcouru2();

			plateau[last_xc][last_yc].setE(null);
			plateau[last_xc][last_yc].setM_couleur(m_Blue);
			plateau[last_xc][last_yc].setRefresh(true);

			if (plateau[xc][yc].getM_couleur() == m_BlockBlue || plateau[xc][yc].getM_couleur() == m_BlockGray) {
				score2++;
				refresh_score = true;
			} else if (plateau[xc][yc].getM_couleur() == m_Red) {
				score2++;
				score1--;
				refresh_score = true;
			}
			plateau[xc][yc].setE(player2);

			plateau[xc][yc].setCouleur((Color) player2.getColor());
			player2.decreasePaintStock();
			m_frame.progresseBar2.setValue((int) (player2.getPaintStock() / (float) MesOptions.paintMax * 100));
			m_frame.doLayout();
			plateau[xc][yc].setRefresh(true);
		} else if ((last_xc != xc || last_yc != yc)) {
			plateau[last_xc][last_yc].setE(null);
			plateau[last_xc][last_yc].setRefresh(true);
			plateau[xc][yc].setE(player2);
			plateau[xc][yc].setRefresh(true);
		}

		if ((last_xc1 != x1 || last_yc1 != y1) && player1.getPaintStock() != 0 && condJ2) {
			statistique.plus_Nombrecase_parcouru1();

			plateau[last_xc1][last_yc1].setE(null);
			plateau[last_xc1][last_yc1].setM_couleur(m_Red);
			plateau[last_xc1][last_yc1].setRefresh(true);

			if (plateau[x1][y1].getM_couleur() == m_BlockBlue || plateau[x1][y1].getM_couleur() == m_BlockGray) {
				score1++;
				refresh_score = true;
			} else if (plateau[x1][y1].getM_couleur() == m_Blue) {
				score1++;
				score2--;
				refresh_score = true;
			}

			plateau[x1][y1].setE(player1);
			plateau[x1][y1].setCouleur((Color) player1.getColor());
			player1.decreasePaintStock();
			m_frame.progresseBar1.setValue((int) (player1.getPaintStock() / (float) MesOptions.paintMax * 100));
			m_frame.doLayout();

			plateau[x1][y1].setRefresh(true);
		} else if (last_xc1 != x1 || last_yc1 != y1) {
			plateau[last_xc1][last_yc1].setE(null);
			plateau[last_xc1][last_yc1].setRefresh(true);
			plateau[x1][y1].setE(player1);
			plateau[x1][y1].setRefresh(true);
		}

	}

	public void spawnzbire(Joueur j, int n, char direction) {
		if (j.getZbire()[n] != null) {
			// System.out.println("sbire " + n);
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
				// System.out.println("if2");
				if (plateau[x][y].getE() instanceof No_Physic_Entity || plateau[x][y].getE() == null) {
					// System.out.println("invoque zbire");
					j.getZbire()[n].setX(x);
					j.getZbire()[n].setY(y);
					plateau[x][y].setE(j.getZbire()[n]);

					if (j == player2) {
						statistique.plus_Nombre_zbire2();
						j2_zbire.add(j.getZbire()[n]);

					} else {
						statistique.plus_Nombre_zbire1();
						j1_zbire.add(j.getZbire()[n]);
					}

					plateau[x][y].setRefresh(true);
					j.resetZbire(n);
					afficher_liste_sprite_zbire(j);
				}
			}
		}
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	public Joueur getJ2() {
		return player2;
	}

	public Joueur getJ1() {
		return player1;
	}

	public Obstacle[] getObstacle() {
		return o;
	}

	public Case[][] getPlateau() {
		return plateau;
	}

	public Statistique getStatistique() {
		return statistique;
	}

	public boolean getTimer() {
		return timer;
	}

	public void setM_frame(GameWindow m_frame2) {
		m_frame = m_frame2;

	}

	public void hit(Joueur j) {
		char dir = j.getDirection();
		Case player2;
		Entity e;
		switch (dir) {
		case 'R':
			player2 = getC(j.x + 1, j.y);
			break;
		case 'L':
			player2 = getC(j.x - 1, j.y);
			break;
		case 'U':
			player2 = getC(j.x, j.y - 1);
			break;
		case 'D':
			player2 = getC(j.x, j.y + 1);
			break;
		default:
			player2 = null;
		}
		if (player2 != null) {
			player2.setRefresh(true);
			e = player2.getE();
			if (e != null) {
				if (e instanceof Physic_Entity) {
					Physic_Entity p_e = (Physic_Entity) e;
					j.hit(p_e);
					Sounds.hit_sound();
				}
			}
			check_case(player2);
		}
	}

	public Case getC(int x, int y) {
		if (x >= 0 && x < MesOptions.nbCol && y >= 0 && y < MesOptions.nbLigne) {
			if ((x != 0 || y != 0) && (x != MesOptions.nbCol - 1 || y != MesOptions.nbLigne - 1))
				return plateau[x][y];
		}
		return null;
	}

	public void check_case(Case player2) {
		Entity e = player2.getE();
		if (e instanceof Obstacle) {
			Obstacle o = (Obstacle) e;
			if (!(o.life()))
				player2.setE(null);
		} else if (e instanceof Joueur) {
			player2.setE(null);

		} else if (e instanceof Zbire) {
			Zbire z = (Zbire) e;
			if (!(z.life()))
				player2.setE(null);
		}
	}

	public Portal getPortal() {
		return portal;
	}

	public void setPortal(Portal portal) {
		this.portal = portal;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSecondes() {
		return secondes;
	}

	public void setSecondes(int secondes) {
		this.secondes = secondes;
	}

}
