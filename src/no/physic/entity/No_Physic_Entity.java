package no.physic.entity;

import mvc.Entity;

public abstract class No_Physic_Entity extends Entity {
		
	public No_Physic_Entity(int x,int y) {
		// false correspond à la colision (désactivé)
		super(x, y, false);
	}
}
