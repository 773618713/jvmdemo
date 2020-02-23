package com.scy.jvmdemo.bytecode.executionengine.methodcall;

/**
 * 方法静态解析演示
 * 使用 invokestatic 调用
 */
public class StaticResolution {

    public static void main(String[] args) {
        StaticResolution.sayHello();
    }

    private static void sayHello() {
        System.out.println("hello world");
    }
}
