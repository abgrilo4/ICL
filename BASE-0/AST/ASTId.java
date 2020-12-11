package AST;

import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.IType;
import Values.IValue;
import Compiler.CompilerEnvironment;
import Compiler.Coordinates;
import Compiler.CodeBlock;

public class ASTId implements ASTNode{

	private String id;
	private IType type;
	
	public IValue eval(Environment<IValue> e) 
	{
		return e.find(id);
	}
	
	public ASTId(String id)
	{
		this.id = id;
	}
	
	public void compile(CodeBlock c, CompilerEnvironment env)	
	{	
		Coordinates coord = env.find(id, 0);
		c.compileId(coord);
	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		type = environment.find(id);
		return type;
	}
	
	

}
