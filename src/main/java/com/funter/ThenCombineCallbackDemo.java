package com.funter;

import java.util.concurrent.*;

/**
 * 编排两个没有依赖关系的回调
 *
 * @author huangfukexing
 * @date 2024/2/28 10:15
 */
public class ThenCombineCallbackDemo {
    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> resFuture = CompletableFuture.supplyAsync(() -> {
            return "第一个任务结果：" + Thread.currentThread().getName();
        }, THREAD_POOL_EXECUTOR);


        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "第二个任务结果：" + Thread.currentThread().getName();
        });

        CompletableFuture<String> stringCompletableFuture1 = resFuture.thenCombine(stringCompletableFuture, (r1, r2) -> {
            return "最终结果：" + r1 + "\t" + r2;
        });

        System.out.println(stringCompletableFuture1.get());
        THREAD_POOL_EXECUTOR.shutdown();
    }
}
