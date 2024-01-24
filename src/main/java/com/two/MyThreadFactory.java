package com.two;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 自定义线程池工厂
 *
 * @author huangfukexing
 * @date 2024/1/19 20:44
 */
public class MyThreadFactory implements ThreadFactory {

    /**
     * 线程名称递增id
     */
    private final static AtomicLong IDX = new AtomicLong();

    @Override
    public Thread newThread(Runnable r) {
        //将任务包装为线程
        Thread thread = new Thread(r);
        //设置线程名称
        thread.setName("test-Thread-"+IDX.getAndIncrement());
        return thread;

    }
}
