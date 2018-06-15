package interpreter;

import ricm3.parser.Ast.Constant;

public class Key_I {
	Constant value;
	
	public Key_I(Constant value) {
		this.value = value;
	}
	
	public String eval() {
		return (String) value.make();
	}
	

}
