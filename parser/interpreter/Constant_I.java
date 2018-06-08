package interpreter;

import ricm3.parser.Ast.Terminal;

public class Constant_I {
	Terminal value;
	
	public Constant_I(Terminal value){
		this.value = value;
	}
	
	public String eval() {
		return (String)value.make();
	}
}
