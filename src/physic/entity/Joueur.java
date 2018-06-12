package physic.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import interpreter.Automaton_I;
import interpreter.Behaviour_I;
import mvc.Case;
import mvc.MesOptions;
import no.physic.entity.Bonus;
import no.physic.entity.Freeze;
import no.physic.entity.Speed;

public class Joueur extends Physic_Entity {

	private int last_x, last_y;
	private int m_w, m_h, m_idx;
	private int speed, timeEffect, paintStock;
	private int m_personnalisation, m_nrows, m_ncols, diameter;
	private int step = 1;
	private int pos_init_x, pos_init_y;
	private int recharge = 10;

	private Color couleur;
	private Zbire z[];

	private BufferedImage m_sprite;
	private BufferedImage[] m_sprites;

	private boolean moveable, reload;
	boolean inMovement;

	private float m_scale;
	private long m_lastMove;

	char direction, last_direction;
	private int timeEffectFreeze;

	public Joueur(BufferedImage sprite, int rows, int columns, int personnalisation, int x, int y, float scale,
			Color couleur) {

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
		m_personnalisation = personnalisation * 48;
		this.couleur = couleur;
		splitSprite();
		paintStock = MesOptions.paintMax;
		z = new Zbire[4];

		pos_init_x = x;
		pos_init_y = y;
		direction = last_direction = 'D';

		m_idx = 45 + m_personnalisation;

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
			if (c[nextX][nextY].isOccuped()) {
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
	public void appliquerItem(int joueur, LinkedList<String> listAut) {

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
				zbire = new Zbire(-1, -1, this.couleur, 10, 0, 0.50F, joueur, aut, aut.entry);
				z[0] = zbire;
			}

		} else if (i >= 25 && i < 50) {
			if (z[1] == null) {
				// System.out.println("zbire : " + 2);
				nom = listAut.get(1);
				aut = search(nom);
				zbire = new Zbire(-1, -1, this.couleur, 10, 1, 0.50F, joueur, aut, aut.entry);
				z[1] = zbire;
			}

		} else if (i >= 50 && i < 75) {
			if (z[2] == null) {
				// System.out.println("zbire : " + 3);
				nom = listAut.get(2);
				aut = search(nom);
				zbire = new Zbire(-1, -1, this.couleur, 10, 2, 0.50F, joueur, aut, aut.entry);
				z[2] = zbire;
			}

		} else {
			if (z[3] == null) {
				// System.out.println("zbire : " + 4);
				nom = listAut.get(3);
				aut = search(nom);
				zbire = new Zbire(-1, -1, this.couleur, 10, 3, 0.50F, joueur, aut, aut.entry);
				z[3] = zbire;
			}
		}
	}
	
	Automaton_I search(String nom) {
		ListIterator<Automaton_I> Iter = MesOptions.automates.listIterator();
		Automaton_I aut;
		
		while(Iter.hasNext()) {
			aut = Iter.next();
			if(aut.name.equals(nom)) {
				return aut;
			}
		}
		return null;
	}

	public void step(long now) {
		// for (int i = 0; i < 4; i++) {
		// if (z[i] != null) {
		// z[i].step(now);
		// if (!z[i].life()) {
		// System.out.println("le sbire doit disparaitre");
		// z[i] = null;
		//
		// }
		// }
		//
		// }
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
	public boolean gotPower() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean key(String cle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean myDir(String dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cell(String dir, String entity, Case[][] plateau) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closest(String dir, String entity, Case[][] plateau) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gotStuff() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void wizz(Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pop(Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(String dir, Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turn(Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jump(Case[][] plateau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hit(Case[][] plateau) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
}
