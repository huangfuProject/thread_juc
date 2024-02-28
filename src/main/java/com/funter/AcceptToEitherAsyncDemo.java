package com.funter;

import java.util.concurrent.*;

/**
 *
 *
 * @author huangfukexing
 * @date 2024/2/28 10:15
 */
public class AcceptToEitherAsyncDemo {
    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) {
        CompletableFuture<String> exceptionally = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "第一个任务结果：" + Thread.currentThread().getName();
        }, THREAD_POOL_EXECUTOR);

        CompletableFuture<String> exceptionally2 = CompletableFuture.supplyAsync(() -> {
            return "第二个任务结果：" + Thread.currentThread().getName();
        }, THREAD_POOL_EXECUTOR);


        //applyToEither 方法   传递两个异步任务  谁先执行完使用谁的结果集   没有返回值
        CompletableFuture<Void> stringCompletableFuture = exceptionally.acceptEitherAsync(exceptionally2, res -> {
            System.out.println("获取到结果:" + res);
        }, THREAD_POOL_EXECUTOR);

        stringCompletableFuture.join();

        THREAD_POOL_EXECUTOR.shutdown();
    }
}
