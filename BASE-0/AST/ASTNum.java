package AST;

import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.IType;
import Types.IntegerType;
import Values.IValue;
import Values.IntegerValue;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;

public class ASTNum implements ASTNode {

	int val;

	public ASTNum(int n)
	{
		val = n;
	}
	
	public IValue eval(Environment<IValue> e) { return new IntegerValue(val); }

	
	public void compile(CodeBlock c, CompilerEnvironment env)	
	{	
		c.emit("sipush " + val);
	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		return IntegerType.singleton;
	}

}

