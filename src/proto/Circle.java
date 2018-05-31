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
	private int step = 8;

	Circle() {
		x = 30;
		y = 30;
		last_x = 30;
		last_y = 30;
		diameter = 50;

	}

	public void paint(Graphics g) {
		g.setColor(Color.ORANGE);
		if (y != last_y || x != last_x) {
			g.fillRect(last_x, last_y, diameter+1, diameter+1);
		}
		g.setColor(Color.BLUE);
		g.fillOval(x, y, diameter, diameter);
		g.setColor(Color.RED);
		g.fillOval(100, 100, diameter, diameter);

	}

	public void step(long now) {

		// long elapsed = now - m_lastMove;
		// if (elapsed > 50L) {
		// x += 1;
		// m_lastMove = now;
		// }
	}

	public void step(char direction) {
		if (direction == 'R') {
			last_x = x;
			last_y = y;
			x += 3 * step;
		} else if (direction == 'L') {
			last_x = x;
			last_y = y;
			x -= 3 * step;
		} else if (direction == 'D') {
			last_x = x;
			last_y = y;
			y += 3 * step;
		} else if (direction == 'U') {
			last_x = x;
			last_y = y;
			y -= 3 * step;
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
