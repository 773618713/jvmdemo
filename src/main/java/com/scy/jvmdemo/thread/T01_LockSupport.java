package com.scy.jvmdemo.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * 两个线程交替输出A 1 B 2 C 3...
 * 一个线程输出字母，一个线程输出数字
 */
public class T01_LockSupport {
    private static Thread t1 = null;
    private static Thread t2 = null;

    public static void main(String[] args) {
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 26; i++) {
                    System.out.println(i + 1);
                    LockSupport.unpark(t2);//叫醒t2
                    LockSupport.park();//阻塞t1 当前线程
                }
            }
        }, "t1");

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 26; i++) {
                    LockSupport.park();//阻塞t2 当前线程
                    System.out.println((char) (i + 65));
                    LockSupport.unpark(t1);//叫醒t1
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
