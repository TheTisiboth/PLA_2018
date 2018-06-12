package interpreter;

import physic.entity.Physic_Entity;

public class UnaryOp_I extends Expression_I {
	String operator;
	Expression_I operand;
	
	public UnaryOp_I(String operator, Expression_I operand) {
		this.operand = operand;
		this.operator = operator;
	}
	
	public boolean eval(Physic_Entity j) {
		if(operator.equals("!")) {
			return !(operand.eval(j));
		}
		return false;
	}

	@Override
	public void exec(Physic_Entity j) {
		operand.exec(j);
	}
}
