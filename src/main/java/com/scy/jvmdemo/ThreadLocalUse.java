package com.scy.jvmdemo;

/**
 * ThreadLocal 存在内存泄漏问题
 * https://blog.csdn.net/qq_25775675/article/details/105731434
 */
public class ThreadLocalUse {
    public static void main(String[] args) {
        ThreadLocal tl = new ThreadLocal();
        tl.set("abc");
        tl = null;
        System.gc();
        Thread t = Thread.currentThread();
    }
}
