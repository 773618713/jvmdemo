package com.scy.jvmdemo.future;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class T02_ListenableFuture {
    public static void main(String[] args) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));

        ListenableFuture<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("currentThread:"+Thread.currentThread().getName());
                return 8;
            }
        });

        Futures.addCallback(future, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        }, service);

        service.shutdown();
    }
}
