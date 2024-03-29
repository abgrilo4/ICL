package Compiler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;

import AST.ASTNode;

public class CodeBlock {

	private ArrayList<String> bytecodes;
	private StackFrame current;
	private int pos;
	private ArrayList<StackFrame> frames;

	public CodeBlock() {
		pos = 0;
		bytecodes = new ArrayList<String>();
		frames = new ArrayList<StackFrame>();
	}

	public void emit(String bytecode)
	{
		bytecodes.add(bytecode);
	}

	public int getSize()
	{
		return pos;
	}

	public void createFrame()
	{
		StackFrame frame = new StackFrame(current, frames.size());
		bytecodes.add("new frame" + frame.getId());
		bytecodes.add("dup");
		bytecodes.add("invokespecial frame" + frame.getId() + "/<init>()V");
		bytecodes.add("dup");
		bytecodes.add("aload 4");
		if(current == null)
			bytecodes.add("putfield frame"  + frame.getId() + "/sl Ljava/lang/Object;");
		else 
			bytecodes.add("putfield frame"  + frame.getId() + "/sl Lframe" + current.getId() + ";");
		bytecodes.add("astore 4");
		current = frame;
		frames.add(frame);			
	}

	public void deleteFrame()
	{
		bytecodes.add("aload 4");
		if(current.getId() == 0)
			bytecodes.add("getfield frame"  + current.getId() + "/sl Ljava/lang/Object;");
		else
			bytecodes.add("getfield frame"  + current.getId() + "/sl Lframe" + current.getStack().getId() + ";");
		bytecodes.add("astore 4");
		current = current.getStack();
	}

	public void compileId(Coordinates coord)
	{
		StackFrame tmp = current;
		bytecodes.add("aload 4");
		for(int i = 0; i < coord.getLevel(); i++)
		{
			bytecodes.add("getfield frame"  + tmp.getId() + "/sl Lframe" + tmp.getStack().getId() + ";");
			tmp = tmp.getStack();
		}
		bytecodes.add("getfield frame"  + tmp.getId() + "/x" + coord.getOffset() + " I");
	}

	public void compileDef(Set<Entry<String, ASTNode>> set, CompilerEnvironment env)
	{
		for(Entry<String, ASTNode> e: set)
		{
			bytecodes.add("aload 4");
			e.getValue().compile(this, env);
			env.assoc(e.getKey(), current.getNumberVariables());
			bytecodes.add("putfield frame"  + current.getId() + "/x" + current.getNumberVariables() + " I");
			current.add();
		}
	}
	public String dump()
	{
		String res ="";
		for(String s: bytecodes)
			res += ("       "+s+"\n");
		return res;
	}

	void dumpFrames() throws FileNotFoundException { 
		for( StackFrame frame: frames) {
			PrintStream out = new PrintStream(new FileOutputStream("frame" + frame.getId()+".j"));
			frame.dump(out);
		}
	}

	public void bool(boolean value) {
		if(value)
			bytecodes.add("sipush 1");
		else
			bytecodes.add("sipush 0");

	}

	public String getLabel() {
		return "L"+(pos++);
	}

	public void print(ASTNode node, CompilerEnvironment env) {
		bytecodes.add("       getstatic java/lang/System/out Ljava/io/PrintStream;");
		node.compile(this, env);
		bytecodes.add("       ; convert to String;");
		bytecodes.add("       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
		bytecodes.add("       ; call println ");
		bytecodes.add("       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
		node.compile(this, env);		
	}
}
