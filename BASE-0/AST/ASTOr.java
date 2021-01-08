package AST;

import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.BooleanType;
import Types.IType;
import Values.BooleanValue;
import Values.IValue;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;

public class ASTOr implements ASTNode{

	ASTNode lhs, rhs;
	private IType type;
	private static final String ERROR_EVAL = "Illegal arguments to - operator";
	private static final String ERROR_TYPECHECK = "Wrong types in -";

	public IValue eval(Environment<IValue>	e) throws WrongTypeException
	{ 
		IValue v1 = lhs.eval(e);
		IValue v2 = rhs.eval(e);

		if(v1 instanceof BooleanType) {
			if(v2 instanceof BooleanType)
			return new BooleanValue(((BooleanValue)v1).getBooleanValue()||((BooleanValue)v2).getBooleanValue());	;
		}
		throw new WrongTypeException(ERROR_EVAL);
	}

	public ASTOr(ASTNode l, ASTNode r)
	{
		lhs = l; rhs = r;
	}

	public void compile(CodeBlock c, CompilerEnvironment env)	
	{	
		lhs.compile(c,	env);	
		rhs.compile(c,	env);	
		c.emit("ior");
	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		IType l = lhs.typechecker(environment);
		IType r = rhs.typechecker(environment);

		if(l.equals(BooleanType.singleton) && r.equals(BooleanType.singleton))
			{
				type = l;
				return type;
			}

		throw new WrongTypeException(ERROR_TYPECHECK);
	}


}
