package interpreter;

import physic.entity.Physic_Entity;

public abstract class Expression_I {
	
	public abstract boolean eval(Physic_Entity j);
	public abstract void exec(Physic_Entity j);
}
