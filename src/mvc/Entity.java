package mvc;

public abstract class Entity {
	public int x;
	public int y;
	public boolean colision;
	
	public Entity(int x, int y,boolean b ) {
		this.x =x;
		this.y=y;
		colision = b;
	}
}
