package interpreter;

import ricm3.parser.Ast.Value;

public class Direction_I {
	Value value;
	
	public Direction_I(Value value) {
		this.value = value;
	}
	
	public String eval() {
		return (String) value.make();
	}
}
