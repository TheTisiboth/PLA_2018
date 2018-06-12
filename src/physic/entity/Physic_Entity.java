package physic.entity;

import mvc.Entity;

public abstract class Physic_Entity extends Entity {

	public Physic_Entity(int x, int y) {
		// collision activated : true
		super(x, y, true);
	}
	
	// Methods Condition
	public abstract boolean gotPower();
	public abstract boolean key(String cle);
	public abstract boolean myDir(String dir);
	public abstract boolean cell(String dir, String entity);
	public abstract boolean closest(String dir, String entity);
	public abstract boolean gotStuff();
	
	// Methods Action
	public abstract void wizz();
	public abstract void pop();
	public abstract void move(String dir);
	public abstract void turn();
	public abstract void jump();
	public abstract void hit();
	public abstract void protect();
	public abstract void pick();
	public abstract void jeter();
	public abstract void store();
	public abstract void get();
	public abstract void power();
	public abstract void kamikaze();
}
