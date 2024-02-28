package com.funter;

import java.util.concurrent.*;

/**
 * 编排两个没有依赖关系的回调
 *
 * @author huangfukexing
 * @date 2024/2/28 10:15
 */
public class AnyOfCallbackDemo {
    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> resFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "第一个任务结果：" + Thread.currentThread().getName();
        }, THREAD_POOL_EXECUTOR);


        CompletableFuture<String> stringCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "第二个任务结果：" + Thread.currentThread().getName();
        });

        CompletableFuture<String> stringCompletableFuture3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "第三个任务结果：" + Thread.currentThread().getName();
        });

        CompletableFuture<String> stringCompletableFuture4 = CompletableFuture.supplyAsync(() -> {
            return "第四个任务结果：" + Thread.currentThread().getName();
        });

        //传递组合多个任务，当其中任意一个任务完成后，该方法就会立即返回任务，返回的结果就是完成的那个CompletableFuture
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(resFuture1, stringCompletableFuture2, stringCompletableFuture3, stringCompletableFuture4);


        System.out.println(objectCompletableFuture.get());
        THREAD_POOL_EXECUTOR.shutdown();
    }
}
