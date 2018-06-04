package physic.entity;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.security.cert.PKIXRevocationChecker.Option;

import mvc.Case;
import mvc.Options;

//import mvc.Model;


public class Joueur extends Physic_Entity {

	private int last_x;
	private int last_y;
	private Color couleur;
	private int speed;
	private int timeEffect;
	private int paint_stock;
	private Zbire z[];
	int m_w, m_h;
	int m_idx;
	float m_scale;
	BufferedImage m_sprite;
	BufferedImage[] m_sprites;
	int m_nrows, m_ncols;
	private boolean moveable;

	private int diameter;

	private long m_lastMove;
	private int step = 1;

	char direction;
	boolean inMovement;

	// public Joueur(int x, int y, Color couleur) {
	// super(x, y);
	// last_x = x;
	// last_y = y;
	// diameter = 34;
	// this.couleur = couleur;
	// moveable = true;
	//
	// }

	public Joueur(BufferedImage sprite, int rows, int columns, int x, int y, float scale, Color couleur) {
		super(x, y);
		m_sprite = sprite;
		m_ncols = columns;
		m_nrows = rows;
		last_x = x;
		last_y = y;
		diameter = 34;
		m_scale = scale;
		moveable = true;
		timeEffect = 0;
		speed = 1;
		this.couleur = couleur;
		splitSprite();
	}

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
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, x * Options.taille_case, y * Options.taille_case, w, h, null);
	}

	public void canMove(Case[][] c) {
		if (inMovement) {
			// On commence par charger la prochaine case
			int nextX, nextY;
			if (y < Options.nbLigne - 1 && direction == 'D') {
				nextX = x;
				nextY = y + 1;
			} else if (y > 0 && direction == 'U') {
				nextX = x;
				nextY = y - 1;
			} else if (x < Options.nbCol - 1 && direction == 'R') {
				nextX = x + 1;
				nextY = y;
			} else if (x > 0 && direction == 'L') {
				nextX = x - 1;
				nextY = y;
			} else {
				// Le joueur veut sortir du plateau
				return;
			}

			// On regarde si la case est occupée
			if (c[nextX][nextY].isOccuped()) {
				// Si occupée, on regarde si ce n'est pas un bonus dessus
				if (!c[nextX][nextY].getE().colision) {
					moveable = true;
				}
				// sinon on ne peut pas y aller
				else {
					moveable = false;
				}
			} else {
				// si la case n'est pas occupée, on peut y aller
				moveable = true;
			}
		}

	}

	public void appliquerBonus(Case[][] c, Joueur adverse) {
		if (c[x][y].getE() instanceof no.physic.entity.Speed) {
			speed = 2;
			timeEffect = 40;
		} else if (c[x][y].getE() instanceof no.physic.entity.Freeze) {
			if (adverse != null) {
				adverse.speed = 0;
				adverse.timeEffect = 20;
			}
		}
	}

	

	public void step(long now) {
		long elapsed = now - m_lastMove;
		// On change la durée avant la prochaine action selon le bonus
		long time = 150L;
		// Cas 1 : Freeze
		if (elapsed > time && speed < 1 && timeEffect > 0) {
			timeEffect--;
			m_lastMove = now;
			elapsed = now - m_lastMove;
			System.out.println("activation du freeze dans step");
		} // cas 2 : Speed
		else if (speed > 1 && elapsed > time / speed && timeEffect > 0) {
			time /= speed;
			timeEffect--;
			System.out.println("activation du speed dans step");
		}

		if (inMovement && elapsed > time && moveable) {
			last_x = x;
			last_y = y;
			if (direction == 'R' && x < Options.nbCol - 1) {
				x += step;
			} else if (direction == 'L' && x > 0) {
				x -= step;
			} else if (direction == 'D' && y < Options.nbLigne - 1) {
				y += step;
			} else if (direction == 'U' && y > 0) {
				y -= step;
			}

				m_lastMove = now;


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

	public int getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
		this.direction = direction;
		inMovement = true;
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
}
