package physic.entity;

import java.awt.Color;

import java.awt.Graphics;

import mvc.Case;
import mvc.Options;
import no.physic.entity.Bonus;

public class Joueur extends Physic_Entity {

	private int last_x;
	private int last_y;
	private Color couleur;
	private int speed;
	private int paint_stock;
	private Zbire z[];
	
	private boolean moveable;

	private int diameter;

	private long m_lastMove;
	private int step = 1;

	char direction;
	boolean inMovement;

	public Joueur(int x, int y, Color couleur) {
		super(x, y);
		last_x = x;
		last_y = y;
		diameter = 34;
		this.couleur = couleur;
		moveable = true;

	}

	public void paint(Graphics g) {
//		g.setColor(couleur);
//		if (y != last_y || x != last_x) {
//			g.fillRect(last_x * Options.taille_case + 2, last_y * Options.taille_case + 2, diameter, diameter);
//		}
		g.setColor(couleur.darker());
		g.fillOval(x * Options.taille_case + 2, y * Options.taille_case + 2, diameter, diameter);

	}

	public void canMove(Case[][] c) {
		if (inMovement) {
			//On commence par charger la prochaine case
			int nextX, nextY;
			if (y < Options.nbLigne-1 && direction == 'D') {
				nextX = x;
				nextY = y+1;
			} else if (y > 0 && direction == 'U') {
				nextX = x;
				nextY = y-1;
			} else if (x < Options.nbCol-1 && direction == 'R') {
				nextX = x+1;
				nextY = y;
			} else if (x > 0 && direction == 'L') {
				nextX = x-1;
				nextY = y;
			}
			else {
				//Le joueur veut sortir du plateau
				return;
			}
			
			//On regarde si la case est occupée
			if (c[nextX][nextY].isOccuped()) {
				//Si occupée, on regarde si ce n'est pas un bonus dessus
				if(c[nextX][nextY].getE() instanceof no.physic.entity.Bonus) {
					
					moveable = true;
				}
				//sinon on ne peut pas y aller
				else {
					moveable = false;
				}
			}else {
				//si la case n'est pas occupée, on peut y aller
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
			} else if (direction == 'L' && x > 0) {
				x -= step;
			} else if (direction == 'D' && y < 17) {
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

	

	

}
