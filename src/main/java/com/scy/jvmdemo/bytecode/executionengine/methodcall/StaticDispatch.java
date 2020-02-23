package com.scy.jvmdemo.bytecode.executionengine.methodcall;

/**
 * <h1>方法静态分派演示<h1/>
 * 使用 invokevirtual 调用
 *
 * 这个案例中 StaticDispatch 的实例对象是确定的。
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
