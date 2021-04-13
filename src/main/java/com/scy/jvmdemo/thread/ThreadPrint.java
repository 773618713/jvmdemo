package com.scy.jvmdemo.thread;

public class ThreadPrint {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ThreadPrint.class) {
                    try {

                        for (int i = 0; i < 26; i++) {
                            ThreadPrint.class.wait();
                            System.out.println((char) (i + 65));
                            ThreadPrint.class.notify();
                            //ThreadPrint.class.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ThreadPrint.class) {
                    try {
                        Thread.sleep(200);
                        for (int i = 0; i < 26; i++) {
                            System.out.println((i + 1));
                            ThreadPrint.class.notify();
                            ThreadPrint.class.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


}
