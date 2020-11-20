import java.util.HashMap;
import java.util.Map;


public class CompilerEnvironment {
	
	private CompilerEnvironment parent;
	private Map<String, Integer> map;
	
	public CompilerEnvironment(CompilerEnvironment parent)
	{
		this.parent = parent;
		map = new HashMap<String, Integer>();
	}
	
	
	public CompilerEnvironment beginScope() 
	{
		CompilerEnvironment environment = new CompilerEnvironment(this);
		return environment;
	}

	public CompilerEnvironment endScope() 
	{
		return parent;
	}

	public void assoc(String id, int value) 
	{
		map.put(id, value);
		
	}
	
	public int depth(CodeBlock c)
	{
		return c.getSize();
	}
	
	public Coordinates find(String id, int numberOfLevels) 
	{
		if(map.containsKey(id))
			return new Coordinates(numberOfLevels, map.get(id));
		return parent.find(id, numberOfLevels + 1);
	}
	

}
