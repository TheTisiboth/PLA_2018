package proto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Personnage extends Entity {

	private int last_x;
	private int last_y;
	int m_w, m_h;
	int m_idx;
	float m_scale;
	private boolean moveable;
	BufferedImage m_sprite;
	private int diameter;
	BufferedImage[] m_sprites;
	private long m_lastMove;
	private int step = 1;
	Model m_model;
	int m_nrows, m_ncols;
	private Color couleur;
	
	char direction;
	boolean inMovement;

	Personnage(Model model, BufferedImage sprite, int rows, int columns, int x, int y, float scale, Color couleur) {
		m_model = model;
		m_sprite = sprite;
		m_ncols = columns;
		m_nrows = rows;
		super.x = x;
		super.y = y;
		last_x = x;
		last_y = y;
		diameter = 34;
		m_scale = scale;
		moveable = true;
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
		g.setColor(this.couleur);
		if (y != last_y || x != last_x) {
			g.fillRect(last_x * taille_case + 3, last_y * taille_case + 3, diameter, diameter);
		}
		Image img = m_sprites[m_idx];
		int w = (int) (m_scale * m_w);
		int h = (int) (m_scale * m_h);
		g.drawImage(img, super.x*taille_case, super.y*taille_case, w, h, null);

	}

	public void canMove(Case[][] c) {
		if (inMovement) {
			if (y < 17 && c[x][y + 1].isOccuped() && direction == 'D') {
				moveable = false;
			} else if (y > 0 && c[x][y - 1].isOccuped() && direction == 'U') {
				moveable = false;
			} else if (x < 30 && c[x + 1][y].isOccuped() && direction == 'R') {
				moveable = false;
			} else if (x > 0 && c[x - 1][y].isOccuped() && direction == 'L') {
				moveable = false;
			} else {
				moveable = true;
			}
		}

	}

	public void step(long now) {
		long elapsed = now - m_lastMove;
		if (inMovement && elapsed > 100L && moveable) {
			last_x = x;
			last_y = y;
			if (direction == 'R' && x < 31) {
				x += step;
				m_idx = 19;
			} else if (direction == 'L' && x > 0) {
				x -= step;
				m_idx = 7;
			} else if (direction == 'D' && y < 17) {
				y += step;
				m_idx = 2;
			} else if (direction == 'U' && y > 0) {
				y -= step;
				m_idx = 13;
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
}
