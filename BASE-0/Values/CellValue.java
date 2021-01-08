package Values;


public class CellValue implements IValue {
	IValue v;

	public CellValue(IValue value){
		this.v = value;
	}

	public IValue getValue() {
		return v;
	}

	public void set(IValue value) {
		this.v = value;
	}

	@Override
	public String toString() {
		return String.valueOf(v);
	}

	@Override
	public String show() {
		return "" + v;
	}

}
