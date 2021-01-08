package AST;

import Compiler.CodeBlock;
import Compiler.CompilerEnvironment;
import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.IType;
import Types.RefType;
import Values.IValue;
import Values.RefValue;

public class ASTDesref implements ASTNode {
	private ASTNode node;
	private IType type;
	private static final String ERROR_EVAL = "Illegal arguments to !(desref) operator";
	private static final String ERROR_TYPECHECK = "Wrong types in ! (desref)";

	public ASTDesref(ASTNode node) {
		this.node = node;
	}

	@Override
	public Values.IValue eval(Environment<Values.IValue> e) throws WrongTypeException {
		IValue aux = node.eval(e);

		if(aux instanceof RefValue) {
			return ((RefValue) aux).getRef();
		}	
		throw new WrongTypeException(ERROR_EVAL);
	}

	@Override
	public void compile(CodeBlock c, CompilerEnvironment environment) {
		// TODO Auto-generated method stub

	}

	@Override
	public Types.IType typechecker(Environment<IType> environment) throws WrongTypeException {
		IType aux = node.typechecker(environment);

		if(aux instanceof RefType) {
			type = ((RefType) aux).getType();
			return type;
		}
		throw new WrongTypeException(ERROR_TYPECHECK);	
	}


}
