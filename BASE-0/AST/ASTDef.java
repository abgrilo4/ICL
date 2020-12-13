package AST;

import Environment.Environment;
import Exceptions.WrongTypeException;
import Types.IType;
import Values.IValue;
import Compiler.CompilerEnvironment;
import Compiler.CodeBlock;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ASTDef implements ASTNode{

	//private List<PairValues> list;
	private Map<String, ASTNode> mapAux;
	private ASTNode body;
	private IType type;
    private List<IType> types;
	private static final String ERROR_TYPECHECK = "Wrong types in def operation";
	
		public IValue eval(Environment<IValue> e) throws WrongTypeException
		{
			Environment<IValue> environment2 = e.beginScope();
			Set<Entry<String, ASTNode>> set = mapAux.entrySet();
			
			
			for(Entry<String, ASTNode> entry: set)
			{
				IValue value = entry.getValue().eval(environment2);
				environment2.assoc(entry.getKey(), value);
			}
			
			IValue value2 = body.eval(environment2);
			e = environment2.endScope();
			return value2;
		}
		
		public ASTDef(Map<String, ASTNode> mapAux, ASTNode body, List<IType> types)
		{
			this.mapAux= mapAux;
			this.body = body;
			this.types = types;
		}
		
		public void compile(CodeBlock c, CompilerEnvironment env)	
		{	
			Set<Entry<String, ASTNode>> set = mapAux.entrySet();
			CompilerEnvironment newEnvironment = env.beginScope();
			c.createFrame();
			c.compileDef(set, newEnvironment);
			body.compile(c, newEnvironment);
			c.deleteFrame();
		}

		@Override
		public IType typechecker(Environment<IType> environment) throws WrongTypeException {
			Set<Entry<String, ASTNode>> set = mapAux.entrySet();
			
			int counter = 0;
			
			for(Entry<String, ASTNode> entry: set) { 
				IType type1 = entry.getValue().typechecker(environment);
				IType type2 = types.get(counter);
				
				if(!(type1.equals(type2))) {
					throw new WrongTypeException(ERROR_TYPECHECK);
				}
				counter++;
			}
			
			Environment<IType> newEnvironment = environment.beginScope(); 

			counter = 0;
			for(Entry<String, ASTNode> entry: set) {
				newEnvironment.assoc(entry.getKey(), types.get(counter));
				counter++;
			}

		    type = body.typechecker(newEnvironment); 
			environment = newEnvironment.endScope();
		     
		    return type;   
		}
	}

	
