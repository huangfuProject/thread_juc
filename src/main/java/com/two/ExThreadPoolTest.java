package com.two;

import java.util.Date;
import java.util.concurrent.*;

/**
 * 扩展线程池  记录任务的开始时间和任务的结束时间
 * @author huangfukexing
 * @date 2023/10/30 16:04
 */
public class ExThreadPoolTest extends ThreadPoolExecutor {
    public ExThreadPoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ExThreadPoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ExThreadPoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public ExThreadPoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        //任务开始执行
        System.out.println("任务开始执行，执行时间为:" + new Date());
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("任务执行完毕，结束时间为:" + new Date());
        super.afterExecute(r, t);
    }

    public static void main(String[] args) {
        ExThreadPoolTest exThreadPoolTest = new ExThreadPoolTest(1, 1, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        exThreadPoolTest.execute(() ->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("任务结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
