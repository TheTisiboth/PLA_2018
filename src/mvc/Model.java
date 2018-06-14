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

	private int minutes, secondes; // duration of the game
	long elapsed, lastTick; // used to handle the calls of functions
	private int counter_sec; // used for statistiques
	boolean timer; // to know when the game is over
	private long m_lastMove; // control the speed of movements, keep in memory
								// the last move

	private String name_j1, name_j2; // name of the players 1 and 2
	private Joueur player2, player1; // players 1 and 2
	private Obstacle o[]; // list of the obstacles on the map

	LinkedList<Zbire> j1_zbire, j2_zbire; // list of sbires invoked by the 2
											// players
	LinkedList<Bonus> listBonus; // list of the bonus on the map
	LinkedList<Item_Zbire> listItem; // list of sbires items
	LinkedList<Recharge> listRecharge; // list of buckets paint for recharging
	private Portal portal; // variable for the unique portal on the map

	Case plateau[][]; // matrix of the board game

	private float score1, score2; // scores of the players 1 and 2
	private boolean refresh_score = true;

	BufferedImage m_personnage, m_obstacle, m_Blue, m_Pink, m_BlockBlue, m_BlockGray, m_thunder, m_stop, m_sbire_item,
			m_recharge, m_portal, zbires; // all the sprites images used
	public BufferedImage m_transparent; // transparent sprite (example : to
										// display the inventory of the game)

	GameWindow m_frame; // game window
	public Statistique statistique; // statistique window

	// constructor
	public Model(int perso1, int perso2) {
		lastTick = 0L;
		counter_sec = 0;

		loadSprites(); // load all the images from the sprite sheets

		score1 = 0; // init
		score2 = 0; // init

		minutes = MesOptions.min; // duration of the game in minutes
		secondes = 0; // duration of the game in secondes
		timer = true; // game in progress

		// creation of the board game
		plateau = new Case[MesOptions.nbCol][MesOptions.nbLigne];
		initPlat(plateau);

		// creation of player 1
		player2 = new Joueur(m_personnage, 12, 24, perso1, MesOptions.nbCol - 1, MesOptions.nbLigne - 1, 0.25F,
				Color.BLUE, zbires);
		plateau[MesOptions.pos_init_x_j2][MesOptions.pos_init_y_j2].setE(player2);
		plateau[MesOptions.pos_init_x_j2][MesOptions.pos_init_y_j2].setCouleur((Color) player2.getColor());
		plateau[MesOptions.pos_init_x_j2][MesOptions.pos_init_y_j2].setRefresh(true);

		// creation of player 2
		player1 = new Joueur(m_personnage, 12, 24, perso2, 0, 0, 0.25F, Color.RED, zbires);
		plateau[MesOptions.pos_init_x_j1][MesOptions.pos_init_y_j1].setE(player1);
		plateau[MesOptions.pos_init_x_j1][MesOptions.pos_init_y_j1].setCouleur((Color) player1.getColor());
		plateau[MesOptions.pos_init_x_j1][MesOptions.pos_init_y_j1].setRefresh(true);

		// list of the bonus, sbires items and bucket paint
		listBonus = new LinkedList<Bonus>();
		listItem = new LinkedList<Item_Zbire>();
		listRecharge = new LinkedList<Recharge>();

		// creation of obstacles
		o = new Obstacle[MesOptions.nb_obstacles];
		initObstacle(); // init

		// new window statistique for the end of the game
		statistique = new Statistique();

		// init of the portal
		initPortal();

		// list of the sbires invoked
		j1_zbire = new LinkedList<Zbire>();
		j2_zbire = new LinkedList<Zbire>();
	}

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

	public long getLastTick() {
		return lastTick;
	}

	public void setLastTick(long lastTick) {
		this.lastTick = lastTick;
	}

	public JFrame getM_frame() {
		return m_frame;
	}

	// load of the sprite sheets
	private void loadSprites() {

		// credits for the sprite of charactrs :
		// https://erikari.itch.io/elements-supremacy-assets
		File imageFile = new File("images/character.png");
		File items = new File("images/items.png");
		File zbireFile = new File("images/Zbires.png");

		try {
			BufferedImage m_items = ImageIO.read(items);
			m_personnage = ImageIO.read(imageFile);
			zbires = ImageIO.read(zbireFile);
			splitSprite(m_items);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

	}

	// divide the sprite sheet of items to create a matrix
	private void splitSprite(BufferedImage m_items) {
		int m_ncols = 4;
		int m_nrows = 3;
		int width = m_items.getWidth(null);
		int height = m_items.getHeight(null);
		BufferedImage[] m_listItems = new BufferedImage[m_nrows * m_ncols]; // create
																			// an
																			// array
																			// of
																			// items
		int m_w = width / m_ncols;
		int m_h = height / m_nrows;
		// division
		for (int i = 0; i < m_nrows; i++) {
			for (int j = 0; j < m_ncols; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_listItems[(i * m_ncols) + j] = m_items.getSubimage(x, y, m_w, m_h);
			}
		}

		m_obstacle = m_listItems[2]; // obstacle image
		m_Blue = m_listItems[9]; // blue splash image
		m_Pink = m_listItems[3]; // pink splash image
		m_BlockBlue = m_listItems[0]; // wall blue
		m_BlockGray = m_listItems[1]; // wall gray
		m_thunder = m_listItems[4]; // bonus acceleration
		m_stop = m_listItems[7]; // bonus freeze
		m_sbire_item = m_listItems[8]; // sbire item
		m_recharge = m_listItems[6]; // recharge paint
		m_portal = m_listItems[5]; // portal
		m_transparent = m_listItems[10]; // transparent image

	}

	// init and place the portal on a case of the board game
	private void initPortal() {
		int x, y;
		// get a random position while the case is occupied
		Random rand = new Random();
		do {
			x = rand.nextInt(MesOptions.nbCol - 1);
			y = rand.nextInt(MesOptions.nbLigne - 1);
		} while (plateau[x][y].isOccupied());

		// creation of the portal
		Portal p = new Portal(x, y, m_portal);
		// positionning the portal on the free case
		this.setPortal(p);
		plateau[x][y].setE(p);
		plateau[x][y].setOccupied(true);
		plateau[x][y].setRefresh(true);
	}

	// init and place all the obstacles on the board game
	private void initObstacle() {
		boolean diff = true;
		int[] tab_x = new int[MesOptions.nb_obstacles];
		int[] tab_y = new int[MesOptions.nb_obstacles];
		int compteur = 0;
		// getting all the position of the obstacles randomly
		do {
			diff = true;
			Random rand = new Random();
			int y = rand.nextInt(MesOptions.nbLigne);
			int x = rand.nextInt(MesOptions.nbCol);
			// if the position of the obstacle is already taken by an other
			// entity
			// the obstacle can't be placed
			for (int i = 0; i < compteur; i++) {
				if (tab_y[i] == y && tab_x[i] == x) {
					diff = false;
				}
			}
			// if the obstacle is placed on the initial position of the player 1
			// or 2
			// aka top left corner and right bottom corner
			// the obstacle can't be placed
			if ((x == 0 && y == 0) || (x == MesOptions.nbCol - 1 && y == MesOptions.nbLigne - 1)) {
				diff = false;
			}
			// if the position of the obstacle is valid, we save the position in
			// an array
			if (diff) {
				tab_y[compteur] = y;
				tab_x[compteur] = x;
				compteur++;
			}
		} while (compteur != MesOptions.nb_obstacles);

		// when we have all the position of the obstacles
		// we place them on the board game
		for (int i = 0; i < MesOptions.nb_obstacles; i++) {
			o[i] = new Obstacle(tab_x[i], tab_y[i], 3, m_obstacle);
			plateau[tab_x[i]][tab_y[i]].setE(o[i]);
			plateau[tab_x[i]][tab_y[i]].setRefresh(true);
			plateau[tab_x[i]][tab_y[i]].setCouleur(o[i].getCouleur());
		}
	}

	// place all the cells of the board game
	// alternation of blue and gray blocks
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
			// is the player1 able to move?
			// then step
			player1.canMove(plateau);
			player1.step(now);

			// is the player2 able to move?
			// then step
			player2.canMove(plateau);
			player2.step(now);

			// management of the sbires
			long elapsed = now - m_lastMove;
			if (elapsed > 200L) {
				Iterator it;

				// player 1
				if (!j1_zbire.isEmpty()) {
					it = j1_zbire.iterator();
					Zbire z;
					while (it.hasNext()) {
						z = (Zbire) it.next();
						// if the sbire has no move anymore
						if (!z.life()) {
							it.remove(); // it disappears
							plateau[z.getX()][z.getY()].setE(null);
							j1_zbire.remove(z);
						} else {
							z.step(now); // otherwise, it moves
						}
						plateau[z.getX()][z.getY()].setRefresh(true);
					}
				}
				// player 2
				if (!j2_zbire.isEmpty()) {
					it = j2_zbire.iterator();
					Zbire z;
					while (it.hasNext()) {
						z = (Zbire) it.next();
						// if the sbire has no move anymore
						if (!z.life()) {
							it.remove(); // it disappears
							plateau[z.getX()][z.getY()].setE(null);
							j2_zbire.remove(z);
						} else {
							z.step(now); // otherwise, it moves
						}
						plateau[z.getX()][z.getY()].setRefresh(true);
					}
				}
				m_lastMove = now;
			}

			checkBonus(); // is there a bonus on the cell?
			checkItem(); // is there an item on the cell?
			checkTP(); // is the player teleporting?
			checkPaint(); // is there a bucket paint on the cell?
			checkImgBonus(); // display images when bonus are taken
			update_plat();

			elapsed = now - lastTick;

			if (elapsed >= 1000L) {
				counter_sec++;
				// manage the stats every 10 seconds
				if (counter_sec == 10) {
					counter_sec = 0;
					statistique.plus_Score_joueur1(score1);
					statistique.plus_Score_joueur2(score2);
					statistique.plus_index();
				}
				// manage the timer
				if (minutes != 0 && secondes == 0) {
					secondes = 60;
					minutes--;
				}
				// end of the game
				if (minutes == 0 && secondes == 0) {
					timer = false;
				}

				// manage the display of the timer
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

	// manage the display of the bonus
	private void checkImgBonus() {
		int speedj1 = player1.getTimeEffect();
		int speedj2 = player2.getTimeEffect();
		int freezej1 = player1.getTimeEffectFreeze();
		int freezej2 = player2.getTimeEffectFreeze();

		// if player 1 takes the speeder, we display the image
		if (speedj1 == 0) {
			m_frame.img_eclair1.setIcon(new ImageIcon());
		}
		// if player 2 takes the speeder, we display the image
		if (speedj2 == 0) {
			m_frame.img_eclair2.setIcon(new ImageIcon());
		}
		// if player 1 takes the freezer, we display the image
		if (freezej1 == 0) {
			m_frame.img_stop2.setIcon(new ImageIcon());
		}
		// if player 2 takes the freezer, we display the image
		if (freezej2 == 0) {
			m_frame.img_stop1.setIcon(new ImageIcon());
		}
	}

	// check if the player is teleporting with the portal
	private void checkTP() {
		if (plateau[player1.getX()][player1.getY()].getE() instanceof Portal) {
			Sounds.portail_sound();
			tP(player1); // teleporte the player1
		}
		if (plateau[player2.getX()][player2.getY()].getE() instanceof Portal) {
			Sounds.portail_sound();
			tP(player2); // teleporte the player2
		}
	}

	// manage the teleportation
	private void tP(Joueur j) {
		int x, y;
		// search for a case which is not occupied
		Random rand = new Random();
		do {
			x = rand.nextInt(MesOptions.nbCol - 1);
			y = rand.nextInt(MesOptions.nbLigne - 1);
		} while (plateau[x][y].isOccupied());
		j.teleport(x, y);
		plateau[x][y].setE(j);
		plateau[x][y].setOccupied(true);
		plateau[x][y].setRefresh(true);
	}

	// check if the player get a bucket paint to recharge
	private void checkPaint() {
		player1.recharger(false);
		player2.recharger(false);
		// if player 1 get a bucket paint
		if (plateau[player1.getX()][player1.getY()].getE() instanceof Recharge) {
			Sounds.charge_sound();
			Recharge r = (Recharge) plateau[player1.getX()][player1.getY()].getE();
			player1.recharger(true);
			plateau[player1.getX()][player1.getY()].setE(null);
			plateau[player1.getX()][player1.getY()].setRefresh(true);
			listRecharge.remove(r);
		}
		// if player 2 get a bucket paint
		if (plateau[player2.getX()][player2.getY()].getE() instanceof Recharge) {
			Sounds.charge_sound();
			Recharge r = (Recharge) plateau[player2.getX()][player2.getY()].getE();
			player2.recharger(true);
			plateau[player2.getX()][player2.getY()].setE(null);
			plateau[player2.getX()][player2.getY()].setRefresh(true);
			listRecharge.remove(r);
		}
	}

	// check if the player get a sbire item
	private void checkItem() {
		// if player 1 get a sbire item
		if (plateau[player1.getX()][player1.getY()].getE() instanceof Item_Zbire) {
			Sounds.pop_sound();
			Item_Zbire item = (Item_Zbire) plateau[player1.getX()][player1.getY()].getE();
			player1.appliquerItem(1);
			plateau[player1.getX()][player1.getY()].setE(null);
			plateau[player1.getX()][player1.getY()].setRefresh(true);
			listItem.remove(item);
			afficher_liste_sprite_zbire(player1);
		}
		// if player 2 get a sbire item
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

	// display the sbires on left and right panels
	private void afficher_liste_sprite_zbire(Joueur player) {
		Zbire[] zbires = player.getZbire();
		if (player == player1) {
			for (int i = 0; i < zbires.length; i++) {
				// if player 1 has a sbire : display
				if (zbires[i] != null) {
					m_frame.bW[i].setIcon(new ImageIcon(zbires[i].m_sprites[4]));
					m_frame.nW[i].setText(MesOptions.automates_j1.get(i));
				}
				// otherwise : display transparent image
				else {
					m_frame.bW[i].setIcon(new ImageIcon(m_transparent));
					m_frame.nW[i].setText("");

				}
			}
		} else {
			for (int i = 0; i < zbires.length; i++) {
				// if player 2 has a sbire : display
				if (zbires[i] != null) {
					m_frame.bE[i].setIcon(new ImageIcon(zbires[i].m_sprites[4]));
					m_frame.nE[i].setText(MesOptions.automates_j1.get(i));

				}
				// otherwise : display transparent image
				else {
					m_frame.bE[i].setIcon(new ImageIcon(m_transparent));
					m_frame.nE[i].setText("");
				}
			}
		}
		m_frame.doLayout();
	}

	// check if a player get a bonus
	private void checkBonus() {
		if (plateau[player1.getX()][player1.getY()].getE() instanceof no.physic.entity.Bonus) {
			Sounds.pop_sound();
			Bonus bonus = (Bonus) plateau[player1.getX()][player1.getY()].getE();
			player1.appliquerBonus(bonus, player2); // apply the bonus on his
													// behaviour
			if (bonus instanceof Speed) {
				m_frame.img_eclair1.setIcon(new ImageIcon("images/eclair_gauche.png")); // display
																						// of
																						// the
																						// thunder
			} else {
				m_frame.img_stop1.setIcon(new ImageIcon("images/stop_gauche.png")); // display
																					// of
																					// the
																					// stop
			}
			m_frame.doLayout();
			statistique.plus_Joueur1_Bonus(); // update bonus stats
			plateau[player1.getX()][player1.getY()].setE(null);
			plateau[player1.getX()][player1.getY()].setRefresh(true);
			listBonus.remove(bonus);
		}
		if (plateau[player2.getX()][player2.getY()].getE() instanceof no.physic.entity.Bonus) {
			Sounds.pop_sound();
			Bonus bonus = (Bonus) plateau[player2.getX()][player2.getY()].getE();
			player2.appliquerBonus(bonus, player1);
			if (bonus instanceof Speed) {
				m_frame.img_eclair2.setIcon(new ImageIcon("images/eclair_droite.png")); // display
																						// of
																						// the
																						// thunder
			} else {
				m_frame.img_stop2.setIcon(new ImageIcon("images/stop_droite.png")); // display
																					// of
																					// the
																					// stop
			}
			m_frame.doLayout();
			statistique.plus_Joueur2_Bonus(); // update bonus stats
			plateau[player2.getX()][player2.getY()].setE(null);
			plateau[player2.getX()][player2.getY()].setRefresh(true);
			listBonus.remove(bonus);
		}

	}

	// pop of item sbire
	private void popItem() {
		if (MesOptions.nb_max_items >= listItem.size()) {
			Random rand = new Random();
			int i = rand.nextInt(MesOptions.popItem);
			if (i < 1) {
				boolean occuped = true;
				int col, ligne;
				while (occuped) {
					// new random position
					col = rand.nextInt(MesOptions.nbCol);
					ligne = rand.nextInt(MesOptions.nbLigne);
					// if the case is not occupied
					if (!plateau[col][ligne].isOccupied()) {
						// we create a new sbire and place it on the board game
						Item_Zbire item = new Item_Zbire(col, ligne, m_sbire_item);
						plateau[col][ligne].setE(item);
						plateau[col][ligne].setRefresh(true);
						listItem.add(item);
						occuped = false;
					}
				}
			}
		}
	}

	// pop of a bucket paint to recharge
	private void PopPaint() {
		if (MesOptions.nb_max_paint >= listRecharge.size()) {
			Random rand = new Random();
			int i = rand.nextInt(MesOptions.popPaint);
			if (i < 1) {
				boolean occuped = true;
				int col, ligne;
				while (occuped) {
					// new random position
					col = rand.nextInt(MesOptions.nbCol);
					ligne = rand.nextInt(MesOptions.nbLigne);
					// if the case is not occupied
					if (!plateau[col][ligne].isOccupied()) {
						Recharge r = new Recharge(col, ligne, m_recharge);
						// we create a new recharge and place it on the board
						// game
						plateau[col][ligne].setE(r);
						plateau[col][ligne].setRefresh(true);
						listRecharge.add(r);
						occuped = false;
					}
				}
			}
		}

	}

	// depop of the bonus
	private void depopBonus() {
		if (!listBonus.isEmpty()) {
			LinkedList<Bonus> used = (LinkedList<Bonus>) listBonus.clone();
			for (Iterator iterator = used.iterator(); iterator.hasNext();) {
				Bonus b = (Bonus) iterator.next();
				b.step();
				if (b.getDurationPop() <= 0) {
					listBonus.remove(b);
					plateau[b.getX()][b.getY()].setE(null);
					plateau[b.getX()][b.getY()].setRefresh(true);
				}
			}
		}
	}

	// pop of the bonus
	private void popBonus() {
		Random rand = new Random();
		int i = rand.nextInt(MesOptions.popBonus);
		if (i < 1) {
			boolean occuped = true;
			int col, ligne;
			while (occuped) {
				// new random position
				col = rand.nextInt(MesOptions.nbCol);
				ligne = rand.nextInt(MesOptions.nbLigne);
				// if the cell is occupied
				if (!plateau[col][ligne].isOccupied()) {
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

	// update the matrix of cells according to the position of each player
	public void update_plat() {
		// position of player 2
		int last_xc = player2.getLastX();
		int last_yc = player2.getLastY();
		int xc = player2.getX();
		int yc = player2.getY();
		char dirc = player2.getDirection();
		char last_dirc = player2.getLast_direction();

		// position of player 1
		int last_xc1 = player1.getLastX();
		int last_yc1 = player1.getLastY();
		int x1 = player1.getX();
		int y1 = player1.getY();
		char dirc1 = player1.getDirection();
		char last_dirc1 = player1.getLast_direction();

		// update the sprite image depending on the position and direction
		if (dirc != last_dirc)
			plateau[xc][yc].setRefresh(true);

		if (dirc1 != last_dirc1)
			plateau[x1][y1].setRefresh(true);

		boolean condJ1 = plateau[xc][yc].getCouleur() != player2.getColor()
				|| (plateau[last_xc][last_yc].getM_couleur() != m_Blue);

		boolean condJ2 = plateau[x1][y1].getCouleur() != player1.getColor()
				|| (plateau[last_xc1][last_yc1].getM_couleur() != m_Pink);

		// update the matrix if player 2 has moved
		if ((last_xc != xc || last_yc != yc) && player2.getPaintStock() != 0 && condJ1) {
			statistique.plus_Nbcases_parcourues2(); // update stats
			plateau[last_xc][last_yc].setE(null);
			plateau[last_xc][last_yc].setM_couleur(m_Blue);
			plateau[last_xc][last_yc].setRefresh(true);
			// update score
			if (plateau[xc][yc].getM_couleur() == m_BlockBlue || plateau[xc][yc].getM_couleur() == m_BlockGray) {
				score2++;
				refresh_score = true;
			} else if (plateau[xc][yc].getM_couleur() == m_Pink) {
				score2++;
				score1--;
				refresh_score = true;
			}
			plateau[xc][yc].setE(player2);
			// update
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

		// update the matrix if player 1 has moved
		if ((last_xc1 != x1 || last_yc1 != y1) && player1.getPaintStock() != 0 && condJ2) {
			statistique.plus_Nbcases_parcourues1();
			plateau[last_xc1][last_yc1].setE(null);
			plateau[last_xc1][last_yc1].setM_couleur(m_Pink);
			plateau[last_xc1][last_yc1].setRefresh(true);
			// update score
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

	// display sbires
	public void spawnzbire(Joueur j, int n, char direction) {
		if (j.getZbire()[n] != null) {
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
				if (plateau[x][y].getE() instanceof No_Physic_Entity || plateau[x][y].getE() == null) {
					if (plateau[x][y].getE() instanceof Portal)
						return;
					j.getZbire()[n].setX(x);
					j.getZbire()[n].setY(y);
					plateau[x][y].setE(j.getZbire()[n]);

					// update stats player 2
					if (j == player2) {
						statistique.plus_Nombre_zbire2();
						j2_zbire.add(j.getZbire()[n]);
					}
					// update stats player 1
					else {
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
		Case c;
		Entity e;
		// get the position of the cell in the front
		switch (dir) {
		case 'R':
			c = getC(j.x + 1, j.y);
			break;
		case 'L':
			c = getC(j.x - 1, j.y);
			break;
		case 'U':
			c = getC(j.x, j.y - 1);
			break;
		case 'D':
			c = getC(j.x, j.y + 1);
			break;
		default:
			c = null;
		}
		// if the case belongs to the board game
		if (c != null) {
			c.setRefresh(true);
			// pick the entity in this cell
			e = c.getE();
			if (e != null) {
				// if the entity is physical
				if (e instanceof Physic_Entity) {
					Physic_Entity p_e = (Physic_Entity) e;
					j.hit(p_e); // we hit
					Sounds.hit_sound();
				}
			}
			check_case(c);
		}
	}

	public Case getC(int x, int y) {
		if (x >= 0 && x < MesOptions.nbCol && y >= 0 && y < MesOptions.nbLigne) {
			if ((x != 0 || y != 0) && (x != MesOptions.nbCol - 1 || y != MesOptions.nbLigne - 1))
				return plateau[x][y];
		}
		return null;
	}

	// check what's on the cell
	public void check_case(Case c) {
		Entity e = c.getE();
		// the cell has an obstacle
		if (e instanceof Obstacle) {
			Obstacle o = (Obstacle) e;
			if (!(o.life()))
				c.setE(null);	// no more life : dispappear
		} 
		// the cell has a player
		else if (e instanceof Joueur) {
			c.setE(null);		// no more life : dispappear
		} 
		// the cell has a sbire
		else if (e instanceof Zbire) {
			Zbire z = (Zbire) e;
			if (!(z.life()))
				c.setE(null); 	// no more life : dispappear
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
