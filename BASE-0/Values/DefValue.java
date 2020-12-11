package Values;

import java.util.List;

import AST.ASTNode;
import Environment.Environment;

public class DefValue implements IValue{

	private List<String> ids;
	private Environment<IValue> environment;
	private ASTNode node;
	
	public DefValue(List<String> ids, Environment<IValue> environment, ASTNode node)
	{
		this.ids = ids;
		this.environment = environment;
		this.node = node;
	}
	
	
	
	public List<String> getIds() {
		return ids;
	}



	public Environment<IValue> getEnvironment() {
		return environment;
	}



	public ASTNode getNode() {
		return node;
	}



	@Override
	public String show() {
		// TODO Auto-generated method stub
		return null;
	}

}
