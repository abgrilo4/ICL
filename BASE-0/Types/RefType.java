package Types;

public class RefType implements IType{
	private IType type;
	
	public RefType(IType type) {
		this.type = type;
	}
	
	@Override
	public boolean equals(Object n) {
		boolean res = false;
		
		if(n instanceof RefType) {
			res = ((RefType) n).getType().equals(type);
		}	
		return res;
	}
	
	public IType getType() {
		return type;
	}

}
