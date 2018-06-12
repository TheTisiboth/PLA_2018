package physic.entity;

import mvc.Case;
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
	public abstract boolean cell(String dir, String entity, Case[][] plateau);
	public abstract boolean closest(String dir, String entity, Case[][] plateau);
	public abstract boolean gotStuff();
	
	// Methods Action
	public abstract void wizz(Case[][] plateau);
	public abstract void pop(Case[][] plateau);
	public abstract void move(String dir, Case[][] plateau);
	public abstract void turn(Case[][] plateau);
	public abstract void jump(Case[][] plateau);
	public abstract void hit(Case[][] plateau);
	public abstract void protect(Case[][] plateau);
	public abstract void pick(Case[][] plateau);
	public abstract void jeter(Case[][] plateau);
	public abstract void store(Case[][] plateau);
	public abstract void get(Case[][] plateau);
	public abstract void power(Case[][] plateau);
	public abstract void kamikaze(Case[][] plateau);

	public abstract void setEtatCourant(String target);
}
