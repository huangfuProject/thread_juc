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
        for (int i = 0; i < 2; i++) {
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
            //第一次获取锁
            LOCK.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到了锁.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                //第一次释放锁
                LOCK.unlock();
            }
            //第二次获取锁
            LOCK.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到了锁.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                //第二次释放锁
                LOCK.unlock();
            }

        }
    }
}
