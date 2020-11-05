package com.scy.jvmdemo.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

/**
 * 虚引用
 */
public class T04_PhantomReference {
    public static List<M> LIST = new LinkedList<>();
    public static ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
        //ByteBuffer b = ByteBuffer.allocate(10);
        //ByteBuffer b2 = ByteBuffer.allocateDirect(10);
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);
        System.out.println(phantomReference.get());
        System.gc();

        new Thread(() -> {
            while (true) {
                Reference poll = QUEUE.poll();
                if (poll != null) {
                    //System.out.println(poll.toString());
                    System.out.println("虚引用对象被jvm回收了");
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();

    }
}

class M {
    @Override
    protected void finalize() throws Throwable {
        //super.finalize();
        System.out.println("m被回收了");
    }
}