package AST;

import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.DefType;
import Types.IType;
import Values.DefValue;
import Values.IValue;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ASTDef implements ASTNode{

	//private List<PairValues> list;
	private List<String> ids;
	private ASTNode body;
	private Map<String, ASTNode> aux;
	private IType type;
	private List<IType> listOfTypes;
	private static final String ERROR_TYPECHECK = "Wrong types in def operation";
	
	public ASTDef(List<String> ids, ASTNode body, List<IType> listOfTypes)
	{
		this.ids = ids;
		this.body = body;
		this.listOfTypes = listOfTypes;
	}
	
	public IValue eval(Environment<IValue> environment) throws WrongTypeException
	{
		return new DefValue(ids, environment, body);
	}
	

	public void compile(CodeBlock c, CompilerEnvironment env)	
	{	
		Set<Entry<String, ASTNode>> set = aux.entrySet();
		CompilerEnvironment newEnvironment = env.beginScope();
		c.createFrame();
		c.compileDef(set, newEnvironment);
		body.compile(c, newEnvironment);
		c.deleteFrame();
	}

	@Override
	public IType typechecker(Environment<IType> environment) throws WrongTypeException {
		if(listOfTypes.size() != ids.size())
		{
			throw new WrongTypeException(ERROR_TYPECHECK);
		}
		
		Environment<IType> newEnvironment = environment.beginScope();
		for(int i = 0; i < listOfTypes.size(); i++)
		{
			newEnvironment.assoc(ids.get(i), listOfTypes.get(i));
		}
		
		IType nodeType = body.typechecker(newEnvironment);
		type = new DefType(listOfTypes, nodeType);
		return type;
	}
	
	public IType getType()
	{
		return type;
	}

}
