package Compiler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import AST.ASTNode;

public class Compiler {
	
	private ASTNode exp;
	
	public Compiler(ASTNode exp)
	{
		this.exp = exp;
	}
	
	public void compile() throws FileNotFoundException
	{
		PrintStream out = new PrintStream(new FileOutputStream("file.j"));
		dumpHeader(out);
		dumpCode(out);		
		dumpFooter(out);
		out.close();
	}
	
	public void dumpHeader(PrintStream out) {
		out.println(".class public File");
		out.println(".super java/lang/Object");
		out.println("");
		out.println(";");
		out.println("; standard initializer");
		out.println(".method public <init>()V");
		out.println("   aload_0");
		out.println("   invokenonvirtual java/lang/Object/<init>()V");
		out.println("   return");
		out.println(".end method");
		out.println("");
		out.println(".method public static main([Ljava/lang/String;)V");
		out.println("       ; set limits used by this method");
		out.println("       .limit locals 10");
		out.println("       .limit stack 256");
		out.println("");
		out.println("       ; setup local variables:");
		out.println("");
		out.println("       ;    1 - the PrintStream object held in java.lang.out");
		out.println("       getstatic java/lang/System/out Ljava/io/PrintStream;");
		out.println("");
		out.println("       ; place your bytecodes here");
		out.println("       ; START");
		out.println("");
	}
	
	
	public void dumpFooter(PrintStream out) {
		out.println("       ; END");
		out.println("");
		out.println("");		
		out.println("       ; convert to String;");
		out.println("       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
		out.println("       ; call println ");
		out.println("       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
		out.println("");		
		out.println("       return");
		out.println("");		
		out.println(".end method");
		out.println("");
	}
	
	public void dumpCode(PrintStream out) throws FileNotFoundException {
		CompilerEnvironment env = new CompilerEnvironment(null);
		CodeBlock code = new CodeBlock();
		exp.compile(code, env);
		out.println("aconst_null");
		out.println("astore 4");
		out.println(code.dump());
		code.dump();
		code.dumpFrames();
	}
	
	
}
