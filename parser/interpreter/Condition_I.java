package interpreter;

import physic.entity.Physic_Entity;

public class Condition_I {
	Expression_I exp;
	
	public Condition_I(Expression_I expression) {
		exp = expression;
	}
	
	public boolean eval(Physic_Entity j) {
		return exp.eval(j);
	}

}
