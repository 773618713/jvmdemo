package com.scy.jvmdemo.bytecode.executionengine.methodcall;

/**
 * <h1>方法静态分派演示<h1/>
 * 使用 invokevirtual 调用
 *
 * 方法的重载使用的是静态分派。根据参数的静态类型来决定调用的方法。
 *
 * 这个案例中 StaticDispatch 的实例对象是确定的。
 *
 *Compiled from "StaticDispatch.java"
 * public class com.scy.jvmdemo.bytecode.executionengine.methodcall.StaticDispatch {
 *   public com.scy.jvmdemo.bytecode.executionengine.methodcall.StaticDispatch();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   void sayHello(com.scy.jvmdemo.bytecode.executionengine.methodcall.StaticDispatch$Human);
 *     Code:
 *        0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *        3: ldc           #3                  // String hello,guy!
 *        5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *        8: return
 *
 *   public void sayHello(com.scy.jvmdemo.bytecode.executionengine.methodcall.StaticDispatch$Man);
 *     Code:
 *        0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *        3: ldc           #5                  // String hello,gentleman!
 *        5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *        8: return
 *
 *   public void sayHello(com.scy.jvmdemo.bytecode.executionengine.methodcall.StaticDispatch$Women);
 *     Code:
 *        0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *        3: ldc           #6                  // String hello,lady!
 *        5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *        8: return
 *
 *   public static void main(java.lang.String[]);
 *     Code:
 *        0: new           #7                  // class com/scy/jvmdemo/bytecode/executionengine/methodcall/StaticDispatch$Man
 *        3: dup
 *        4: invokespecial #8                  // Method com/scy/jvmdemo/bytecode/executionengine/methodcall/StaticDispatch$Man."<init>":()V
 *        7: astore_1
 *        8: new           #9                  // class com/scy/jvmdemo/bytecode/executionengine/methodcall/StaticDispatch$Women
 *       11: dup
 *       12: invokespecial #10                 // Method com/scy/jvmdemo/bytecode/executionengine/methodcall/StaticDispatch$Women."<init>":()V
 *       15: astore_2
 *       16: new           #11                 // class com/scy/jvmdemo/bytecode/executionengine/methodcall/StaticDispatch
 *       19: dup
 *       20: invokespecial #12                 // Method "<init>":()V
 *       23: astore_3
 *       24: aload_3
 *       25: aload_1
 *       26: invokevirtual #13                 // Method sayHello:(Lcom/scy/jvmdemo/bytecode/executionengine/methodcall/StaticDispatch$Human;)V
 *       29: aload_3
 *       30: aload_2
 *       31: invokevirtual #13                 // Method sayHello:(Lcom/scy/jvmdemo/bytecode/executionengine/methodcall/StaticDispatch$Human;)V
 *       34: return
 * }
 *
 *
 */
public class StaticDispatch {
    static abstract class Human{
    }

    static class Man extends Human{
    }

    static class Women extends Human{
    }

    void sayHello(Human guy){
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy){
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Women guy){
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Women();
        StaticDispatch sd = new StaticDispatch();
        sd.sayHello(man);
        sd.sayHello(women);
    }
}
