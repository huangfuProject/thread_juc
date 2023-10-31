package com.three;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 *
 * @author huangfukexing
 * @date 2023/10/31 16:11
 */
public class FairLockTest {
    protected final static ReentrantLock LOCK = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        Task target = new Task();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Thread thread1 = new Thread(target, "线程"+i);
            threadList.add(thread1);
        }

        for (Thread thread : threadList) {
            Thread.sleep(20);
            thread.start();
        }

    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            LOCK.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到了锁.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                LOCK.unlock();
            }
            //当前线程释放锁之后，再次尝试获取，如果是公平锁，他就会排在10个线程之后
            LOCK.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到了锁.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                LOCK.unlock();
            }

        }
    }
}
