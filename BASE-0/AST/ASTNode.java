package AST;

import Environment.Environment;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;


public interface ASTNode {

	int	eval(Environment e);
	void compile(CodeBlock c, CompilerEnvironment environment);
	
}

