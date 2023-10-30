package com.two;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 默认的线程池的创建方式
 *
 * @author huangfukexing
 * @date 2023/10/30 14:36
 */
public class DefaultThreadPoolCreateTest {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        //执行一次
        //scheduledExecutorService.schedule(() -> System.out.println("定时任务执行"), 1, TimeUnit.SECONDS);
        //固定频率执行
        scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("定时任务执行"), 1, 1, TimeUnit.SECONDS);
    }
}
