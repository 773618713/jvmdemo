package com.scy.jvmdemo.volatileuse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 内存可见性
 * initFlag没有用volatile修饰。B线程修改initFlag = true时,A线程无法嗅探到。
 */
public class VolatileVisibilitySample {
    private static Logger logger = LoggerFactory.getLogger(VolatileVisibilitySample.class);
    private static boolean initFlag = false;

    public static void main(String[] args) {
        Thread threadA = new Thread(()->{
            while (!initFlag){

            }
            logger.info("线程："+Thread.currentThread().getName()+"当前线程嗅探到initFlag状态的改变");
        },"threadA");
        threadA.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread threadB = new Thread(()->{
           refresh();
        },"threadB");
        threadB.start();
    }

    private static void refresh(){
        logger.info("refresh data......");
        initFlag = true;
        logger.info("refresh data success......");
    }

}
