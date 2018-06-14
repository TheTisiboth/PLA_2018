package physic.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import interpreter.Automaton_I;
import mvc.Case;

import mvc.Entity;
import mvc.Model;
import mvc.Sounds;

import mvc.MesOptions;
import mvc.Model;
import no.physic.entity.Bonus;
import no.physic.entity.Freeze;
import no.physic.entity.Item_Zbire;
import no.physic.entity.No_Physic_Entity;
import no.physic.entity.Portal;
import no.physic.entity.Recharge;
import no.physic.entity.Speed;

public class Joueur extends Physic_Entity {

	private int last_x, last_y; // retiens la derniere position du joueur
	private int m_w, m_h, m_idx; // servit pour le chargement des sprites
	private int speed, timeEffect; // gestion des bonnus
	private int paintStock; // stock de peinture
	private int m_personnalisation, m_nrows, m_ncols, diameter; // personalisation des sprites
	private int step = 1; // pas en case de chaque mouvement
	private int pos_init_x, pos_init_y; // position initiale du zbire
	private int recharge = 10; // value de la recharge de peinture sur chaque item

	private Color couleur; // couleur du joueur
	private Zbire z[]; // inventaire de zbires du joueur

	private BufferedImage m_sprite; // matrice de sprite du joueur
	private BufferedImage[] m_sprites; // tableau des sprites du joueur
	private BufferedImage m_zbires; // matrice de sprite des zbire

	private boolean moveable;// indique si le joueur peut bouger
	private boolean reload; // utilisé pour la recharge de peinture avec une case de décalage
	boolean inMovement; // indique si la touche de mouvement est enfoncée ou non

	private long m_lastMove; // temps du dernier mouvmeent du joueur

	char direction, last_direction;
	private int timeEffectFreeze;
	private Automaton_I automaton;
	String etatcourant;

	Model m_model;

	public Joueur(Model model, BufferedImage sprite, int rows, int columns, int personnalisation, int x, int y,
			float scale, Color couleur, Automaton_I automaton_I, BufferedImage zbires) {
		super(x, y);
		this.automaton = automaton_I;
		if (automaton_I != null) {
			etatcourant = automaton.entry;
		}

		m_sprite = sprite;
		m_ncols = columns;
		m_nrows = rows;
		last_x = x;
		last_y = y;
		diameter = 34;
		moveable = true;
		timeEffect = 0;
		speed = 1;
		m_personnalisation = personnalisation * 48;
		this.couleur = couleur;
		splitSprite();
		paintStock = MesOptions.paintMax;
		z = new Zbire[4];

		pos_init_x = x;
		pos_init_y = y;
		direction = last_direction = 'D';
		m_model = model;
		m_idx = 45 + m_personnalisation;

		m_zbires = zbires;

	}

