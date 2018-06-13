package physic.entity;

import mvc.Entity;

public abstract class Physic_Entity extends Entity {

	public Physic_Entity(int x, int y) {
		// collision activated : true
		super(x, y, true);
	}

}
