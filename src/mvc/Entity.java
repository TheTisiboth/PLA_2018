package mvc;

import java.awt.Graphics;

public abstract class Entity {
	public int x, y;
	public boolean colision;

	public Entity(int x, int y, boolean b) {
		this.x = x;
		this.y = y;
		colision = b;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public abstract void paint(Graphics g);
}
