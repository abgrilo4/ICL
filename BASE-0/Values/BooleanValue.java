package Values;

public class BooleanValue implements IValue{
	
	private boolean bool;
	
	public BooleanValue(boolean bool)
	{
		this.bool = bool;
	}
	
	public boolean getBooleanValue()
	{
		return bool;
	}

	@Override
	public String show() {
		return "" + bool;
	}
	

}
