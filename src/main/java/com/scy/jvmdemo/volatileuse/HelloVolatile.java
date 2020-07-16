package com.scy.jvmdemo.volatileuse;

/**
 * https://blog.csdn.net/qq_25775675/article/details/107168357
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly
 * 查看编译后的汇编码
 */
public class HelloVolatile {
    public static volatile int i = 0;
    public static void main(String[] args) {
        for (int j = 0; j < 100000; j++) {
            m();
            n();
        }
    }
    public static synchronized void m() {
    }
    public static void n() {
        i++;
    }
}
