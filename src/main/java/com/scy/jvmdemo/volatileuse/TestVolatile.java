package com.scy.jvmdemo.volatileuse;


import java.util.concurrent.TimeUnit;

/**
 * https://blog.csdn.net/u014674862/article/details/89168376
 */
public class TestVolatile {
    //注意没用volatile修饰数组
    public static long[] arr = new long[20];
    //这里用volatile修饰另一个变量
    //public static volatile int vol = 0;

    public static void main(String[] args) throws Exception {
        //线程1
        new Thread(new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    arr[19] = 2;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //线程2
        new Thread(new Thread(){
            @Override
            public void run() {
                while (true) {
                    //读取vol的值
                    //int i = vol;
                    if (arr[19] == 2) {
                        break;
                    }
                }
                System.out.println("Jump out of the loop!");
            }
        }).start();
    }
}