package interpreter;

import mvc.Case;
import physic.entity.Physic_Entity;

public class Condition_I {
	Expression_I exp;
	
	public Condition_I(Expression_I expression) {
		exp = expression;
	}
	
	public boolean eval(Physic_Entity j, Case[][] plateau) {
		return exp.eval(j, plateau);
	}

}
