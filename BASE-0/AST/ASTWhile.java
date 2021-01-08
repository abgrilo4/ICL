package AST;

import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.BooleanType;
import Types.IType;
import Values.BooleanValue;
import Values.IValue;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;

public class ASTWhile implements ASTNode{

	ASTNode lhs, rhs;
	private IType type;
	private static final String ERROR_EVAL = "Illegal arguments to - operator";
	private static final String ERROR_TYPECHECK = "Wrong types in -";

	public IValue eval(Environment<IValue>	e) throws WrongTypeException
	{ 
		IValue auxCondition = lhs.eval(e);
		
		if(auxCondition instanceof BooleanValue)
		{
			IValue auxDo = null;
			while(((BooleanValue) auxCondition).getBooleanValue() == true)
			{
				auxDo = rhs.eval(e);
				auxCondition = lhs.eval(e);
			}
			BooleanValue result = new BooleanValue(false);
			return result;
		}
		throw new WrongTypeException(ERROR_EVAL);
	}

	public ASTWhile(ASTNode l, ASTNode r)
	{
		lhs = l; rhs = r;
	}

	@SuppressWarnings("unlikely-arg-type")
	public void compile(CodeBlock c, CompilerEnvironment env)	
	{	
		String l1, l2;
		l1 = c.getLabel();
		l2 = c.getLabel();
		
		c.emit(l1 + ":");
		lhs.compile(c, env);
		
		c.equals(l2);
		
		rhs.compile(c, env);
		c.emit("goto" + l1);
		c.emit(l2 + ":");
	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		IType condition = lhs.typechecker(environment);

		if(condition.equals(BooleanType.singleton))
			{
				type = condition;
				return type;
			}
		throw new WrongTypeException(ERROR_TYPECHECK);
	}
}
