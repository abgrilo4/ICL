package AST;

import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.BooleanType;
import Types.IType;
import Values.BooleanValue;
import Values.IValue;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;

public class ASTIf implements ASTNode{

	private ASTNode condition;
	private ASTNode trueResult;
	private ASTNode falseResult;
	private IType type;
	private static final String ERROR_TYPECHECK = "Wrong types in -";

	public IValue eval(Environment<IValue>	e) throws WrongTypeException
	{ 
		IValue aux  = condition.eval(e);
		
		if(aux instanceof BooleanValue)
		{
			if(((BooleanValue) aux).getBooleanValue() == true)
			{
				IValue trueAux = trueResult.eval(e);
				return trueAux;
			}
			else
			{
				IValue falseAux = falseResult.eval(e);
				return falseAux;
			}
		}
		throw new WrongTypeException(ERROR_TYPECHECK);
	}

	public ASTIf(ASTNode condition, ASTNode trueResult, ASTNode falseResult)
	{
		this.condition = condition;
		this.trueResult = trueResult;
		this.falseResult = falseResult;
	}

	@SuppressWarnings("unlikely-arg-type")
	public void compile(CodeBlock c, CompilerEnvironment env)	
	{	
		String l1;
		String l2;
		l1 = c.getLabel();
		l2 = c.getLabel();
		
		condition.compile(c, env);
		c.emit("sipush " + 0);
		c.equals(l1);
		
		trueResult.compile(c, env);
		c.emit("goto " + l2);
		c.emit(l1 + ":");
		falseResult.compile(c, env);
		c.emit(l2 + ":");
	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		IType condition2 = condition.typechecker(environment);
		
		if(condition2.equals(BooleanType.singleton))
		{
			IType thenCondition = trueResult.typechecker(environment);
			IType elseCondition = falseResult.typechecker(environment);
			
			if(thenCondition.equals(elseCondition)) {
				type = elseCondition;
				return type;
			}
		}
		throw new WrongTypeException(ERROR_TYPECHECK);
	}
}
