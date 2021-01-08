package AST;

import Compiler.CodeBlock;
import Compiler.CompilerEnvironment;
import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.BooleanType;
import Types.IType;
import Values.BooleanValue;
import Values.IValue;

public class ASTNot implements ASTNode {
	private ASTNode node;
	private IType type;
	private static final String ERROR_EVAL = "Illegal arguments to ~ operator";
	private static final String ERROR_TYPECHECK = "Wrong types in ~";

	public ASTNot(ASTNode node) {
		this.node = node;
	}

	@Override
	public IValue eval(Environment<IValue> e) throws WrongTypeException {
		IValue aux1 = node.eval(e);
		if(aux1 instanceof BooleanValue) {
			return new BooleanValue(!((BooleanValue)aux1).getBooleanValue());	
			
		}
		throw new WrongTypeException(ERROR_EVAL);
	}

	@Override
	public void compile(CodeBlock c, CompilerEnvironment environment) {
		String L1, L2;
		L1 = c.getLabel();
		L2 = c.getLabel();
		
		node.compile(c,environment);
		c.emit("if_icmpne " + L1);
		c.emit("sipush " + 0);
		c.emit("goto " + L2);
		c.emit(L1 + ":");
		c.emit("sipush " + 1);
		c.emit(L2 + ":");		
	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		IType aux = node.typechecker(environment);

		if(aux.equals(BooleanType.singleton)) {
			type = aux;
			return type;
		}		
		throw new WrongTypeException(ERROR_TYPECHECK);
	}

}
