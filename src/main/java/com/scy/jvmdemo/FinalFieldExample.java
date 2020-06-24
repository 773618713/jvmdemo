package com.scy.jvmdemo;

/**
 * 验证对象的半初始化（没有验证成功）
 * https://zhuanlan.zhihu.com/p/100536345
 */
public class FinalFieldExample {
    final int x;
    int y;
    static FinalFieldExample f;

    public FinalFieldExample() {
        x = 3;
        y = 4;
    }

    static void writer() {
        f = new FinalFieldExample();
    }

    static void reader() {
        if (f != null) {
            int i = f.x;  // guaranteed to see 3
            int j = f.y;  // could see 0
            if (j == 0) {
                System.out.println(i);
                System.out.println(j);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) writer();
        }).start();
        new Thread(() -> {
            while (true) reader();
        }).start();
    }
}
