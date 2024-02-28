package com.funter;

import java.util.concurrent.*;

/**
 * 运行一个异步方法  有返回值
 *
 * @author huangfukexing
 * @date 2024/2/28 09:56
 */
public class SupplyAsyncAndResultDemo {

    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> resFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务开始执行");
            return "线程：" + Thread.currentThread().getName() + "- 运行任务.";
        }, THREAD_POOL_EXECUTOR);

        System.out.println("结果集: "+resFuture.get());
        THREAD_POOL_EXECUTOR.shutdown();
    }
}
