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
		if (elapsed > 35L) {
			canMove = true;
			m_lastMove = now;
		}
	}

	public void step(char direction) {
		if (canMove) {
			canMove = false;
			if (direction == 'R') {
				if (x < 1280-(diameter)-(diameter/2)) {
					last_x = x;
					last_y = y;
					x += step;
				}
			} else if (direction == 'L') {
				if (x > 0+(diameter/2)) {
					last_x = x;
					last_y = y;
					x -= step;
				}
			} else if (direction == 'D') {
				if (y < 720-1-(diameter)-(diameter/2)) {
					last_x = x;
					last_y = y;
					y += step;
				}
			} else if (direction == 'U') {
				if (y > 0+(diameter/2)) {
					last_x = x;
					last_y = y;
					y -= step;
				}
			}
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
}
