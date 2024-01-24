package com.three;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huangfukexing
 * @date 2024/1/24 09:52
 */
public class TryLockTest {

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    private static class Task implements Runnable {
        private final ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            boolean tryLock = lock.tryLock();
            if(tryLock) {
                try{
                    System.out.println(Thread.currentThread().getName() +"获取到了锁.");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }else {
                System.out.println(Thread.currentThread().getName() +"没有抢占到锁.");
            }

        }
    }
}
