package Compiler;

import java.io.PrintStream;

public class StackFrame {
	
	public final static int SL_CONSTANT = 4;
	
	private int id;
	private StackFrame stack;
	private int numberVariables;
	
	public StackFrame(StackFrame stack, int id)
	{
		this.id = id;
		this.stack = stack;
		numberVariables = 0;
	}

	public int getId() 
	{
		return id;
	}

	public StackFrame getStack()
	{
		return stack;
	}

	public int getNumberVariables() 
	{
		return numberVariables;
	}
	
	public void add()
	{
		numberVariables++;
	}
	
	public void dump(PrintStream ps)
	{
		ps.println(".class frame" + id);
		ps.println(".super java/lang/Object");
		if(stack != null)
			ps.println(".field public sl Lframe" + stack.getId() + ";");
		else
			ps.println(".field public sl Ljava/lang/Object;");
		
		for(int i = 0; i < numberVariables; i++)
			ps.println(".field public x" + i + " I");
		ps.println("\n.method public <init>()V");
		ps.println(" aload_0");
		ps.println(" invokenonvirtual java/lang/Object/<init>()V");
		ps.println(" return");
		ps.println(".end method");
	}

}
