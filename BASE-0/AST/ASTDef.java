package AST;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import Compiler.CodeBlock;
import Compiler.CompilerEnvironment;
import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.IType;
import Values.IValue;

public class ASTDef implements ASTNode{

	private Map<String, ASTNode> aux;
	private List<PairValues> list;
	private ASTNode body;

	public ASTDef(List<PairValues> list, ASTNode body, Map<String, ASTNode> aux)
	{
		this.list = list;
		this.body = body;
		this.aux = aux;
	}
	
	@Override
	public IValue eval(Environment<IValue> e) throws WrongTypeException {
		Environment<IValue> newEnvironment = e.beginScope();

		for(PairValues v: list)
		{
			IValue value = v.getValue().eval(e);
			newEnvironment.assoc(v.getKey(), value);
		}

		IValue value2 = body.eval(newEnvironment);
		newEnvironment.endScope();
		return value2;
	}

	@Override
	public void compile(CodeBlock c, CompilerEnvironment environment) {
		Set<Entry<String, ASTNode>> set = aux.entrySet();
		CompilerEnvironment newEnvironment = environment.beginScope();
		c.createFrame();
		c.compileDef(set, newEnvironment);
		body.compile(c, newEnvironment);
		c.deleteFrame();
	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		// TODO Auto-generated method stub
		return null;
	}

}



