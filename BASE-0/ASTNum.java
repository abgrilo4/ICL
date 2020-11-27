public class ASTNum implements ASTNode {

	int val;

	public int eval(Environment e) { return val; }

	public ASTNum(int n)
	{
		val = n;
	}
	
	public void compile(CodeBlock c, CompilerEnvironment env)	
	{	
		c.emit("sipush " + val);
	}

}

