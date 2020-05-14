package com.scy.jvmdemo.bytecode.executionengine.methodcall;

/**
 * 方法的动态分派演示
 * 使用 invokevirtual 调用
 * 在编译阶段不能够确定方法是哪一个
 *
 * 方法的重写使用动态分派。调用方法取决于实例类型（方法接收者）。
 *
 */
public class DynamicDispatch {

    static abstract class Human{
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Women extends Human {
        @Override
        protected void sayHello() {
            System.out.println("women say hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Women();
        man.sayHello();
        women.sayHello();

        man = new Women();
        man.sayHello();
    }

}
