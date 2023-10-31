package com.three;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁测试
 *
 * @author huangfukexing
 * @date 2023/10/31 14:14
 */
public class ReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread thread1 = new Thread(task, "线程1");
        thread1.start();
        thread1.join();

        System.out.println("最终的计算结果"+task.getCount());
    }


    private static class Task implements Runnable {
        int count = 0;
        protected final static ReentrantLock LOCK = new ReentrantLock();

        @Override
        public void run() {
            addCount();
        }
        public int getCount() {
            return count;
        }

        public void addCount(){
            System.out.println(Thread.currentThread().getName() + "开始获取锁， 此时加锁次数为：" + LOCK.getHoldCount());
            LOCK.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取锁成功.");
                if(++count < 20) {
                    addCount();
                }
                System.out.println(Thread.currentThread().getName() + "开始释放锁， 此时加锁次数为：" + LOCK.getHoldCount());
            }finally {
                LOCK.unlock();
            }
        }
    }
}
