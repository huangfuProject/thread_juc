package com.three;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可中断锁
 *
 * @author huangfukexing
 * @date 2023/10/31 15:11
 */
public class InterruptReentrantLockTest {
    protected final static ReentrantLock LOCK = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Task target = new Task();
        Thread thread1 = new Thread(target, "线程1");
        Thread thread2 = new Thread(target, "线程2");

        thread1.start();
        //睡眠的原因是先尝试让线程1 获取锁
        TimeUnit.SECONDS.sleep(3);
        thread2.start();
        //线程2等待锁的过程中中断等待
        thread2.interrupt();
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "尝试获取锁.");
                LOCK.tryLock(60, TimeUnit.SECONDS);
                try {
                    System.out.println(Thread.currentThread().getName() + "获取到了锁.");
                    try {
                        TimeUnit.SECONDS.sleep(60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }finally {
                    LOCK.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "等待锁的时候被中断.");
            }

        }
    }
}
