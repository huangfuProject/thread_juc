package com.three;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 共享锁测试
 *
 * @author huangfukexing
 * @date 2023/10/31 17:33
 */
public class WriteLockTest {
    private final static ReentrantReadWriteLock REENTRANT_READ_WRITE_LOCK = new ReentrantReadWriteLock();
    private final static ReentrantReadWriteLock.WriteLock WRITE_LOCK = REENTRANT_READ_WRITE_LOCK.writeLock();

    public static void main(String[] args) {
        Task task = new Task();
        new Thread(task, "线程1").start();
        new Thread(task, "线程2").start();
        new Thread(task, "线程3").start();
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始获取锁.");
            WRITE_LOCK.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取锁成功开始写入数据.");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "执行完毕，释放锁.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                WRITE_LOCK.unlock();
            }
        }
    }
}
