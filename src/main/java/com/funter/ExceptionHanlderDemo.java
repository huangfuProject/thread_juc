package com.funter;

import java.util.concurrent.*;

/**
 * 编排两个没有依赖关系的回调
 *
 * @author huangfukexing
 * @date 2024/2/28 10:15
 */
public class ExceptionHanlderDemo {
    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) {
        CompletableFuture<String> exceptionally = CompletableFuture.supplyAsync(() -> {
            return "第一个任务结果：" + Thread.currentThread().getName();
        }, THREAD_POOL_EXECUTOR).thenApply((res) -> {
            int i = 1 / 0;
            return res + "\t第二个任务结果：" + Thread.currentThread().getName();
        }).handle((res, ex) ->{
            if(ex != null) {
                System.out.println("上一步出现异常了, 异常信息为：" + ex.getMessage());
                return "异常恢复后.";
            }
            return res;
        }).thenApply((res) -> {
            return res + "\t第三个任务结果：" + Thread.currentThread().getName();
        }).thenApply((res) -> {
            return res + "\t第四个任务结果：" + Thread.currentThread().getName();
        });

        System.out.println(exceptionally.join());

        THREAD_POOL_EXECUTOR.shutdown();
    }
}
