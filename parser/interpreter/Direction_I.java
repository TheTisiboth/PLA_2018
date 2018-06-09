package interpreter;

import ricm3.parser.Ast.Expression;

public class Direction_I {
	Expression value;
	
	public Direction_I(Expression value) {
		this.value = value;
	}
	
	public String eval() {
		return (String) value.make();
	}
}
