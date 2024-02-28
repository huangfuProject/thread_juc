package com.funter;

import java.util.concurrent.*;

/**
 * 没有参数  没有结果集的完成回调
 *
 * @author huangfukexing
 * @date 2024/2/28 10:30
 */
public class ThenRunCallbackDemo {

    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> resFuture = CompletableFuture.runAsync(() -> {
            System.out.println("异步任务开始执行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步任务完成运行, 结果集为：" + "线程：" + Thread.currentThread().getName() + "- 运行任务.");

        }, THREAD_POOL_EXECUTOR);

        //这里也开始修改为链式调用   使用的是上一个任务的线程池
        resFuture.thenRun(() -> {
            System.out.println("上一个异步任务完成， 回调任务开始运行");

        });

        THREAD_POOL_EXECUTOR.shutdown();
    }
}
