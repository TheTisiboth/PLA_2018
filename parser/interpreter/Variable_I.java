package interpreter;

import ricm3.parser.Ast.Terminal;

public class Variable_I {
	Terminal name;
	
	public Variable_I(Terminal name) {
		this.name = name;
	}
	
	public String eval() {
		return (String) name.make();
	}
}
