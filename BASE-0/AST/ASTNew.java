package AST;

import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.IType;
import Types.RefType;
import Values.IValue;
import Values.RefValue;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;

public class ASTNew implements ASTNode{

	private ASTNode node;
	private IType type;

	public IValue eval(Environment<IValue>	e) throws WrongTypeException
	{ 
		IValue aux = node.eval(e);
		return new RefValue(aux);
	}

	public ASTNew(ASTNode node)
	{
		this.node = node;
	}

	public void compile(CodeBlock c, CompilerEnvironment env)	
	{	

	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		type = new RefType(node.typechecker(environment));
		return type;
	}


}
