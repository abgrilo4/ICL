package AST;


import Compiler.CodeBlock;
import Compiler.CompilerEnvironment;
import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.BooleanType;
import Types.IType;
import Types.IntegerType;
import Values.BooleanValue;
import Values.IValue;
import Values.IntegerValue;

public class ASTGreater implements ASTNode{
	private ASTNode left;
	private ASTNode right;
	private IType type;
	private static final String ERROR_EVAL = "Illegal arguments to > operator";
	private static final String ERROR_TYPECHECK = "Wrong types in >";

	public ASTGreater(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IType typechecker(Environment<IType> env) throws WrongTypeException {
		IType l = left.typechecker(env);
		IType r = right.typechecker(env);

		if(l.equals(IntegerType.singleton) && r.equals(IntegerType.singleton)) {
			type = BooleanType.singleton;
			return type;
		}		
		throw new WrongTypeException(ERROR_TYPECHECK);
	}

	@Override
	public IValue eval(Environment<IValue> e) throws WrongTypeException {
		IValue aux1 = left.eval(e);
		IValue aux2 = right.eval(e);

		if(aux1 instanceof IntegerValue) {
			if(aux2 instanceof IntegerValue) {
				return new BooleanValue(((IntegerValue)aux1).getIntegerValue() > ((IntegerValue)aux2).getIntegerValue());	
			}
		}
		throw new WrongTypeException(ERROR_EVAL);
	}

	@Override
	public void compile(CodeBlock c, CompilerEnvironment environment) {
		String L1, L2;
		L1 = c.getLabel();
		L2 = c.getLabel();

		left.compile(c,environment);
		right.compile(c,environment);
		c.emit("if_icmpgt " + L1);
		c.emit("sipush " + 0);
		c.emit("goto " + L2);
		c.emit(L1 + ":");
		c.emit("sipush " + 1);
		c.emit(L2 + ":");			
	}
}
