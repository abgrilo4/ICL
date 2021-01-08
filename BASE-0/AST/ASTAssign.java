package AST;

import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.IType;
import Types.RefType;
import Values.IValue;
import Values.RefValue;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;

public class ASTAssign implements ASTNode{

	ASTNode lhs, rhs;
	private IType type;
	private static final String ERROR_EVAL = "Illegal arguments to - operator";
	private static final String ERROR_TYPECHECK = "Wrong types in -";

	public IValue eval(Environment<IValue>	e) throws WrongTypeException
	{ 
		IValue v1 = lhs.eval(e);
		IValue v2 = rhs.eval(e);

		if(v1 instanceof RefValue) {
			((RefValue)v1).setRef(v2);
			return((RefValue)v1).getRef();
		}
		throw new WrongTypeException(ERROR_EVAL);
	}

	public ASTAssign(ASTNode l, ASTNode r)
	{
		lhs = l; rhs = r;
	}

	public void compile(CodeBlock c, CompilerEnvironment env)	
	{	
		lhs.compile(c,	env);	
		rhs.compile(c,	env);	
		c.emit("iassign");
	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		IType l = lhs.typechecker(environment);
		IType r = rhs.typechecker(environment);

		if(l instanceof RefType)
			if(r.equals(((RefType) l).getType()))
			{
				type = l;
				return type;
			}

		throw new WrongTypeException(ERROR_TYPECHECK);
	}


}
