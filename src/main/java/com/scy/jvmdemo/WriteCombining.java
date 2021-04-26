package com.scy.jvmdemo;

/**
 * 合并写技术
 * 参考：
 * https://www.bilibili.com/video/BV1b7411J7Xi?from=search&seid=8953377850879822733
 * https://blog.csdn.net/aigoogle/article/details/41517293
 *
 */
public class WriteCombining {
    private final static int ITERATIONS = Integer.MAX_VALUE;
    private final static int MASK = 127;
    private static byte[] arrayA = new byte[MASK + 1];
    private static byte[] arrayB = new byte[MASK + 1];
    private static byte[] arrayC = new byte[MASK + 1];
    private static byte[] arrayD = new byte[MASK + 1];
    private static byte[] arrayE = new byte[MASK + 1];
    private static byte[] arrayF = new byte[MASK + 1];

    public static void main(String[] args) {
        runCaseOne();
        runCaseTwo();
    }

    /**
     * 单次循环比较慢
     */
    private static void runCaseOne() {
        long start = System.nanoTime();
        int i = ITERATIONS;
        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i;
            arrayA[slot] = b;
            arrayB[slot] = b;
            arrayC[slot] = b;
            arrayD[slot] = b;
            arrayE[slot] = b;
            arrayF[slot] = b;
        }
        System.out.println(System.nanoTime() - start);
    }

    /**
     * 多次循环比较快
     */
    private static void runCaseTwo() {
        long start = System.nanoTime();
        int i = ITERATIONS;
        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i;
            arrayA[slot] = b;
            arrayB[slot] = b;
            arrayC[slot] = b;
        }

        i = ITERATIONS;
        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i;
            arrayD[slot] = b;
            arrayE[slot] = b;
            arrayF[slot] = b;
        }
        System.out.println(System.nanoTime() - start);
    }


}
