package Values;

public class IntegerValue implements IValue{

	private int value;
	
	public IntegerValue(int value)
	{
		this.value = value;
	}
	
	public int getIntegerValue()
	{
		return value;
	}
	
	@Override
	public String show() {
		return "" + value;
	}

}
