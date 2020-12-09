package AST;

import Environment.Environment;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ASTDef implements ASTNode{

	private List<PairValues> list;
	private ASTNode body;
	private Map<String, ASTNode> aux;
	
	public int eval(Environment e)
	{
		Environment environment2 = e.beginScope();
		
		for(PairValues v: list)
		{
			int value = v.getValue().eval(e);
			environment2.assoc(v.getKey(), value);
		}
		
		int value2 = body.eval(environment2);
		environment2.endScope();
		return value2;
	}
	
	public ASTDef(List<PairValues> list, ASTNode body, Map<String, ASTNode> aux)
	{
		this.list = list;
		this.body = body;
		this.aux = aux;
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

}
