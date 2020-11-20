import java.util.HashMap;
import java.util.Map;

public class Environment{
	private Environment parent;
	private Map<String, Integer> map;
	
	public Environment(Environment parent)
	{
		this.parent = parent;
		map = new HashMap<String, Integer>();
	}
	
	
	public Environment beginScope() 
	{
		Environment environment = new Environment(this);
		return environment;
	}

	public Environment endScope() 
	{
		return parent;
	}

	public void assoc(String id, int value) 
	{
		map.put(id, value);
		
	}

	public int find(String id) 
	{
		if(map.containsKey(id))
			return map.get(id);
		return parent.find(id);
	}
	
		
}
