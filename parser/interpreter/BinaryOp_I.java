package interpreter;

import java.util.Random;

import physic.entity.Physic_Entity;

public class BinaryOp_I extends Expression_I {
	String operator;
	Expression_I left_operand;
	Expression_I right_operand;
	
	public BinaryOp_I(String operator, Expression_I left_operand, Expression_I right_operand) {
		this.operator = operator;
		this.left_operand = left_operand;
		this.right_operand = right_operand;
	}

	@Override
	public boolean eval(Physic_Entity j) {
		
		if(operator.equals("&")) {
			return left_operand.eval(j) && right_operand.eval(j);
		}
		else if (operator.equals("/")) {
			return left_operand.eval(j) || right_operand.eval(j);
		}
		return false;
	}

	@Override
	public void exec(Physic_Entity j) {
		if(operator.equals("&")) {
			left_operand.exec(j);
		}
		else if (operator.equals("/")) {
			Random rand = new Random();
			int i = rand.nextInt(10);
			if(i <= 7)
				left_operand.exec(j);
			else
				right_operand.exec(j);
		}
	}
}
