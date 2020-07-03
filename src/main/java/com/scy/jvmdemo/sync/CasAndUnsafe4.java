package com.scy.jvmdemo.sync;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用多线程对一个变量进行自增运算
 * 使用AtomicInteger，可以解决线程安全问题
 * https://blog.csdn.net/qq_25775675/article/details/105624494
 */
public class CasAndUnsafe4 {
    private static AtomicInteger m = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    m.incrementAndGet();//m++
                }
                latch.countDown();
            });
        }
        Arrays.stream(threads).forEach((t)->t.start());
        latch.await();
        System.out.println(m);
    }
}
