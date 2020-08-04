package com.scy.jvmdemo;

/**
 * 使用字符串做锁，注意字符串可能是同一个对象。
 */
public class ThreadLock {
    public static void main(String[] args) {
        Object lock1 = "";
        Object lock2 = "";
        fun(lock1, lock2);
        fun(lock2, lock1);
    }

    public static void fun(Object outLock, Object inLock) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (outLock) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (inLock) {
                        System.out.println("方法执行");
                    }
                }
            }
        }).start();
    }

}
