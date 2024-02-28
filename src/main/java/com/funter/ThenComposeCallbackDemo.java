package com.funter;

import java.util.concurrent.*;

/**
 * 编排两个具有依赖关系的回调
 *
 * @author huangfukexing
 * @date 2024/2/28 10:15
 */
public class ThenComposeCallbackDemo {
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

        //thenCompose  用于处理两个CompletableFuture的连接操作，他可以将上一个CompletableFuture的结果集传递到下一个CompletableFuture中进行处理，并返回一个CompletableFuture
        CompletableFuture<String> stringCompletableFuture = resFuture.thenCompose(res -> {
            return CompletableFuture.supplyAsync(() ->{
                return "第二个异步任务开始运行，上一个任务的结果集为：" + res;
            });
        });

        System.out.println(stringCompletableFuture.get());
        THREAD_POOL_EXECUTOR.shutdown();
    }
}
