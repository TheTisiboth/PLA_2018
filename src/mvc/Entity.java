package mvc;

import java.awt.Graphics;

public abstract class Entity {
	public int x;
	public int y;
	public boolean colision;
	
	public Entity(int x, int y,boolean b ) {
		this.x =x;
		this.y=y;
		colision = b;
	}

	public abstract void paint(Graphics g);
}
