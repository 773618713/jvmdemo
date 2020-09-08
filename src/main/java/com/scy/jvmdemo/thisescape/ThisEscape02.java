package com.scy.jvmdemo.thisescape;

/**
 * this逃逸
 * 构造函数中启动线程
 */
public class ThisEscape02 {
    final int value;

    public ThisEscape02() {
        new Thread(new TestDemo()).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.value = 1;
    }

    public class TestDemo implements Runnable {
        @Override
        public void run() {
            /**
             * 这里是可以通过ThisEscape.this调用外围类对象的，但是测试外围累对象可能还没有构造完成，
             * 所以会发生this逃逸现象
             */
            System.out.println(ThisEscape02.this.value);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(ThisEscape02.this.value);
        }
    }

    public static void main(String[] args) {
        new ThisEscape02();
    }
}
