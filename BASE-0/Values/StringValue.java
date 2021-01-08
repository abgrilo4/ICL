package Values;

public class StringValue implements IValue{

	private String value;

	public StringValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public String show() {
		return "" + value;
	}
}
