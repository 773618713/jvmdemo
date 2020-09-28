package com.scy.jvmdemo.cacheline;

import java.util.concurrent.CountDownLatch;

/**
 * 缓存行
 * 缓存行为64字节
 * long为8字节
 * 这里如果直接使用数组来实验，是不行的，原因可能是数组的物理内存并不连续？
 */
public class T1 {
    public static long COUNT =1_0000_0000L;
    //使用数组来实验是不受影响的（数组并不是连续的内存？）
    //public static volatile long[] arr2 = new long[9];

    private static class T{
        //这里使用数组填充，有时依然很慢（数组并不是连续的内存？）
        //public long[] arr1 = new long[7];
        public long p1,p2,p3,p4,p5,p6,p7;
        public volatile long x =  0L;
        public long p8,p9,p10,p11,p12,p13,p14;
        //public long[] arr2 = new long[7];
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(()->{
            for (int i = 0; i < COUNT; i++) {
                arr[0].x = i;
            }
            latch.countDown();
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < COUNT; i++) {
                arr[1].x = i;
            }
            latch.countDown();
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        latch.await();
        System.out.println((System.nanoTime()-start)/100_0000);
    }


}
