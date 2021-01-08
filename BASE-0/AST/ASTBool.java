package AST;

import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.BooleanType;
import Types.IType;
import Values.BooleanValue;
import Values.IValue;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;

public class ASTBool implements ASTNode{

	private boolean value;

	public IValue eval(Environment<IValue>	e) throws WrongTypeException
	{ 
		return new BooleanValue(value);
	}

	public ASTBool(boolean value)
	{
		this.value = value;
	}

	public void compile(CodeBlock c, CompilerEnvironment env)	
	{	
		c.bool(value);
	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		return BooleanType.singleton;
	}
}
