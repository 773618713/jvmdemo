package com.scy.jvmdemo.extents.methodcall;

public class Sub extends Parent{

    public void callSay(){
        say();
    }

    public void say(){
        System.out.println("I am Sub");
    }
}
