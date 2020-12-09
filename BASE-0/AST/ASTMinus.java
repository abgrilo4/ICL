package AST;

import Environment.Environment;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;

public class ASTMinus implements ASTNode{

	ASTNode lhs, rhs;

	public int eval(Environment	e)
	{ 
		int v1 = lhs.eval(e);
		int v2 = rhs.eval(e);
		return v1-v2; 
	}

	public ASTMinus(ASTNode l, ASTNode r)
	{
		lhs = l; rhs = r;
	}
	
	public void compile(CodeBlock c, CompilerEnvironment env)	
	{	
		lhs.compile(c,	env);	
		rhs.compile(c,	env);	
		c.emit("isub");
	}
}
