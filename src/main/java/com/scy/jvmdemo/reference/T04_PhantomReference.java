package com.scy.jvmdemo.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

/**
 * 虚引用
 * 一旦执行gc方法，虚引用中的对象就会被回收掉。虚引用会被放入一个队列中。
 * 虚引用的实际引用，堆外内存的管理。
 *  //ByteBuffer b = ByteBuffer.allocate(10);
 *  //ByteBuffer b2 = ByteBuffer.allocateDirect(10);
 */
public class T04_PhantomReference {
    public static List<M> LIST = new LinkedList<>();
    public static ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);
        System.out.println(phantomReference.get());
        System.gc();

        //检测队列中是否存在虚引用
        new Thread(() -> {
            while (true) {
                Reference poll = QUEUE.poll();
                //System.out.println(poll == phantomReference);
                if (poll != null) {
                    System.out.println("虚引用对象被jvm回收了");
                    break;
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class M {
}