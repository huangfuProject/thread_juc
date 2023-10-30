package com.two;

import java.util.concurrent.*;

/**
 * 线程池参数
 *
 * @author huangfukexing
 * @date 2023/10/30 11:33
 */
public class ThreadPoolParamTest {
    public static void main(String[] args) {


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        }, new ThreadPoolExecutor.AbortPolicy());

    }
}
