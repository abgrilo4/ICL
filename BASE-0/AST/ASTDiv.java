package AST;

import Environment.Environment;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;
import Exceptions.WrongTypeException;
import Types.IType;
import Types.IntegerType;
import Values.*;

public class ASTDiv implements ASTNode{
	
	
	ASTNode lhs, rhs;
	private IType type;
	private static final String ERROR_EVAL = "Illegal arguments to / operator";
	private static final String ERROR_TYPECHECK = "Wrong types in /";

	public IValue eval(Environment<IValue>	e) throws WrongTypeException
	{ 
		IValue v1 = lhs.eval(e);
		IValue v2 = rhs.eval(e);
		
		if(v1 instanceof IntegerValue) {
			if(v2 instanceof IntegerValue) {
				return new IntegerValue(((IntegerValue)v1).getIntegerValue()/((IntegerValue)v2).getIntegerValue());
			}
				
		}
		throw new WrongTypeException(ERROR_EVAL);
	}

	public ASTDiv(ASTNode l, ASTNode r)
	{
		lhs = l; rhs = r;
	}
	
	public void compile(CodeBlock c, CompilerEnvironment env)	
	{	
		lhs.compile(c, env);	
		rhs.compile(c, env);	
		c.emit("idiv");
	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		IType l = lhs.typechecker(environment);
		IType r = rhs.typechecker(environment);
		
		if(l.equals(IntegerType.singleton) && r.equals(IntegerType.singleton)) {
			type = l;
			return type;
		}
		throw new WrongTypeException(ERROR_TYPECHECK);
	}
	
	
}
