package AST;

import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.IType;
import Values.IValue;
import Values.IntegerValue;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;

public class ASTUMinus implements ASTNode{

	ASTNode lhs;
	private static final String ERROR_EVAL = "Illegal arguments to - operator";

	public IValue eval(Environment<IValue>	e) throws WrongTypeException
	{ 
		IValue v1 = lhs.eval(e);

		if(v1 instanceof IntegerValue) 
			return new IntegerValue((((IntegerValue)v1).getIntegerValue() - 1));
		else
			throw new WrongTypeException(ERROR_EVAL);
	}

	public ASTUMinus(ASTNode l)
	{
		lhs = l; 
	}

	public void compile(CodeBlock c, CompilerEnvironment env)	
	{	
		lhs.compile(c,	env);	
		c.emit("ineg");
	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		IType l = lhs.typechecker(environment);

		return l;
	}
}
