package AST;

import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.IType;
import Values.CellValue;
import Values.IValue;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;

public class ASTRef implements ASTNode{

	ASTNode ref;
	private static final String ERROR_EVAL = "Illegal arguments to - operator";

	public IValue eval(Environment<IValue>	e) throws WrongTypeException
	{ 
		IValue ref2 = ref.eval(e);
		if(ref2 instanceof CellValue)
			return ((CellValue) (ref2)).getValue();
		throw new WrongTypeException(ERROR_EVAL);
	}

	public ASTRef(ASTNode ref)
	{
		this.ref = ref;
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
