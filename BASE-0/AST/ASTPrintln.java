package AST;

import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.IType;
import Values.IValue;
import Values.StringValue;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;

public class ASTPrintln implements ASTNode{

	String value;
	
	public IValue eval(Environment<IValue>	e) throws WrongTypeException
	{ 
		return new StringValue(value);
	}

	public ASTPrintln()
	{
		this.value = "\n";
	}
	
	public ASTPrintln(String value) {
		this.value = value + "\n";
	}


	public void compile(CodeBlock c, CompilerEnvironment env)	
	{		
		//TODO
	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		//TODO
		return null;
	}


}
