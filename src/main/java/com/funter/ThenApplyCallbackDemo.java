package com.funter;

import java.util.concurrent.*;

/**
 * 完成回调
 *
 * @author huangfukexing
 * @date 2024/2/28 10:15
 */
public class ThenApplyCallbackDemo {
    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> resFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务开始执行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步任务完成运行");
            return "线程：" + Thread.currentThread().getName() + "- 运行任务.";
        }, THREAD_POOL_EXECUTOR);

        //这里也开始修改为链式调用   使用的是上一个任务的线程池
        CompletableFuture<String> stringCompletableFuture = resFuture.thenApply(res -> {
            System.out.println("上一个异步任务完成， 回调任务开始运行");
            System.out.println("上一步的结果为：" + res);
            return res + "-new";
        });

        System.out.println(stringCompletableFuture.get());
        THREAD_POOL_EXECUTOR.shutdown();
    }
}
