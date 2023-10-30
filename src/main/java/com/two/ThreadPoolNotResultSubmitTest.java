package com.two;

import java.util.concurrent.*;

/**
 * 线程池没有返回结果的任务的提交方式
 *
 * @author huangfukexing
 * @date 2023/10/30 12:12
 */
public class ThreadPoolNotResultSubmitTest {

    /**
     * 使用默认的线程工厂
     */
    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        THREAD_POOL_EXECUTOR.execute(() ->{
            System.out.println("线程池执行任务，线程名为: " + Thread.currentThread().getName());
        });
    }
}
