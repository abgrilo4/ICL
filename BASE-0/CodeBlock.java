import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;

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
		
}
