package AST;

import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.IType;
import Values.IValue;
import Values.IntegerValue;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;

public class ASTMod implements ASTNode{

	ASTNode lhs, rhs;
	private static final String ERROR_EVAL = "Illegal arguments to - operator";

	public IValue eval(Environment<IValue>	e) throws WrongTypeException
	{ 
		IValue v1 = lhs.eval(e);
		IValue v2 = rhs.eval(e);

		if(v1 instanceof IntegerValue && v2 instanceof IntegerValue) {
			return new IntegerValue(((IntegerValue) v1).getIntegerValue() % ((IntegerValue) v2).getIntegerValue());
		}
		throw new WrongTypeException(ERROR_EVAL);
	}

	public ASTMod(ASTNode l, ASTNode r)
	{
		lhs = l; rhs = r;
	}

	public void compile(CodeBlock c, CompilerEnvironment env)	
	{	
		lhs.compile(c,	env);	
		rhs.compile(c,	env);	
		c.emit("imod");
	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		// TODO Auto-generated method stub
		return null;
	}
}
