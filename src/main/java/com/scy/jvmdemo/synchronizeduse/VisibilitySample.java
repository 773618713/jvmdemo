package com.scy.jvmdemo.synchronizeduse;

/**
 * 验证 synchronized 可见性
 * synchronized 也是通过锁来实现可见性的，但是它不能替代 volatile。
 */
public class VisibilitySample {
    public static boolean flag = false;

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            while (true) {
                if (flag) {
                    System.out.println("flag可见为true");
                }
            }
        }).start();

        Thread.sleep(500);
        //synchronized (Test.class) {  //加入同步
            flag = true;
        //}
        System.out.println("改变flag的值为：" + flag);
    }
}

