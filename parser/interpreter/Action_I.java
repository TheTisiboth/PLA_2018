package interpreter;

import physic.entity.Physic_Entity;

public class Action_I {
	Expression_I exp;
	
	public Action_I(Expression_I expression) {
		exp = expression;
	}
	
	public void exec(Physic_Entity j) {
		exp.exec(j);
	}

}
