package com.two;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池没有返回结果的任务的提交方式
 *
 * @author huangfukexing
 * @date 2023/10/30 12:12
 */
public class StopThreadPoolTest {

    /**
     * 使用默认的线程工厂
     */
    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(1, 3, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws InterruptedException {
        THREAD_POOL_EXECUTOR.execute(() ->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("线程池执行任务，线程名为: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        THREAD_POOL_EXECUTOR.execute(() ->{
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("线程池执行任务，线程名为: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        THREAD_POOL_EXECUTOR.shutdownNow();
        TimeUnit.SECONDS.sleep(1);
        //线程池是否被停止
        System.out.println(THREAD_POOL_EXECUTOR.isShutdown());
        //线程池是否处于终止中的状态
        System.out.println(THREAD_POOL_EXECUTOR.isTerminating());
        //线程池是否处于终止状态
        System.out.println(THREAD_POOL_EXECUTOR.isTerminated());
    }
}
