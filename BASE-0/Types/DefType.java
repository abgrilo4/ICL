package Types;

import java.util.List;

public class DefType implements IType{
	
	private List<IType> types; //tipos das variaveis
	private IType body;

	public DefType(List<IType> types, IType body) {
		this.types = types;
		this.body = body;
	}
	
	@Override
	public boolean equals(Object n) {
		boolean res = false;
		
		if(n instanceof DefType) {
			
			if(types.size() != ((DefType) n).getTypesOfVariables().size()) {
				return false;
			}
			
			for(int i = 0; i<types.size(); i++) {
				if(!(types.get(i).equals(((DefType) n).getTypesOfVariables().get(i)))) {
					return false;
				}
			}
			
			if(!(body.equals(((DefType) n).getTypeOfBody()))) {
				return false;
			}
			res = true;	
		}	
		return res;
	}

	
	public IType getTypeOfBody() {
		return body;
	}
	
	public List<IType> getTypesOfVariables(){
		return types;
	}
}
