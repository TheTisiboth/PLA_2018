package interpreter;

import mvc.Case;
import physic.entity.Physic_Entity;

public class UnaryOp_I extends Expression_I {
	String operator;
	Expression_I operand;
	
	public UnaryOp_I(String operator, Expression_I operand) {
		this.operand = operand;
		this.operator = operator;
	}
	
	public boolean eval(Physic_Entity j, Case[][] plateau) {
		if(operator.equals("!")) {
			return !(operand.eval(j, plateau));
		}
		return false;
	}

	@Override
	public void exec(Physic_Entity j, Case[][] plateau) {
		operand.exec(j, plateau);
	}
}
