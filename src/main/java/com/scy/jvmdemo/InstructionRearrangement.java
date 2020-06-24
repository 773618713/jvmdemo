package com.scy.jvmdemo;

/**
 * 验证指令集重排序
 */
public class InstructionRearrangement {
    static int a = 0, b = 0, x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true) {
            ++i;
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            Thread one = new Thread(new Runnable() {
                public void run() {
                    a = 1;
                    x = b;
                }
            });

            Thread other = new Thread(new Runnable() {
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            one.start();
            other.start();
            one.join();
            other.join();
            //没有指令重排，x和y不可能同时为0
            if (x == 0 && y == 0) {
                System.out.println(i + "次,x=" + x + ",y=" + y);
            }
        }
    }

}
