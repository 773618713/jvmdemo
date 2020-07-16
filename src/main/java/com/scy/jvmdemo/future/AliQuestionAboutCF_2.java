package com.scy.jvmdemo.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 问题：可以归为分布式事务失败回滚的手工实现
 * 代码依然不太完善，等待你的进一步完善
 */
public class AliQuestionAboutCF_2 {
    //任务执行结束的三种状态
    static enum Result {
        SUCCESS, FAIL, CANCELLED
    }

    static List<MyTask> tasks = new ArrayList<>();

    public static void main(String[] args) {
        MyTask task1 = new MyTask("task1", 4, Result.SUCCESS);
        MyTask task2 = new MyTask("task2", 3, Result.SUCCESS);
        MyTask task3 = new MyTask("task3", 1, Result.FAIL);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        for (MyTask task : tasks) {
            CompletableFuture f1 = CompletableFuture.supplyAsync(() -> task.runTask())
                    .thenAccept((result) -> callback(result));
        }
    }

    private static void callback(Result result) {
        //可以处理的更加精确一些，根据不同的任务状态，做出到底是取消还是忽略这样的处理 2PC
        //这里要考虑同步，除非Cancel无状态，幂等，否则应该加同步
        if (Result.FAIL == result) {
            for (MyTask _task : tasks) {
                if (_task != tasks) {
                    _task.cancel();
                }
            }
        }
    }

    private static class MyTask {
        private String name;
        private int timeInSeconds;
        private Result ret;

        volatile boolean cancelling = false;
        volatile boolean cancelled = false;

        public MyTask(String name, int timeInSeconds, Result ret) {
            this.name = name;
            this.timeInSeconds = timeInSeconds * 1000;
            this.ret = ret;
        }

        public Result runTask() {
            int interval = 100;
            int total = 0;

            try {
                for (; ; ) {
                    Thread.sleep(interval);//cpu密集型
                    total += interval;
                    if (total >= timeInSeconds) break;
                    if (cancelled) return Result.CANCELLED;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(name + " end!");
            return ret;
        }

        public void cancel() {
            if (!cancelled){
                return;
            }
            synchronized (this) {
                if (cancelled){
                    return;
                }
                cancelling = true;//正在cancel
                System.out.println(name + "cancelling");
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + "cancelled");
                cancelled = true;
            }
        }

    }


}
