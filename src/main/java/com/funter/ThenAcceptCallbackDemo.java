package com.funter;

import java.util.concurrent.*;

/**
 * thenAccept 完成回调  没有返回值的回调
 *
 * @author huangfukexing
 * @date 2024/2/28 10:15
 */
public class ThenAcceptCallbackDemo {
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


        resFuture.thenAccept(res ->{
            System.out.println("第二个异步任务： " + res);
        });


        THREAD_POOL_EXECUTOR.shutdown();
    }
}
