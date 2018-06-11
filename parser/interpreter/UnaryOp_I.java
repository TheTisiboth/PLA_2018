package interpreter;

import ricm3.parser.Ast.Expression;
import ricm3.parser.Ast.Terminal;

public class UnaryOp_I extends Expression_I {
	Terminal operator;
	Expression operand;
	
	public UnaryOp_I(Terminal operator, Expression operand) {
		this.operand = operand;
		this.operator = operator;
	}
	
	public boolean eval() {
		Expression_I op = (Expression_I) operand.make();
		String ope = (String) operator.make();
		
		if(ope == "!") {
			return !(op.eval());
		}
		return false;
	}
}