	// divide the sprite image
	void splitSprite() {
		int width = m_sprite.getWidth(null);
		int height = m_sprite.getHeight(null);
		m_sprites = new BufferedImage[m_nrows * m_ncols];
		m_w = width / m_ncols;
		m_h = height / m_nrows;
		for (int i = 0; i < m_nrows; i++) {
			for (int j = 0; j < m_ncols; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * m_ncols) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
			}
		}
	}

	public void paint(Graphics g) {
		Image img = m_sprites[m_idx];
		g.drawImage(img, x * MesOptions.taille_case + (MesOptions.taille_case / 4), y * MesOptions.taille_case,
				MesOptions.taille_case / 2, MesOptions.taille_case, null);
	}

	public void canMove(Case[][] c) {
		if (inMovement) {
			// charge the next case
			int nextX, nextY;

			// goes down
			if (y < MesOptions.nbLigne - 1 && direction == 'D') {
				nextX = x;
				nextY = y + 1;
			}
			// goes up
			else if (y > 0 && direction == 'U') {
				nextX = x;
				nextY = y - 1;
			}
			// goes to the right
			else if (x < MesOptions.nbCol - 1 && direction == 'R') {
				nextX = x + 1;

				nextY = y;
			}
			// goes to the left
			else if (x > 0 && direction == 'L') {
				nextX = x - 1;
				nextY = y;
			}
			// goes out of bounds
			else {
				return;
			}

			// is the case occupied?
			if (c[nextX][nextY].isOccupied()) {
				// if yes, is there a bonus above?
				if (!c[nextX][nextY].getE().colision) {
					moveable = true;
				}
				// if there's not a bonus, we can't go there
				else {
					moveable = false;
				}
			} else {
				// if the case is not occupied, let's go on it
				moveable = true;
			}
		}

	}

	// apply bonus
	public void appliquerBonus(Bonus b, Joueur adverse) {
		// bonus = speeder
		if (b instanceof Speed) {
			speed = 2;
			timeEffect = 10; // 10 cases
		}
		// bonus = freezer
		else if (b instanceof Freeze) {
			if (adverse != null) {
				adverse.speed = 0; // block the opponent
				adverse.timeEffectFreeze = 20;
			}
		}
	}

	// charge the paint when empty
	public void recharger(boolean reload) {
		// charge the paint for next game
		if (reload) {
			this.reload = true;
		} // charge the paint now
		else if (this.reload) {
			paintStock += MesOptions.recharge;
			if (paintStock > MesOptions.paintMax) {
				paintStock = MesOptions.paintMax;
			}
			this.reload = false;
		}
	}

	// apply item zbire
	public void appliquerItem(int joueur, LinkedList<String> listAut, BufferedImage obs, BufferedImage spl,
			BufferedImage cP, BufferedImage cI) {

		Random rand = new Random();
		int i = rand.nextInt(100);
		Zbire zbire = null;
		Automaton_I aut;
		String nom;

		if (i >= 0 && i < 25) {
			if (z[0] == null) {
				// System.out.println("zbire : " + 1);
				nom = listAut.get(0);
				aut = search(nom);
				zbire = new Zbire(m_model, -1, -1, this.couleur, 5, 0, 0.50F, joueur, aut, aut.entry, obs, spl, cP, cI, m_zbires);
				z[0] = zbire;
			}

		} else if (i >= 25 && i < 50) {
			if (z[1] == null) {
				// System.out.println("zbire : " + 2);
				nom = listAut.get(1);
				aut = search(nom);
				zbire = new Zbire(m_model, -1, -1, this.couleur, 10, 1, 0.50F, joueur, aut, aut.entry, obs, spl, cP, cI, m_zbires);
				z[1] = zbire;
			}

		} else if (i >= 50 && i < 75) {
			if (z[2] == null) {
				// System.out.println("zbire : " + 3);
				nom = listAut.get(2);
				aut = search(nom);
				zbire = new Zbire(m_model, -1, -1, this.couleur, 10, 2, 0.50F, joueur, aut, aut.entry, obs, spl, cP, cI, m_zbires);
				z[2] = zbire;
			}

		} else {
			if (z[3] == null) {
				// System.out.println("zbire : " + 4);
				nom = listAut.get(3);
				aut = search(nom);
				zbire = new Zbire(m_model, -1, -1, this.couleur, 20, 3, 0.50F, joueur, aut, aut.entry, obs, spl, cP, cI, m_zbires);
				z[3] = zbire;
			}
		}
	}

	Automaton_I search(String nom) {
		ListIterator<Automaton_I> Iter = MesOptions.automates.listIterator();
		Automaton_I aut;

		while (Iter.hasNext()) {
			aut = Iter.next();
			if (aut.name.equals(nom)) {
				return aut;
			}
		}
		return null;
	}

	public void step(long now, Case[][] plateau) {
		long elapsed = now - m_lastMove;
		last_x = x;
		last_y = y;

		// On change la durée avant la prochaine action selon le bonus
		long time = 150L;

		// case 1 : Freeze
		if (elapsed > time && speed < 1 && timeEffectFreeze > 0) {
			timeEffectFreeze--;
			m_lastMove = now;
			elapsed = now - m_lastMove;

		} // case 2 : Speed
		else if (speed > 1 && elapsed > time / speed && timeEffect > 0) {
			time /= speed;
		}

		if (automaton != null) {
			if (elapsed > 2 * time) {
				automaton.step(this, etatcourant, plateau);
				m_lastMove = now;
			}
		}

		if (inMovement && elapsed > time && moveable) {

			if (direction == 'R' && x < MesOptions.nbCol - 1) {
				x += step;
				m_idx = (m_idx == 1 + m_personnalisation) ? 4 + m_personnalisation : 1 + m_personnalisation;
			} else if (direction == 'L' && x > 0) {
				x -= step;
				m_idx = (m_idx == 25 + m_personnalisation) ? 28 + m_personnalisation : 25 + m_personnalisation;
			} else if (direction == 'D' && y < MesOptions.nbLigne - 1) {
				y += step;
				m_idx = (m_idx == 42 + m_personnalisation) ? 44 + m_personnalisation : 42 + m_personnalisation;
			} else if (direction == 'U' && y > 0) {
				y -= step;
				m_idx = (m_idx == 12 + m_personnalisation) ? 13 + m_personnalisation : 12 + m_personnalisation;

			}

			m_lastMove = now;
			if (timeEffect > 0) {
				timeEffect--;
			}
		} else {
			switch (direction) {
			case 'R':
				m_idx = (m_idx == 1 + m_personnalisation) ? 4 + m_personnalisation : 1 + m_personnalisation;
				break;
			case 'L':
				m_idx = (m_idx == 25 + m_personnalisation) ? 28 + m_personnalisation : 25 + m_personnalisation;
				break;
			case 'D':
				m_idx = (m_idx == 42 + m_personnalisation) ? 44 + m_personnalisation : 42 + m_personnalisation;
				break;
			case 'U':
				m_idx = (m_idx == 12 + m_personnalisation) ? 13 + m_personnalisation : 12 + m_personnalisation;
				break;
			}
		}
	}

	// hit button
	public void hit(Physic_Entity e) {
		// hit an obstacle
		if (e instanceof Obstacle) {
			Obstacle o = (Obstacle) e;
			o.reduce_life(); // the obstacle disappears
		}
		// hit a player
		else if (e instanceof Joueur) {
			Joueur j = (Joueur) e;
			// the player is sent back to its initial position
			j.x = j.getPosInit_x();
			j.y = j.getPosInit_y();
		}
		// hit a sbire
		else {
			Zbire z = (Zbire) e;
			z.reduce_nb_case(); // reduces its lifetime
		}
	}

	// GETTER SETTER

	public int getLastX() {
		return last_x;
	}

	public int getX() {
		return x;
	}

	public int getLastY() {
		return last_y;
	}

	public int getY() {
		return y;
	}

	public int getDiameter() {
		return diameter;
	}

	public char getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
		last_direction = this.direction;
		this.direction = direction;
		inMovement = true;
	}

	public char getLast_direction() {
		return last_direction;
	}

	public void setMovement(boolean b) {
		inMovement = b;
	}

	public Color getColor() {
		return couleur;
	}

	public boolean isInMovement() {
		return inMovement;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int i) {
		speed = i;
	}

	public int getTimeEffect() {
		return timeEffect;
	}

	public void decreaseTimeEffect() {
		timeEffect--;
	}

	public int getPaintStock() {
		return paintStock;
	}

	public Zbire[] getZbire() {
		return z;
	}

	public void resetZbire(int n) {
		z[n] = null;
	}

	public void decreasePaintStock() {
		if (paintStock > 0)
			paintStock--;
	}

	public int getPosInit_x() {
		return pos_init_x;
	}

	public int getPosInit_y() {
		return pos_init_y;
	}

	public void teleport(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getTimeEffectFreeze() {
		return timeEffectFreeze;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean gotPower() {
		return paintStock > 0;
	}

	@Override
	public boolean key(String cle) {
		return false;
	}

	@Override
	public boolean myDir(String dir) {
		switch (dir) {
		case "N":
			return direction == 'U';
		case "S":
			return direction == 'D';
		case "E":
			return direction == 'R';
		case "O":
			return direction == 'L';

		default:
			return false;
		}
	}

	@Override
	public boolean cell(String dir, String entity, Case[][] plateau) {
		Case c = null;

		switch (dir) {
		case "N":
			c = getC(x, y - 1, plateau);
			break;
		case "S":
			c = getC(x, y + 1, plateau);
			break;
		case "E":
			c = getC(x + 1, y, plateau);
			break;
		case "O":
			c = getC(x - 1, y, plateau);
			break;
		case "F":
			switch (direction) {
			case 'U':
				c = getC(x, y - 1, plateau);
				break;
			case 'D':
				c = getC(x, y + 1, plateau);
				break;
			case 'R':
				c = getC(x + 1, y, plateau);
				break;
			case 'L':
				c = getC(x - 1, y, plateau);
				break;
			default:
				System.out.println("Pb direction joueur");
				break;
			}
			break;
		case "B":
			switch (direction) {
			case 'U':
				c = getC(x, y + 1, plateau);
				break;
			case 'D':
				c = getC(x, y - 1, plateau);
				break;
			case 'R':
				c = getC(x - 1, y, plateau);
				break;
			case 'L':
				c = getC(x + 1, y, plateau);
				break;
			default:
				System.out.println("Pb direction sbire");
				break;
			}
			break;
		case "L":
			switch (direction) {
			case 'U':
				c = getC(x - 1, y, plateau);
				break;
			case 'D':
				c = getC(x + 1, y, plateau);
				break;
			case 'R':
				c = getC(x, y - 1, plateau);
				break;
			case 'L':
				c = getC(x, y + 1, plateau);
				break;
			default:
				System.out.println("Pb direction sbire");
				break;
			}
			break;
		case "R":
			switch (direction) {
			case 'U':
				c = getC(x + 1, y, plateau);
				break;
			case 'D':
				c = getC(x - 1, y, plateau);
				break;
			case 'R':
				c = getC(x, y + 1, plateau);
				break;
			case 'L':
				c = getC(x, y - 1, plateau);
				break;
			default:
				System.out.println("Pb direction sbire");
				break;
			}
			break;
		default:
			return false;
		}

		if (c == null)
			return false;
		else {
			return entite(c, entity);
		}
	}

	private boolean entite(Case c, String entity) {
		/*
		 * 
		 * V : Vide T : Sbire à nous A : Joueur d'en face (Adversaire) D : Sbire de
		 * l'autre jour P : Sbire à ramasser J : Recharge de peinture G : Mur M : Bonus
		 * (accélération et freeze)
		 */
		mvc.Entity e = c.getE();
		switch (entity) {
		case "V":
			return e == null;
		case "T":
			if (e instanceof Zbire) {
				if (((Zbire) e).getCouleur() == couleur) {
					return true;
				}
			}
			return false;
		case "A":
			if (e instanceof Joueur) {
				return couleur != ((Joueur) e).getColor();
			} else {
				return false;
			}
		case "D":
			if (e instanceof Zbire) {
				return couleur != ((Zbire) e).getCouleur();
			} else {
				return false;
			}
		case "P":
			return (e instanceof Item_Zbire);
		case "J":
			return (e instanceof Recharge);
		case "G":
			return (e instanceof Obstacle);
		case "M":
			return (e instanceof Bonus);
		default:
			return false;
		}
	}

	public Case getC(int x, int y, Case[][] plateau) {
		if (x >= 0 && x < MesOptions.nbCol && y >= 0 && y < MesOptions.nbLigne) {
			return plateau[x][y];
		}
		return null;
	}

	@Override
	public boolean closest(String dir, String entity, Case[][] plateau) {

		String m_dir = null;
		int nb_case = 0;
		int min = 100;
		boolean trouve = false;
		int x_recherche = x;
		int y_recherche = y;
		Case c;

		// recherche dans la direction (carré nord) nord indice 0
		for (int i = 0; i < y_recherche; i++) {
			nb_case = y_recherche - i + x_recherche;
			for (int j = 0; j < MesOptions.nbCol - 1; j++) {

				if (j < x_recherche) {
					nb_case--;
				} else {
					nb_case++;
				}

				c = plateau[j][i];
				trouve = entite(c, entity);

				if (trouve && nb_case < min) {
					m_dir = "N";
					min = nb_case;
				}
			}

		}

		// recherche dans la direction (carré sud) sud indice 1
		for (int i = y_recherche; i < MesOptions.nbLigne; i++) {
			nb_case = MesOptions.nbLigne - i + x_recherche;
			for (int j = 0; j < MesOptions.nbCol - 1; j++) {

				if (j < x_recherche) {
					nb_case--;
				} else {
					nb_case++;
				}

				c = plateau[j][i];
				trouve = entite(c, entity);

				if (trouve && nb_case < min) {
					m_dir = "S";
					min = nb_case;
				}
			}

		}

		// recherche dans la direction est indice 2
		nb_case = 0;
		while (x_recherche < MesOptions.nbCol - 1) {
			x_recherche++;
			nb_case++;
			c = plateau[x_recherche][y_recherche];
			trouve = entite(c, entity);
			if (trouve && nb_case < min) {
				min = nb_case;
				m_dir = "E";
			}
		}

		// recherche dans la direction ouest indice 3
		x_recherche = x;
		nb_case = 0;
		while (x_recherche > 0) {
			x_recherche--;
			nb_case++;
			c = plateau[x_recherche][y_recherche];
			trouve = entite(c, entity);
			if (trouve && nb_case < min) {
				min = nb_case;
				m_dir = "O";
			}
		}

		if (m_dir == null)
			return false;

		switch (dir) {
		case "N":	
		case "S":
		case "E":
		case "O":
			return dir.equals(m_dir);
		case "F":
			switch (direction) {
			case 'U':
				return m_dir.equals("N");
			case 'D':
				return m_dir.equals("S");
			case 'L':
				return m_dir.equals("O");
			case 'R':
				return m_dir.equals("E");
			}
		case "B":
			switch (direction) {
			case 'U':
				return m_dir.equals("S");
			case 'D':
				return m_dir.equals("N");
			case 'R':
				return m_dir.equals("O");
			case 'L':
				return m_dir.equals("E");
			default:
				System.out.println("Pb direction sbire");
				break;
			}
		case "L":
			switch (direction) {
			case 'U':
				return m_dir.equals("O");
			case 'D':
				return m_dir.equals("E");
			case 'R':
				return m_dir.equals("N");
			case 'L':
				return m_dir.equals("S");
			default:
				System.out.println("Pb direction sbire");
				break;
			}
		case "R":
			switch (direction) {
			case 'U':
				return m_dir.equals("E");
			case 'D':
				return m_dir.equals("O");
			case 'R':
				return m_dir.equals("S");
			case 'L':
				return m_dir.equals("N");
			default:
				System.out.println("Pb direction sbire");
				break;
			}
		default:
			return false;
		}
	}

	@Override
	public boolean gotStuff() {
		return z[0] != null || z[1] != null || z[2] != null || z[3] != null;
	}

	@Override
	public void wizz(Case[][] plateau) {
		return;

	}

	@Override
	public void pop(Case[][] plateau) {
		return;

	}

	@Override
	public void move(String dir, Case[][] plateau) {
		int next_x = x;
		int next_y = y;

		switch (dir) {
		case "F":
			switch (direction) {
			case 'U':
				next_y--;
				break;
			case 'D':
				next_y++;
				break;
			case 'L':
				next_x--;
				break;
			case 'R':
				next_x++;
				break;
			default:
				break;
			}
			break;
		case "B":
			switch (direction) {
			case 'U':
				next_y++;
				direction = 'D';
				break;
			case 'D':
				next_y--;
				direction = 'U';
				break;
			case 'L':
				next_x++;
				direction = 'R';
				break;
			case 'R':
				next_x--;
				direction = 'L';
				break;
			default:
				break;
			}
			break;
		case "R":
			switch (direction) {
			case 'U':
				next_x++;
				direction = 'R';
				break;
			case 'D':
				next_x--;
				direction = 'L';
				break;
			case 'L':
				next_y--;
				direction = 'U';
				break;
			case 'R':
				next_y++;
				direction = 'D';
				break;
			default:
				break;
			}
			break;
		case "L":
			switch (direction) {
			case 'U':
				next_x--;
				direction = 'L';
				break;
			case 'D':
				next_x++;
				direction = 'R';
				break;
			case 'L':
				next_y++;
				direction = 'D';
				break;
			case 'R':
				next_y--;
				direction = 'U';
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		Case c = getC(next_x, next_y, plateau);
		if (c != null && !(c.getE() instanceof Physic_Entity)) {
			last_x = x;
			last_y = y;
			x = next_x;
			y = next_y;
			m_model.checkPaint();
			m_model.checkBonus();
			m_model.checkItem();
			m_model.checkTP();
			plateau[last_x][last_y].setE(null);
			plateau[last_x][last_y].setRefresh(true);
			plateau[x][y].setE(this);
			plateau[x][y].setRefresh(true);
			//

		}

	}

	@Override
	public void turn(Case[][] plateau) {
		switch (direction) {
		case 'U':
			direction = 'R';
			break;
		case 'R':
			direction = 'D';
			break;
		case 'D':
			direction = 'L';
			break;
		case 'L':
			direction = 'U';
			break;
		default:
			System.out.println("Pb direction sbire");
			break;
		}

	}

	@Override
	public void jump(String dir, Case[][] plateau) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hit(Case[][] plateau) {
		switch (direction) {
		case 'U':
			this.hit((Physic_Entity) plateau[x][y - 1].getE());
			checkCase(plateau[x][y - 1], plateau);
			break;
		case 'D':
			this.hit((Physic_Entity) plateau[x][y + 1].getE());
			checkCase(plateau[x][y + 1], plateau);

			break;
		case 'L':
			this.hit((Physic_Entity) plateau[x - 1][y].getE());
			checkCase(plateau[x - 1][y], plateau);

			break;
		case 'R':
			this.hit((Physic_Entity) plateau[x + 1][y].getE());
			checkCase(plateau[x + 1][y], plateau);

			break;
		default:
			break;
		}

	}

	public void checkCase(Case c, Case[][] plateau) {
		Entity e = c.getE();
		if (e instanceof Obstacle) {
			Obstacle o = (Obstacle) e;
			if (!(o.life()))
				c.setE(null);
			c.setRefresh(true);
		} else if (e instanceof Joueur) {
			Joueur j = (Joueur) e;
			plateau[j.getPosInit_x()][j.getPosInit_y()].setE(j);
			plateau[j.getPosInit_x()][j.getPosInit_y()].setRefresh(true);
			c.setE(null);
		} else if (e instanceof Zbire) {
			Zbire z = (Zbire) e;
			if (!(z.life()))
				c.setE(null);
		}

	}

	@Override
	public void protect(Case[][] plateau) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pick(Case[][] plateau) {
		// TODO Auto-generated method stub

	}

	@Override
	public void jeter(Case[][] plateau) {
		// TODO Auto-generated method stub

	}

	@Override
	public void store(Case[][] plateau) {
		// TODO Auto-generated method stub

	}

	@Override
	public void get(Case[][] plateau) {
		// TODO Auto-generated method stub

	}

	@Override
	public void power(Case[][] plateau) {
		// TODO Auto-generated method stub

	}

	@Override
	public void kamikaze(Case[][] plateau) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEtatCourant(String target) {
		etatcourant = target;

	}

	public void setAutomate() {
		automaton = search("IA");
		etatcourant = automaton.entry;

	}
}
