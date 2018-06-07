package fenetre;

import java.awt.Color;
import java.awt.Graphics;

public class Circle {
	private int x;
	private int y;
	private int diameter;
	private int step = 8;

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
//		long elapsed = now - m_lastMove;
//		if (elapsed > 50L) {
//			x += 1;
//			m_lastMove = now;
//		}
	}
	
	public void step(char direction) {
		if (direction == 'R') {
			  x += 3*step;
		  }
		  else if(direction == 'L') {
			  x -= 3*step;
		  }
		  else if(direction == 'D') {
			  y += 3*step;
		  }
		  else if(direction == 'U') {
			  y -= 3*step;
		  }
	}
}
