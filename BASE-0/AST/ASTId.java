package AST;

import Environment.Environment;
import Compiler.CompilerEnvironment;
import Compiler.Coordinates;
import Compiler.CodeBlock;

public class ASTId implements ASTNode{

	private String id;
	
	public int eval(Environment e) 
	{
		return e.find(id);
	}
	
	public ASTId(String id)
	{
		this.id = id;
	}
	
	public void compile(CodeBlock c, CompilerEnvironment env)	
	{	
		Coordinates coord = env.find(id, 0);
		c.compileId(coord);
	}

}
