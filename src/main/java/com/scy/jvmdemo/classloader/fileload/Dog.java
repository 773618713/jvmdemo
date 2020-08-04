package com.scy.jvmdemo.classloader.fileload;

import java.lang.reflect.Method;

public class Dog {
    public void eat() {
        System.out.println("dog eat");
        try {
            Class<?> clazz = Class.forName("com.scy.jvmdemo.classloader.fileload.Cat");

            Object o = clazz.newInstance();
            Method method = clazz.getDeclaredMethod("eat");
            method.invoke(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
