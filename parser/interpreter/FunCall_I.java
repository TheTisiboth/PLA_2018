package interpreter;
import java.util.List;

import interpreter.Expression_I;
import ricm3.parser.Ast.Parameter;
import ricm3.parser.Ast.Terminal;

public class FunCall_I extends Expression_I {
	Terminal name;
	List<Parameter> parameters;
	
	
	public FunCall_I(Terminal name, List<Parameter> parameters) {
		this.name = name;
		this.parameters = parameters;
	}
	
	@Override
	public boolean eval() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void exec() {
		// TODO Auto-generated method stub
		
	}

}
