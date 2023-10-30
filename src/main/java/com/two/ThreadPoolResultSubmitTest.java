package com.two;

import java.util.concurrent.*;

/**
 * 线程池有返回结果的任务的提交方式
 *
 * @author huangfukexing
 * @date 2023/10/30 12:12
 */
public class ThreadPoolResultSubmitTest {

    /**
     * 使用默认的线程工厂
     */
    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<String> future = THREAD_POOL_EXECUTOR.submit(() -> {
            System.out.println("我执行了");
            return String.format("我是执行结果，我被线程【%s】执行", Thread.currentThread().getName());
        });

        System.out.println("线程执行结果: "+future.get());
    }
}
