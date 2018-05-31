package proto;

import java.awt.Color;
import java.awt.Graphics;

public class Circle {
	private int x;
	private int y;
	private int diameter;
	private long m_lastMove;

	Circle() {
		x = 30;
		y = 30;
		diameter = 50;
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(x, y, diameter / 2, diameter / 2);
		g.setColor(Color.RED);
		g.fillOval(100, 100, diameter / 2, diameter / 2);

	}

	public void step(long now) {
		long elapsed = now - m_lastMove;
		if (elapsed > 50L) {
			x += 1;
			m_lastMove = now;
		}

	}
}
