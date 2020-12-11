package Environment;

import java.util.HashMap;
import java.util.Map;

public class Environment<T>{
	private Environment<T> parent;
	private Map<String, T> map;
	
	public Environment(Environment<T> parent)
	{
		this.parent = parent;
		map = new HashMap<String, T>();
	}
	
	
	public Environment<T> beginScope() 
	{
		Environment<T> environment = new Environment<T>(this);
		return environment;
	}

	public Environment<T> endScope() 
	{
		return parent;
	}

	public void assoc(String id, T value) 
	{
		map.put(id, value);
		
	}

	public T find(String id) 
	{
		if(map.containsKey(id))
			return map.get(id);
		return parent.find(id);
	}
	
		
}
