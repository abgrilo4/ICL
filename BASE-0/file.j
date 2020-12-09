.class public File
.super java/lang/Object

;
; standard initializer
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

.method public static main([Ljava/lang/String;)V
       ; set limits used by this method
       .limit locals 10
       .limit stack 256

       ; setup local variables:

       ;    1 - the PrintStream object held in java.lang.out
       getstatic java/lang/System/out Ljava/io/PrintStream;

       ; place your bytecodes here
       ; START

aconst_null
astore 4
       new frame0
       dup
       invokespecial frame0/<init>()V
       dup
       aload 4
       putfield frame0/sl Ljava/lang/Object;
       astore 4
       aload 4
       sipush 1
       putfield frame0/x0 I
       new frame1
       dup
       invokespecial frame1/<init>()V
       dup
       aload 4
       putfield frame1/sl Lframe0;
       astore 4
       aload 4
       sipush 2
       putfield frame1/x0 I
       new frame2
       dup
       invokespecial frame2/<init>()V
       dup
       aload 4
       putfield frame2/sl Lframe1;
       astore 4
       aload 4
       aload 4
       getfield frame2/sl Lframe1;
       getfield frame1/sl Lframe0;
       getfield frame0/x0 I
       aload 4
       getfield frame2/sl Lframe1;
       getfield frame1/x0 I
       iadd
       putfield frame2/x0 I
       aload 4
       getfield frame2/sl Lframe1;
       getfield frame1/sl Lframe0;
       getfield frame0/x0 I
       aload 4
       getfield frame2/sl Lframe1;
       getfield frame1/x0 I
       iadd
       aload 4
       getfield frame2/x0 I
       iadd
       aload 4
       getfield frame2/sl Lframe1;
       astore 4
       aload 4
       getfield frame1/sl Lframe0;
       astore 4
       aload 4
       getfield frame0/sl Ljava/lang/Object;
       astore 4

       ; END


       ; convert to String;
       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
       ; call println 
       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

       return

.end method

