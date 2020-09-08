package com.scy.jvmdemo.thisescape;

/**
 * this逃逸
 * 构造器内将this传递出去
 */
public class ThisEscape01 {
    //final常量会保证在构造器内完成初始化（但是仅限于未发生this逃逸的情况下，具体可以看多线程对final保证可见性的实现）
    final int i;
    int j;
    static ThisEscape01 thisEscape;

    public ThisEscape01() {
        this.thisEscape = this;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i = 1;
        j = 1;
    }

    public static void main(String[] args) {
        //在第一个线程里，new ThisEscape对象。
        new Thread(() -> {
            new ThisEscape01();
        }).start();

        new Thread(() -> {
            System.out.println("j=" + thisEscape.j);
            System.out.println("i=" + thisEscape.i);
        }).start();
    }
}