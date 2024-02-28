package com.funter;

import java.util.concurrent.*;

/**
 * 运行一个异步方法  无返回值
 *
 * @author huangfukexing
 * @date 2024/2/28 09:56
 */
public class RunAsyncNoResultDemo {

    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(() -> System.out.println("线程：" + Thread.currentThread().getName() + "- 运行任务."), THREAD_POOL_EXECUTOR);
    }
}
