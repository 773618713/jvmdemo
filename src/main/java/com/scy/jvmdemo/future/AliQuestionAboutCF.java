package com.scy.jvmdemo.future;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * 有这样一个任务T:
 * T由N个子任务构成,每个子任务完成的时长不同。
 * 若其中有一个子任务失败，所有任务结束,T任务失败。
 * 请写程序模拟这个过程
 * 要求 Fail Fast
 */
public class AliQuestionAboutCF {
    public static void main(String[] args) throws IOException {
        MyTask task1 = new MyTask("task1", 4, true);
        MyTask task2 = new MyTask("task2", 3, true);
        MyTask task3 = new MyTask("task3", 1, false);

        CompletableFuture f1 = CompletableFuture.supplyAsync(() -> task1.call())
                .thenAccept((result) -> callback(result));
        CompletableFuture f2 = CompletableFuture.supplyAsync(() -> task2.call())
                .thenAccept((result) -> callback(result));
        CompletableFuture f3 = CompletableFuture.supplyAsync(() -> task3.call())
                .thenAccept((result) -> callback(result));

        System.in.read();
    }

    private static void callback(Boolean result) {
        if (false == result) {
            //处理结束流程
            //通知其它线程结束(回滚)
            //超时处理
            System.exit(0);
        }
    }

    private static class MyTask {
        private String name;
        private int timeInSeconds;
        private boolean ret;

        public MyTask(String name, int timeInSeconds, boolean ret){
            this.name = name;
            this.timeInSeconds = timeInSeconds;
            this.ret = ret;
        }

        public Boolean call(){
            try {
                Thread.sleep(timeInSeconds*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name +" task callback");
            return ret;
        }

    }

}
