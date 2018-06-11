package interpreter;

import java.util.Random;

import mvc.MesOptions;
import ricm3.parser.Ast.Expression;
import ricm3.parser.Ast.Terminal;

public class BinaryOp_I extends Expression_I {
	Terminal operator;
	Expression left_operand;
	Expression right_operand;
	
	public BinaryOp_I(Terminal operator, Expression left_operand, Expression right_operand) {
		this.operator = operator;
		this.left_operand = left_operand;
		this.right_operand = right_operand;
	}

	@Override
	public boolean eval() {
		Expression_I l_op = (Expression_I) left_operand.make();
		Expression_I r_op = (Expression_I) right_operand.make();
		String ope = (String) operator.make();
		
		if(ope == "&") {
			return l_op.eval() && r_op.eval();
		}
		else if (ope == "/") {
			return l_op.eval() || r_op.eval();
		}
		return false;
	}

	@Override
	public void exec() {
		Expression_I l_op = (Expression_I) left_operand.make();
		Expression_I r_op = (Expression_I) right_operand.make();
		String ope = (String) operator.make();
		
		if(ope == "&") {
			l_op.exec();
		}
		else if (ope == "/") {
			Random rand = new Random();
			int i = rand.nextInt(10);
			if(i <= 7)
				l_op.exec();
			else
				r_op.exec();
		}
	}
}
