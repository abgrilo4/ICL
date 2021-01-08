package AST;

import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.IType;
import Values.IValue;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;

public class ASTPrint implements ASTNode{

	private ASTNode node;
	private IType type;
	
	public IValue eval(Environment<IValue>	e) throws WrongTypeException
	{ 
		IValue aux = node.eval(e);
		
		return aux;
	}

	public ASTPrint(ASTNode node)
	{
		this.node = node;
	}

	public void compile(CodeBlock c, CompilerEnvironment env)	
	{		
		c.print(node, env);
	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		type = node.typechecker(environment);
		return type;
	}


}
