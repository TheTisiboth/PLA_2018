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

//import mvc.Model;

public class Joueur extends Physic_Entity {

	private int last_x;
	private int last_y;
	private Color couleur;
	private int speed;
	private int timeEffect;
	private int paintStock;
	private Zbire z[];
	private int m_w, m_h;
	private int m_idx;
	private float m_scale;
	private BufferedImage m_sprite;
	private BufferedImage[] m_sprites;
	private int m_personali;
	private int m_nrows, m_ncols;
	private boolean moveable;

	private int diameter;

	private long m_lastMove;
	private int step = 1;

	private int pos_init_x;
	private int pos_init_y;

	private int recharge = 10;
	private boolean reload; // Sert a recharger la peinture sur le tour d'après

	char direction;
	char last_direction;
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

	public Joueur(BufferedImage sprite, int rows, int columns, int personali, int x, int y, float scale, Color couleur) {

		super(x, y);
		m_sprite = sprite;
		m_ncols = columns;
		m_nrows = rows;
		last_x = x+10;
		last_y = y+10;
		diameter = 34;
		m_scale = scale;
		moveable = true;
		timeEffect = 0;
		speed = 1;
		m_personali = personali*48;
		this.couleur = couleur;
		splitSprite();
		paintStock = MesOptions.paintMax;
		z = new Zbire[4];

		pos_init_x = x;
		pos_init_y = y;
		direction = last_direction = 'D';
		
		m_idx = 45+m_personali;


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
		g.drawImage(img, x * MesOptions.taille_case, y * MesOptions.taille_case, w, h, null);
	}

	public void canMove(Case[][] c) {
		if (inMovement) {
			// On commence par charger la prochaine case
			int nextX, nextY;

			if (y < MesOptions.nbLigne - 1 && direction == 'D') {
				nextX = x;
				nextY = y + 1;
			} else if (y > 0 && direction == 'U') {
				nextX = x;

				nextY = y - 1;
			} else if (x < MesOptions.nbCol - 1 && direction == 'R') {
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

	public void appliquerBonus(Bonus b, Joueur adverse) {
		if (b instanceof Speed) {
			speed = 2;
			timeEffect = 10;
		} else if (b instanceof Freeze) {
			if (adverse != null) {
				adverse.speed = 0;
				adverse.timeEffect = 20;
			}
		}
	}

	// Un cas pour recharger au prochain tour, l'autre pour recharger la peinture
	public void recharger(boolean reload) {
		// la peinture sera rechargée au prochain tour
		if (reload) {
			this.reload = true;
		} // on recharge la peinture
		else if (this.reload) {
			paintStock += recharge;
			if (paintStock > MesOptions.paintMax) {
				paintStock -= MesOptions.paintMax - paintStock;
			}
			this.reload = false;
		}
	}

	public void appliquerItem(int joueur) {

		Random rand = new Random();
		int i = rand.nextInt(100);
		Zbire zbire = null;

		if (i >= 0 && i < 25) {
			if (z[0] == null) {
				System.out.println("zbire : "+ 1);
				zbire = new Zbire(m_sprite,12,24,-1, -1, this.couleur, 10, 0,m_scale,joueur);
				z[0] = zbire;
			}

		} else if (i >= 25 && i < 50) {
			if (z[1] == null) {
				System.out.println("zbire : "+ 2);
				zbire = new Zbire(m_sprite,12,24,-1, -1, this.couleur, 10, 1,m_scale,joueur);
				z[1] = zbire;
			}

		} else if (i >= 50 && i < 75) {
			if (z[2] == null) {
				System.out.println("zbire : "+ 3);
				zbire =new Zbire(m_sprite,12,24,-1, -1, this.couleur, 10, 2,m_scale,joueur);
				z[2] = zbire;
			}

		} else {
			if (z[3] == null) {
				System.out.println("zbire : "+ 4);
				zbire = new Zbire(m_sprite,12,24,-1, -1, this.couleur, 10, 3,m_scale,joueur);
				z[3] = zbire;
			}

		}

	}

	public void step(long now) {
		for(int i = 0;i<4;i++){
			if(z[i]!=null)
				z[i].step(now);
		}
		long elapsed = now - m_lastMove;
		last_x = x;
		last_y = y;

		// On change la durée avant la prochaine action selon le bonus
		long time = 150L;
		// Cas 1 : Freeze
		if (elapsed > time && speed < 1 && timeEffect > 0) {
			timeEffect--;
			m_lastMove = now;
			elapsed = now - m_lastMove;

		} // cas 2 : Speed
		else if (speed > 1 && elapsed > time / speed && timeEffect > 0) {
			time /= speed;
			//System.out.println("activation du speed dans step");
		}

		if (elapsed > time) {

			if (inMovement && moveable) {
				
				if (direction == 'R' && x < MesOptions.nbCol - 1) {
					x += step;
				} else if (direction == 'L' && x > 0) {
					x -= step;
				} else if (direction == 'D' && y < MesOptions.nbLigne - 1) {
					y += step;
				} else if (direction == 'U' && y > 0) {
					y -= step;
				}
			}
			
			switch (direction) {
			case 'R':
				m_idx = (m_idx == 1+m_personali) ? 4+m_personali : 1+m_personali;
				break;
			case 'L':
				m_idx = (m_idx == 25+m_personali) ? 28+m_personali : 25+m_personali;
				break;
			case 'D':
				m_idx = (m_idx == 42+m_personali) ? 44+m_personali : 42+m_personali;
				break;
			case 'U':
				m_idx = (m_idx == 12+m_personali) ? 13+m_personali : 12+m_personali;
				break;
			}
			

			m_lastMove = now;
			if (timeEffect > 0) {
				timeEffect--;
			}
		}
	}

	public void hit(Physic_Entity e) {
		if (e instanceof Obstacle) {
			Obstacle o = (Obstacle) e;
			o.reduce_life();
		} else if (e instanceof Joueur) {
			Joueur j = (Joueur) e;
			j.x = j.getPosInit_x();
			j.y = j.getPosInit_y();
		} else {
			Zbire z = (Zbire) e;
			z.reduce_nb_case();
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
	
	public Zbire[] getZbire(){
		return z;
	}
	
	public void resetZbire(int n){
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
}
