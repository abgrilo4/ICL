public class PairValues {
	
	private String key;
	private ASTNode value;
	
	
	public PairValues(String key, ASTNode value) 
	{
		this.key = key;
		this.value = value;
	}
	
	public String getKey()
	{
		return key;
	}
	
	
	public ASTNode getValue() 
	{
		return value;
	}
	

}
