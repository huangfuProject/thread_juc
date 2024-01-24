package com.two;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义的拒绝策略
 *
 * @author huangfukexing
 * @date 2024/1/19 20:35
 */
public class MyPolicy implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("线程池已经达到最大极限，该任务被丢弃...");
    }
}
