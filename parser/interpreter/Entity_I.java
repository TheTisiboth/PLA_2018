package interpreter;

import ricm3.parser.Ast.Value;

public class Entity_I {
	Value value;
	
	public Entity_I(Value value) {
		this.value = value;
	}
	
	public String eval() {
		return (String) value.make();
	}

}
