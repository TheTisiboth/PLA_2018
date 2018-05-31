package proto;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.org.apache.regexp.internal.recompile;

public class Circle {
	private int x;
	private int last_x;
	private int last_y;
	private int y;
	private int diameter;
	private long m_lastMove;
	private int step = 40;
	private boolean canMove = false;
	char direction;
	boolean inMovement;

	Circle() {
		x = 42;
		y = 42;
		last_x = x;
		last_y = y;
		diameter = 36;

	}

	public void paint(Graphics g) {
		g.setColor(Color.ORANGE);
		if (y != last_y || x != last_x) {
			g.fillRect(last_x, last_y, diameter + 1, diameter + 1);
		}
		g.setColor(Color.BLUE);
		g.fillOval(x, y, diameter, diameter);
		g.setColor(Color.RED);
		g.fillOval(100, 100, diameter, diameter);

	}

	public void step(long now) {

		long elapsed = now - m_lastMove;
		if (inMovement && elapsed > 100L) {
			last_x = x;
			last_y = y;
			if (direction == 'R') {
				x += step;
			} else if (direction == 'L') {
				x -= step;
			} else if (direction == 'D') {
				y += step;
			} else if (direction == 'U') {
				y -= step;
			}
			m_lastMove = now;
		}
	}

	public void setDirection(char direction) {
		this.direction = direction;
		setMovement(true);
	}
	
	public void setMovement(boolean b) {
		inMovement = b;
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
}
