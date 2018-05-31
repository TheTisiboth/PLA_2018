package proto;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.org.apache.regexp.internal.recompile;
import com.sun.xml.internal.ws.api.Cancelable;

import javafx.collections.ListChangeListener.Change;
import sun.security.krb5.internal.ccache.CCacheOutputStream;

public class Circle extends Entity {

	private int last_x;
	private int last_y;
	private Color couleur;
	private boolean moveable;

	private int diameter;

	private long m_lastMove;
	private int step = 1;

	char direction;
	boolean inMovement;

	Circle(int x, int y, Color couleur) {
		super.x = x;
		super.y = y;
		last_x = x;
		last_y = y;
		diameter = 34;
		this.couleur = couleur;
		moveable = true;

	}

	public void paint(Graphics g) {
		g.setColor(Color.ORANGE);
		if (y != last_y || x != last_x) {
			g.fillRect(last_x * taille_case + 2, last_y * taille_case + 2, diameter, diameter);
		}
		g.setColor(couleur);
		g.fillOval(x * taille_case + 2, y * taille_case + 2, diameter, diameter);
		// g.setColor(Color.RED);
		// g.fillOval(100, 100, diameter, diameter);

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
}
