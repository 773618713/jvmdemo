package com.scy.jvmdemo.reference;

import java.lang.ref.SoftReference;

/**
 * 软引用
 * 一旦内存被占满，垃圾回收器就会将其回收。
 * 实际应用，可以作为图片缓存。
 */
public class T02_SoftReference {
    public static void main(String[] args) {
        SoftReference ref = new SoftReference(new byte[5 * 1024 * 1024]);
        byte[] a = new byte[5 * 1024 * 1024];
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ref.get());
    }
}
