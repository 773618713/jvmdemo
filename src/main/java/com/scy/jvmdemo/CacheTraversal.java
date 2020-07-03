package com.scy.jvmdemo;

/**
 * a[i][j]遍历和 a[j][i]遍历的区别
 * 因为cpu缓存行的原因 a[i][j]遍历会快很多
 * https://honeypps.com/backend/row-major-and-column-major-and-iliffe-vector/
 */
public class CacheTraversal {
    public static void main(String[] args) {
        traversal_ij();
        traversal_ji();
    }

    /**
     * a[i][j]遍历
     */
    private static void traversal_ij() {
        int LEN = 10000;
        int[][] arr = new int[LEN][LEN];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                arr[i][j] = 1;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("a[i][j]遍历时间:" + (endTime - startTime));
    }

    /**
     * a[j][i]遍历
     */
    private static void traversal_ji() {
        int LEN = 10000;
        int[][] arr = new int[LEN][LEN];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                arr[j][i] = 1;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("a[j][i]遍历时间:" + (endTime - startTime));
    }

}
