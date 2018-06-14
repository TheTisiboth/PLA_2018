package physic.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import mvc.Case;
import mvc.MesOptions;
import no.physic.entity.Bonus;
import no.physic.entity.Freeze;
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

	char direction, last_direction; // direction et derniere direction du joueur
	private int timeEffectFreeze; // durée du freeze du joueur

	public Joueur(BufferedImage sprite, int rows, int columns, int personnalisation, int x, int y, float scale,
			Color couleur, BufferedImage zbires) {

		super(x, y);
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
			paintStock += recharge;
			if (paintStock > MesOptions.paintMax) {
				paintStock = MesOptions.paintMax;
			}
			this.reload = false;
		}
	}

	// apply item zbire
	public void appliquerItem(int joueur) {

		Random rand = new Random();
		int i = rand.nextInt(100);
		Zbire zbire = null;

		if (i >= 0 && i < 25) {
			if (z[0] == null) {
				zbire = new Zbire(-1, -1, this.couleur, 10, 0, 0.50F, joueur, m_zbires);
				z[0] = zbire;
			}

		} else if (i >= 25 && i < 50) {
			if (z[1] == null) {
				zbire = new Zbire(-1, -1, this.couleur, 10, 1, 0.50F, joueur, m_zbires);
				z[1] = zbire;
			}

		} else if (i >= 50 && i < 75) {
			if (z[2] == null) {
				zbire = new Zbire(-1, -1, this.couleur, 10, 2, 0.50F, joueur, m_zbires);
				z[2] = zbire;
			}

		} else {
			if (z[3] == null) {
				zbire = new Zbire(-1, -1, this.couleur, 10, 3, 0.50F, joueur, m_zbires);
				z[3] = zbire;
			}
		}
	}

	public void step(long now) {
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

	public Object getColor() {

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
}
