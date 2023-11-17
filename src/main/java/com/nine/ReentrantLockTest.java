package com.nine;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huangfukexing
 * @date 2023/11/17 15:58
 */
public class ReentrantLockTest {

    private static final ReentrantLock reentrantLock = new ReentrantLock(true);

    public synchronized static void main(String[] args) {

        Thread thread = new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"获取到锁");
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        });

        Thread thread1 = new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"获取到锁");
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        });

        thread.setName("线程1");
        thread1.setName("线程2");

        thread.start();
        thread1.start();
    }
}
