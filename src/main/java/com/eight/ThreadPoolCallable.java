package com.eight;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池使用callable
 *
 * @author huangfukexing
 * @date 2023/11/10 21:49
 */
public class ThreadPoolCallable {
    private final static AtomicInteger IDX = new AtomicInteger(0);

    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(1, 3, 5, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "test-" + IDX.getAndIncrement());
        }
    }, new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) throws Exception {
        Future<String> submit = THREAD_POOL_EXECUTOR.submit(new Task());
        System.out.println(submit.get());

    }

    private static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            try {
                Thread.sleep(2000);
            }catch (Exception e) {
                e.printStackTrace();
            }
            return "我是返回结果";
        }
    }
}
