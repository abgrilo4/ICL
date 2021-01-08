package Values;

public class RefValue implements IValue{
	private IValue value;
	
	public RefValue(IValue value) {
		this.value = value;
	}
	
	public IValue getRef() {
		return value;
	}
	
	public void setRef(IValue value) {
		this.value = value;
	}

	@Override
	public String show() {
		return value.show();
	}
}
