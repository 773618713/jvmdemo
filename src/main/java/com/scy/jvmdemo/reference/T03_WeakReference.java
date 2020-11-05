package com.scy.jvmdemo.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 * 垃圾回收一旦执行就会回收。
 * 实际应用，ThreadLocal。
 */
public class T03_WeakReference {
    public static void main(String[] args) {
        WeakReference weakReference = new WeakReference(new byte[5 * 1024 * 1024]);
        System.gc();
        System.out.println(weakReference.get());
    }
}
