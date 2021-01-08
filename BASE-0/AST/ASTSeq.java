package AST;

import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.IType;
import Values.IValue;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;

public class ASTSeq implements ASTNode{

	ASTNode lhs, rhs;
	private IType type;

	public IValue eval(Environment<IValue>	e) throws WrongTypeException
	{ 
		lhs.eval(e);
		return rhs.eval(e);
	}

	public ASTSeq(ASTNode l, ASTNode r)
	{
		lhs = l; rhs = r;
	}

	public void compile(CodeBlock c, CompilerEnvironment env)	
	{	
		lhs.compile(c,	env);	
		c.emit("pop");
		rhs.compile(c,	env);	
	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		type = rhs.typechecker(environment);
		return type;
	}


}
