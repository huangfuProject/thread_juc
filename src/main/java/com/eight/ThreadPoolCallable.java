package com.eight;

import java.util.concurrent.*;

/**
 * 线程池使用callable
 *
 * @author huangfukexing
 * @date 2023/11/10 21:49
 */
public class ThreadPoolCallable {

    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) throws Exception {
        Future<String> submit = THREAD_POOL_EXECUTOR.submit(new Task());
        Thread.sleep(1000);
        System.out.println(submit.cancel(true));
        //System.out.println(submit.get());
        System.out.println(THREAD_POOL_EXECUTOR.getCorePoolSize());
        System.out.println(THREAD_POOL_EXECUTOR.getActiveCount());


        Future<String> submit1 = THREAD_POOL_EXECUTOR.submit(new Task());
        Thread.sleep(1000);
        //System.out.println(submit.cancel(true));
        System.out.println(submit1.get());
        System.out.println(THREAD_POOL_EXECUTOR.getCorePoolSize());
        System.out.println(THREAD_POOL_EXECUTOR.getActiveCount());
    }

    private static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(3000);
            return "我执行完了";
        }
    }
}
