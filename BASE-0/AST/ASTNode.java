package AST;

import Environment.Environment;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;
import Exceptions.WrongTypeException;
import Types.IType;
import Values.IValue;


public interface ASTNode {

	IValue	eval(Environment<IValue> e) throws WrongTypeException;
	void compile(CodeBlock c, CompilerEnvironment environment);
	IType typechecker(Environment<IType> environment) throws WrongTypeException;
	
}

