package com.scy.jvmdemo.sync;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * 使用多线程对一个变量进行自增运算
 * 加synchronized，可以解决线程安全问题
 * https://blog.csdn.net/qq_25775675/article/details/105624494
 */
public class CasAndUnsafe3 {
    private static int m = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        Object o = new Object();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                synchronized (o){
                    for (int j = 0; j < 10000; j++) {
                        m++;
                    }
                    latch.countDown();
                }

            });
        }
        Arrays.stream(threads).forEach((t)->t.start());
        latch.await();
        System.out.println(m);
    }
}
