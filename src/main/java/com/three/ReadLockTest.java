package com.three;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 共享锁测试
 *
 * @author huangfukexing
 * @date 2023/10/31 17:33
 */
public class ReadLockTest {
    private final static ReentrantReadWriteLock REENTRANT_READ_WRITE_LOCK = new ReentrantReadWriteLock();
    private final static ReentrantReadWriteLock.ReadLock READ_LOCK = REENTRANT_READ_WRITE_LOCK.readLock();
    private final static ReentrantReadWriteLock.WriteLock WRITE_LOCK = REENTRANT_READ_WRITE_LOCK.writeLock();

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始获取数据.");
                READ_LOCK.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "开始读取数据.");
                    TimeUnit.SECONDS.sleep(30);
                    System.out.println(Thread.currentThread().getName() + "执行完毕，释放锁.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    READ_LOCK.unlock();
                }
            }
        }, "线程0").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                WRITE_LOCK.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "获取写锁成功");
                    TimeUnit.SECONDS.sleep(20);
                    System.out.println(Thread.currentThread().getName() + "开始释放锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    WRITE_LOCK.unlock();
                }
            }
        }, "线程4").start();
        Thread.sleep(1000);

        new Thread(task, "线程1").start();
        new Thread(task, "线程2").start();
        new Thread(task, "线程3").start();

    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始获取数据.");
            READ_LOCK.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "开始读取数据.");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "执行完毕，释放锁.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                READ_LOCK.unlock();
            }
        }
    }
}
